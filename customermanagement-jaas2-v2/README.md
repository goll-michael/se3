# Konfiguration
- demoUser1@password

# Ausführung in IDE
- src/main/java/de.se3.security.Start
- Parameter
    - -Djava.security.auth.login.config=%PATH%\target\classes\config\login.config
    - -Djava.security.policy=%PATH%\target\classes\config\login.policy
 
# Ausführung über Maven
- mvn clean install package
- /target/java -Djava.security.auth.login.config=%PATH%/target/classes/config/login.config -Djava.security.policy=%PATH%/target/classes/config/login.policy -jar customermanagement-jaas.jar
- PowerShell: /target/java ``-Djava.security.auth.login.config=%PATH%/target/classes/config/login.config ``-Djava.security.policy=%PATH%/target/classes/config/login.policy -jar customermanagement-jaas-v2.jar
