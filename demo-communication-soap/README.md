# Generierung der WSDL-Klassen
- src/main/java/server/StartServerUI
- mvn clean jaxws:wsimport -P wsimport
- Die Klassen werden direkt im Paket client.application gespeichert. Der Server muss anschlie�end beendet werden.
- Derzeit werden noch "javax"-Importe erzeugt. Diese m�ssen zu "jakarta" ausgetauscht werden. Statt >import javax.xml.ws*< muss >import jakarta.xml.ws*< festgelegt werden.
- Ein Rebuild des Projekts ist notwendig.

# Ausf�hrung in IDE 
- src/main/java/server/ServerStart
- src/main/java/client.gui/ClientStart
 
# Ausf�hrung �ber Maven
- mvn clean install package -P build
- /target/java -jar customermanagement-communication-soap-server.jar
- /target/java -jar customermanagement-communication-soap-client.jar