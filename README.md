# Pickle Wiki

The Pickle Wiki is an open source encyclopedia project, written collaboratively and based on the *Wikipedia* system. Unlike the traditional *Wikipedia*, the Pickle Wiki is a search tool with a focus on internal networks.

The application's name is a parody of the name *Wikipedia* combined with the expression *"Pickle Rick"* from the American animation [Rick and Morty](https://en.wikipedia.org/wiki/Rick_and_Morty) (created by *Justin Roiland* and *Dan Harmon*). The genial suggestion of the name was made by [John Soares](https://github.com/JohnSoares) during a brainstorm.

## Tecnology

The Pickle Wiki is a single page application which uses **React JS** as front-end and **Spring Boot** as back-end. To data persistence it  was chosen the **PostgresSQL** and to make text handling it was chosen the **Markdown**.

## How to install

### Front-end

In the file **package.json** set value of *homepage*.

```json
"homepage": "http://localhost:8080/pickle_wiki/"
```

In the file **axios-orders.js** inside the **src** path, set the back-end *URL*.

```javascript
const url = "http://localhost:8080/pickle_wiki_backend";
```

In the file **config.js** inside the **src** path, set the image files path and the homepage. The variables *URL_IMAGES* and *URL_IMAGES_PROFILE* make reference to the path of image files for articles and for profiles, respectively.

```javascript
export const URL_IMAGES = "http://localhost:8080/pickle-wiki-image";

export const URL_IMAGES_PROFILE = "http://localhost:8080/pickle-wiki-image/profiles";

export const URL_HOME_PAGE = "/pickle_wiki";
```

After the configuration is done, execute the command to generate the folder for production.

```
npm run build
```
After, copy the *build* path and paste on the server with the name you have set in the previous configurations *("pickle_wiki" on the example)*.

### Back-end

In the file *application.properties*, set the database parameters...
```
spring.datasource.url=jdbc:postgresql://localhost:5432/pickle_wiki
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```
... and the path for image file uploading.
```
uploading.image.path=/opt/tomcat/webapps/pickle-wiki-image
uploading.image.path.profiles=/opt/tomcat/webapps/pickle-wiki-image/profiles
```
In the the file **Main.java** (path: *'springBoot/src/main/java/io/github/samirsales/'*), comment the *development part* and uncomment the *production part*. 

Uncomment the war configuration in the **pom.xml** file. 
```xml
<packaging>war</packaging>
```
After, execute the **package** command on **maven** to generate the *WAR* file and put it on the server.

## Idioms
At the moment, the texts of the systems are all in Brazilian Portuguese.

## Screenshot

![Pickle Wiki](screenshot.png)

## License

MIT

Copyright &copy; 2018 Samir Sales