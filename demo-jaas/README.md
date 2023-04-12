# Konfiguration
- benutzer1@passwort1
- benutzer2@passwort2

# Ausführung in IDE
- src/main/java/gui/Start
- Parameter
    - -Djava.security.auth.login.config=%PATH%\src\main\resources\config\login.config
    - -Djava.security.policy=%PATH%\src\main\resources\config\login.policy
 
# Ausführung über Maven
- mvn clean install package
- /target/java -Djava.security.auth.login.config=%PATH%/target/classes/config/login.config -Djava.security.policy=%PATH%/target/classes/config/login.policy -jar customermanagement-jaas.jar
- PowerShell: /target/java ``-Djava.security.auth.login.config=%PATH%/target/classes/config/login.config ``-Djava.security.policy=%PATH%/target/classes/config/login.policy -jar customermanagement-jaas.jar