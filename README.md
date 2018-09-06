# Pickle Wiki

The Pickle Wiki is an open source encyclopedia project, written collaboratively and based on the *Wikipedia* system. Unlike the traditional *Wikipedia*, the Pickle Wiki is a search tool with a focus on internal networks.

The application's name is a parody of the name *Wikipedia* combined with the expression *"Pickle Rick"* from the American animation [Rick and Morty](https://en.wikipedia.org/wiki/Rick_and_Morty) (created by *Justin Roiland* and *Dan Harmon*). The genial suggestion of the name was made by [John Soares](https://github.com/JohnSoares) during a brainstorm.

## Tecnology

The Pickle Wiki is a single page application which uses **React JS** as front-end and **Spring Boot** as back-end. To data persistence it  was chosen the **PostgresSQL** and to make text handling it was chosen the **Markdown**.

## Idioms
At the moment, the texts of the systems are all in Brazilian Portuguese.

## How to install

### Front-end (ReactJS)

In the file **package.json** set valeu of *homepage*.

```json
"homepage": "http://localhost:8080/pickle_wiki/"
```
At react project set the file **config.js** inside of the *src* path.

```javascript
export const URL_IMAGES = "http://localhost:8080/pickle-wiki-image";

export const URL_IMAGES_PROFILE = "http://localhost:8080/pickle-wiki-image/profiles";

export const URL_HOME_PAGE = "/pickle_wiki";
```

*URL_IMAGES* AND *URL_IMAGES_PROFILE* are the path of image files for articles and for profiles, respectively.

After the configuration is done, execute the command to generate the folder for production.

```
    npm run build
```
Then, copy the *build* path and paste on the server with the name you set in the configurations before *("pickle_wiki" on the example)*.

## Screenshot

![Pickle Wiki](screenshot.png)

## License

MIT

Copyright &copy; 2018 Samir Sales