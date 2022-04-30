 
 <addr> Projet 12 du parcours Developpeur Web Java. 

 
> Modules : 
 
  * Client UI 
  * API Bibliotheque 
  * Batch 
 
 > Installation necessaire au deploiement : 
  * Installer la version 3.8.1 d'apache maven . 
  * Installer la version 11 de Java. 
 
  
 > Implementation App Web : 
 
  * Se rendre dans le fichier *P10/bibliotheque/src/main/resources/application.properties* de l'api Bibliotheque. 
  * Remplacer les informations vous concernant pour la connexion à votre base de donnée. 
  * Dans votre base de donnée utiliser le scripts SLQ de création de base de données contenant le jeu de démo,
    "P10/SQL/CreationBDD&Demo.SQL". 
  * Ouvrer un terminal et rendez-vous à la racine du dossier "bibliotheque" et utliser la commande : mvn spring-boot:run.
  * Ouvrer un autre terminal et rendez-vous à la racine du dossier "clientUI" et utliser la commande : mvn spring-boot:run.
 
 > Implementation Batch : 
