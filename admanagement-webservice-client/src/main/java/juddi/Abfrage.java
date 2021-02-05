package juddi;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.registry.BulkResponse;
import javax.xml.registry.BusinessLifeCycleManager;
import javax.xml.registry.BusinessQueryManager;
import javax.xml.registry.Connection;
import javax.xml.registry.ConnectionFactory;
import javax.xml.registry.FindQualifier;
import javax.xml.registry.JAXRException;
import javax.xml.registry.RegistryService;
import javax.xml.registry.infomodel.InternationalString;
import javax.xml.registry.infomodel.Key;
import javax.xml.registry.infomodel.Organization;
import javax.xml.registry.infomodel.Service;
import javax.xml.registry.infomodel.ServiceBinding;

public class Abfrage
{
	// Verbindungsparameter. Bitte den geänderten Port 8090 beachten!!!
    private static final String PUBLICATION_MANAGER_URL = "http://localhost:8090/juddiv3/services/publishv2";
    private static final String INQUIRY_MANAGER_URL = "http://localhost:8090/juddiv3/services/inquiry";
    
    // ID des von uns über die jUDDI-Webkonsole gespeicherten Publishers "juddi"
    private static final String JUDDI_PUBLISHER_ID = "juddi";
    
    private BusinessQueryManager mBusinessQueryMgr; // QueryManager zum Abfragen von Registry-Inhalten
    private BusinessLifeCycleManager mBusinessLifecycleMgr; // LifeCycleManager zum Schreiben von Inhalten in das Verzeichnis
    private Connection mConnection;  // Verbindung zum UDDI Registry
	
    public Abfrage()
    {
    	try
    	{
			init();
		}
    	catch (JAXRException e)
    	{
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args)
    {
    	try {
			new Abfrage().init();
		} catch (JAXRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	 /**
     * Verbindung zum jUDDI-Server herstellen
     * @throws JAXRException
     */
    public void init() throws JAXRException
    {
    	// Instanz der ConnectionFactory ermitteln, um eine Verbindung zum UDDI Registry herzustellen
        ConnectionFactory jaxrConnectionFactory = ConnectionFactory.newInstance();

        // Verbindungsparameter wie oben definiert übergeben
        Properties uddiVerbindungsKonfiguration = new Properties();
        uddiVerbindungsKonfiguration.setProperty(
            "javax.xml.registry.queryManagerURL",
            INQUIRY_MANAGER_URL
        );
        uddiVerbindungsKonfiguration.setProperty(
            "javax.xml.registry.lifeCycleManagerURL",
            PUBLICATION_MANAGER_URL
        );
        uddiVerbindungsKonfiguration.setProperty(
            "javax.xml.registry.security.authenticationMethod",
            "UDDI_GET_AUTHTOKEN"
        );
        jaxrConnectionFactory.setProperties(uddiVerbindungsKonfiguration);

        // Verbindung herstellen
        mConnection = jaxrConnectionFactory.createConnection();

        // QueryManager und LifecycleManager laden
        RegistryService registryService = mConnection.getRegistryService();
        mBusinessQueryMgr = registryService.getBusinessQueryManager();
        mBusinessLifecycleMgr = registryService.getBusinessLifeCycleManager();
        
    	/*
    	 * Zugangsdaten für die Verbindung zum UDDI Registry setzen
    	 * In der Standardeinstellung authentifiziert juddi Benutzer, die
    	 * in der Tabelle PUBLISHER gespeichert sind anhand der Spalte PUBLISHER_ID
    	 * unabhängig vom Kennwort. Anmeldung hier als Publisher "juddi" (s.o.)
    	 */
        PasswordAuthentication passwordAuthentication =
            new PasswordAuthentication(JUDDI_PUBLISHER_ID, "".toCharArray());
        Set<PasswordAuthentication> credentials =
            new HashSet<PasswordAuthentication>();
        credentials.add(passwordAuthentication);
        mConnection.setCredentials(credentials);    	
    }      
	
    @SuppressWarnings("unchecked")
	public Map<String,String> sucheServices(String suchbegriff)
    {
    	Map<String,String> serviceInfos = new HashMap<String,String>();
        try
        {            
            System.out.println("=========================================================");
            System.out.println("==================UDDI Abfrage gestartet=================");
            System.out.println("=========================================================");            
            System.out.println();   
            System.out.println("Suchbegriff: \"" + suchbegriff + "\"");   
            System.out.println();   
            for(Organization org: sucheOrganisationenNachNamen(suchbegriff))
            {
            	System.out.println("Organisation: " + org.getName().getValue());
            	System.out.println("Key: " + org.getKey().getId());
            	// Services zur Organisation durchlaufen
            	for(Service service: (Collection<Service>)org.getServices())
            	{
            		System.out.println("|_____Service: " + service.getName().getValue());
            		System.out.println("       |_______Beschreibung: " + service.getDescription().getValue());
            		// Service-Bindings zum Service aufrufen und die darin gespeicherten Links ausgeben
            		for(ServiceBinding sb: (Collection<ServiceBinding>)service.getServiceBindings())
            		{
            			System.out.println("       |_______Link: " + sb.getAccessURI());
            			serviceInfos.put(service.getName().getValue(), sb.getAccessURI());
            		}
            	}
                System.out.println(); 
                System.out.println();           
            }
        } catch (JAXRException theException)
        {
            theException.printStackTrace();
        }
        return serviceInfos;
    }
    
    /**
     * Suche einer registrierten Organisation anhand ihres Namens
     * @param suchBegriff Name der Organisation
     * @return Informationen zur gesuchten Organisation
     * @throws JAXRException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<Organization> sucheOrganisationenNachNamen(String suchbegriff) throws JAXRException
    {
    	Collection<String> findQualifiers = new ArrayList<String>();
    	findQualifiers.add(FindQualifier.CASE_SENSITIVE_MATCH);
    	Collection<String> suchMuster = new ArrayList<String>();
    	suchMuster.add("%" + suchbegriff + "%");

    	// Suche nach Organisationen, deren Name den Suchbegriff enthält
    	BulkResponse response = mBusinessQueryMgr.findOrganizations(
    		findQualifiers,suchMuster,null,null,null,null);
    	Collection orgs = response.getCollection(); 
    	return orgs;
    }
    
    /**
     * Erstellt eine neue Organisation mit dem übergebenen Namen
     * @param organisationsName Name der zu erstellenden Organisation
     * @throws JAXRException
     */
    @SuppressWarnings("unchecked")
	public void erstelleOrganisation(String organisationsName) throws JAXRException
    {        
    	// Erzeugen einer neuen Organisation mithilfe des LifeCycleManagers
        Organization organisation = mBusinessLifecycleMgr.createOrganization(organisationsName);

        // Der LifeCycleManager erwartet eine Collection mit zu speichernden Organisationen als Parameter
        Collection<Organization> zuSpeicherndeOrganisationen = new ArrayList<Organization>();
        zuSpeicherndeOrganisationen.add(organisation);

        // Organisationsliste speichern
        BulkResponse response = mBusinessLifecycleMgr.saveOrganizations(zuSpeicherndeOrganisationen);

        // Key der neuer Organisation ausgeben, sofern keine Ausnahmen auftreten
        if (response.getExceptions() == null)
        {
            Collection<Key> responseCollection = response.getCollection();
            Key key = responseCollection.iterator().next();
            System.out.println("Eine neue Organisation mit dem Namen + \"" + organisationsName + "\" wurde gespeichert.");
            System.out.println("Der Organisation wurde der folgende Schlüssel zugewiesen: " + key.getId());
        } 
        else
        {
            System.out.println("Beim Speichern der Organisation + \"" + organisationsName + "\" ist ein Fehler aufgetreten.");
        }
    }
    
    /**
     * Erstellt einen neuen Service und ordnet diesen über ein ServiceBinding einer bestimmten Organisation zu
     * @param name Name des zu erstellenden Service
     * @param beschreibung Beschreibung des Service
     * @param wsdlUrl URL des WSDL-Dokumentes
     * @param organisation Organisation, welche den Service anbietet
     * @throws JAXRException
     */
    @SuppressWarnings("unchecked")
	public void erstelleService(String name, String beschreibung, String wsdlUrl, Organization organisation) throws JAXRException
    {                
        // Die Methoden der Klasse Service erwarten Texte als Objekte der Klasse InternationalString
        InternationalString nameAlsIS =  mBusinessLifecycleMgr.createInternationalString(name);
        InternationalString beschreibungAlsIS =  mBusinessLifecycleMgr.createInternationalString(beschreibung);
        
        // Service erstellen
        Service service = mBusinessLifecycleMgr.createService(nameAlsIS);
        service.setDescription(beschreibungAlsIS);
        
        // Service Binding erstellen
        ServiceBinding binding = mBusinessLifecycleMgr.createServiceBinding();
        InternationalString serviceBindingBeschreibung = 
        	mBusinessLifecycleMgr.createInternationalString("Binding fuer Service " + name + " mit der Beschreibung " + beschreibung);
        binding.setDescription(serviceBindingBeschreibung);
       
        // URL des WSDL-Dokumentes speichern. Normalerweise wird dies über das Attribut SpecificationLink realisiert, welcher allerdings
        // zwingend ein beschreibendes Objekt vom Typ Concept benötigt. Um das Beispiel nicht zu verkomplizieren wird der Link in diesem Fall
        // im Attribut AccessURI gespeichert, welches normalerweise die URL des WebService und nicht die des WSDL-Dokumentes enthält
        // http://java.sun.com/webservices/docs/2.0/tutorial/doc/JAXR3.html
        binding.setValidateURI(false);
        binding.setAccessURI(wsdlUrl);
        
        // Service Bindings werden einem Service als Liste von Bindings hinzugefügt
        Collection<ServiceBinding> serviceBindings = new ArrayList<ServiceBinding>();        
        serviceBindings.add(binding);
        service.addServiceBindings(serviceBindings);

    	// Services werden einer Organisation als Liste von Services zugeordnet
        Collection<Service> services = new ArrayList<Service>();
        services.add(service);
        organisation.addServices(services); 

        // Der LifecycleManager erwartet eine Liste von Organisationen, die er speichert
        Collection<Organization> zuSpeichernedeOrganisationen = new ArrayList<Organization>();
        zuSpeichernedeOrganisationen.add(organisation);

        // Speichern der Organisation und damit auch des neu verknüpften Service
        BulkResponse response = mBusinessLifecycleMgr.saveOrganizations(zuSpeichernedeOrganisationen);

        // Erfolgsmeldungen ausgeben, falls keine Ausnahmen auftreten
        if (response.getExceptions() == null)
        {
            Collection<Key> responseCollection = response.getCollection();
            Key key = responseCollection.iterator().next();
            System.out.println("Der Organisation mit dem Schlüssel " + key.getId() + " wurde der Service \"" + name + "\" hinzugefügt.");
        } 
        else
        {
            System.out.println("Beim Speichern des Service \"" + name + "\" ist ein Fehler aufgetreten.");
        }
    }   
    
    /*
     * Organisation loeschen
     */
    @SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public void loescheOrganisation(String suchbegriff) throws JAXRException
    {    
    	System.out.println("=========================================================");
        System.out.println("===============Alte Organisationen loeschen==============");
        System.out.println("========================================================="); 
    	for(Organization org: sucheOrganisationenNachNamen(suchbegriff))
        {
        	System.out.println("Organisation: " + org.getName().getValue());
        	System.out.println("Key: " + org.getKey().getId());
        	
        	Collection keys = new ArrayList();
            keys.add(org.getKey());
            BulkResponse response = mBusinessLifecycleMgr.deleteOrganizations(keys);
        }
    	System.out.println(""); 
    }
}
