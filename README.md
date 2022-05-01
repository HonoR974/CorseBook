<h1 align="center">
 CorseBook
</h1>

 
> Installation necessaire au deploiement : 

 * Installer la version 3.8.1 d'apache maven . 
 * Installer la version 11 de Java. 

> Mise en place du front-end
 * Installation d'un bucket S3. 
 * Creation d'un bucket sur [AWS S3](https://hackernoon.com/hosting-an-angular-application-on-amazon-s3-using-github-actions) et d'un IAM.
 * Nommez le : `testp12`.
 * Entrez vos identifiant dans le fichier `CorseBook/angular/src/app/_services/upload-s3.service.ts`. 

> Modules : 
  * angular 
  * springboot 
  
 
  
 > Implementation du front-end : 
  
  * Se rendre dans le fichier `CorseBook/angular` avec un terminal. 
  * Tapez la commande `npm install`.
  * Puis la commande `ng serve`.
 
  > Implementation du back -end : 

  * Ouvrez un terminal et rendez-vous à la racine du dossier `CorseBook/springboot/src/main/resources/application.properties`.
  * Remplacer les informations vous concernant pour la connexion à votre base de donnée.
  * Tapez: `mvn spring-boot:run`
 
