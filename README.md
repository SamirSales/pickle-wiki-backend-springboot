# Pickle Wiki

The Pickle Wiki is an open source encyclopedia project, written collaboratively and based on the *Wikipedia* system. Unlike the traditional *Wikipedia*, the Pickle Wiki is a search tool with a focus on internal networks.

The application's name is a parody of the name *Wikipedia* combined with the expression *"Pickle Rick"* from the American animation [Rick and Morty](https://en.wikipedia.org/wiki/Rick_and_Morty) (created by *Justin Roiland* and *Dan Harmon*). The genial suggestion of the name was made by [John Soares](https://github.com/JohnSoares) during a brainstorm.

## How to install

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

## License

MIT

Copyright &copy; 2019 Samir Sales