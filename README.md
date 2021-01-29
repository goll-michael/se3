# Software-Engineering 3

## Wildfly-Einrichtung für Admanagement
- Herunterladen der folgenden Derby-Dateien (siehe Maven Repository)
 - derby.jar
 - derbynet.jar
 - derbyshared.jar
 - derbytools.jar
- Herunterladen einer Wildfly-Version
- Erstellung einer Datei 'cli-file' mit folgendem Inhalt:
connect
module add --name=org.apache.derby --resources=%DERBY_PATH%/derby.jar,%DERBY_PATH%/derbynet.jar,%DERBY_PATH%/derbyshared.jar,%DERBY_PATH%/derbytools.jar --resource-delimiter=, --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=derby-embedded:add(driver-name=derby-embedded, driver-module-name=org.apache.derby, driver-class-name=org.apache.derby.jdbc.EmbeddedDriver)
data-source add --name=amCS-Datenquelle --driver-name=derby-embedded --connection-url=jdbc:derby:${jboss.server.base.dir}/amCS_db;create=true --jndi-name=java:jboss/jdbc/amCS-Datenquelle
data-source add --name=amWEB-Datenquelle --driver-name=derby-embedded --connection-url=jdbc:derby:${jboss.server.base.dir}/amWEB_db;create=true --jndi-name=java:jboss/jdbc/amWEB-Datenquelle
data-source add --name=amWEB-ET-Datenquelle --driver-name=derby-embedded --connection-url=jdbc:derby:${jboss.server.base.dir}/amWEB_ET_db;create=true --jndi-name=java:jboss/jdbc/amWEB-ET-Datenquelle
data-source add --name=amWEB-IT-Datenquelle --driver-name=derby-embedded --connection-url=jdbc:derby:${jboss.server.base.dir}/amWEB_IT_db;create=true --jndi-name=java:jboss/jdbc/amWEB-IT-Datenquelle
data-source add --name=amWEB-SWT-Datenquelle --driver-name=derby-embedded --connection-url=jdbc:derby:${jboss.server.base.dir}/amWEB_SWT_db;create=true --jndi-name=java:jboss/jdbc/amWEB-SWT-Datenquelle
- Ausführung des folgenden Befehls: '%JBOSS_HOME%/bin/jboss-cli.bat --file=cli-file'

## Eclipse-Konfiguration
- Java -> Installed JREs -> Hinzufügen von JDK 11 (https://jdk.java.net/archive)
- Java -> Installed JREs -> Execution Environment -> JavaSE-11 -> Aktivierung des hinzugefügten JDK 11

## Import in Eclipse
- File -> Import...
- Maven -> Existing Maven Projects -> Next -> Root Directory auswählen -> Finish

## Ausführung
Die notwendigen Tätigkeiten zur Ausführung sind im jeweilgen Projekt dokumentiert. Vor der Ausführung der Admanagement-Projekte muss admanagement-parent mit 'mvn clean install package' gebaut werden.

## Maven-Tags
- includeBaseDirectory: Bezeichnung des Projekts in in Verzeichnisstruktur übernehmen
- outputDirectory: Verzeichnisstruktur innerhalb des generierten Artefakts (z. B. jar).