# Konfiguration
- demoUser1@password

# Ausf�hrung in IDE
- src/main/java/de.se3.security.Start
- Parameter
    - -Djava.security.auth.login.config=%PATH%\target\classes\config\login.config
    - -Djava.security.policy=%PATH%\target\classes\config\login.policy
 
# Ausf�hrung �ber Maven
- mvn clean install package
- /target/java -Djava.security.auth.login.config=%PATH%/target/classes/config/login.config -Djava.security.policy=%PATH%/target/classes/config/login.policy -jar customermanagement-jaas.jar
- PowerShell: /target/java ``-Djava.security.auth.login.config=%PATH%/target/classes/config/login.config ``-Djava.security.policy=%PATH%/target/classes/config/login.policy -jar customermanagement-jaas-v2.jar
