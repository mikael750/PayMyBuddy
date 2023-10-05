# PayMyBuddy

### Description
PayMyBuddy est une application qui permet aux clients de transférer de l'argent pour gérer leurs finances ou payer leurs amis.

### Prerequis
L'application fonctionne avec Java 17, Maven et MYSQL

### Installation
Après avoir téléchargé les prérequis:

Vous pouvez importer le code dans n'importe quel IDE et le lancé.
Le projet a été réalisé avec IntelliJ IDEA.

Pour exécuter le programme, il doit être lancé avec les variables d'environnement suivantes dans application.properties

spring.datasource.url={mysql database}
spring.datasource.username={your username}
spring.datasource.password={your password}

Pour installer l'API depuis un terminal,
aller dans alerts, la ou il y a le pom.xml,
et faite la commande :
```
mvn install
```

### Lancer l'application
Après l'avoir installé,
vous retrouverez le jar du projet dans
'application\target'.
Pour le lancer avec le jar,
ouvrer un terminal ici et faire la commande :
```
java -jar application-1.0.1-SNAPSHOT.jar
```

### Testing
L'application dispose de tests unitaires et de tests d'intégration.

Pour exécuter les tests à partir de maven, allez dans le dossier qui contient le fichier pom.xml et exécutez la commande ci-dessous.
```
mvn site
```
### Diagramme de classe UML
![diagrammeUmlPayMyBuddy](https://github.com/mikael750/PayMyBuddy/assets/91464417/797a2989-b4a6-46b0-9c04-4581e53993d3)

### Modèle physique de données
![MDPPayMyBuddy](https://github.com/mikael750/PayMyBuddy/assets/91464417/bb3764ee-3279-4625-bc8d-37e880f96e12) 