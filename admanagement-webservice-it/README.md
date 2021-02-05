# Ausführung über Maven
- Umgebungsvariable JBOSS_HOME muss auf JBOSS-Installation verweisen
- In PowerShell: mvn clean install package `-Djboss.home=$Env:JBOSS_HOME/standalone/deployments (alternativ: Angabe des gesamten Pfades)
- /target/java -jar amWS-IT-Client.jar
- http://localhost:8080/admanagementIT