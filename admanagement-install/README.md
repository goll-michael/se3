# Software-Engineering 3

## Wildfly-Einrichtung
- Herunterladen einer Wildfly-Version
- Ausführung von %JBOSS_HOME%/bin/jboss-cli.bat --file=cli-file

## JUDDI-Einrichtung für Admanagement
- Herunterladen von JUDDI Version 2.0.1 (http://archive.apache.org/dist/juddi/2_0_1/)
- Einrichtung einer Umgebungsvariable für Java 8 (z. B. JAVA_HOME_JUDDI)
- Anpassung in setclasspath.bat
 - Ersetzung von JAVA_HOME zu JAVA_HOME_JUDDI
- Anpassung in server.cfg
 - Ersetzung des Ports 8080 zu Port 8090
- Ausführung von startup.bat
- Aufruf der Seite http://localhost:8090/juddi-console
 - Aufruf von "get_authToken" (userID="jdoe",cred=""). Den Wert in "<authInfo>...</authInfo>" in die Zwischenablage kopieren.
 - Aufruf von "save_publisher" (authInfo=aus Zwischenablage einfügen,publisherID="juddi",publisherName="juddi")
 
## Admanagement-Einrichtung
- Zur Ausführung wird in jedem Service ein Kunde mit eine beliebigen Kundennummer (z. B. "1") vorausgesetzt.
- Die Einrichtung kann über den Java-Client oder über die Weboberfläche durchgeführt werden.