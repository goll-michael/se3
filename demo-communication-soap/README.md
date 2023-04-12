# Generierung der WSDL-Klassen
- src/main/java/server/StartServerUI
- mvn clean jaxws:wsimport -P wsimport
- Die Klassen werden direkt im Paket client.application gespeichert. Der Server muss anschließend beendet werden.
- Derzeit werden noch "javax"-Importe erzeugt. Diese müssen zu "jakarta" ausgetauscht werden. Statt >import javax.xml.ws*< muss >import jakarta.xml.ws*< festgelegt werden.
- Ein Rebuild des Projekts ist notwendig.

# Ausführung in IDE 
- src/main/java/server/ServerStart
- src/main/java/client.gui/ClientStart
 
# Ausführung über Maven
- mvn clean install package -P build
- /target/java -jar customermanagement-communication-soap-server.jar
- /target/java -jar customermanagement-communication-soap-client.jar