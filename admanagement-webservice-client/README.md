# TODO

# Ausführung in IDE 
- src/main/java/gui/Start

# Ausführung über Maven
- Umgebungsvariable JBOSS_HOME muss auf JBOSS-Installation verweisen
- In PowerShell: mvn clean install package `-Djboss.home=$Env:JBOSS_HOME/standalone/deployments (alternativ: Angabe des gesamten Pfades)
- /target/java -jar amCS-Client-descriptor-client.jar