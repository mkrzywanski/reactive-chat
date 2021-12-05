# reactive-chat

<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
        <li><a href="#built-with">Built With</a></li>
    </li>
    <li><a href="#how-to-use">How to use</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

## About The Project

This is a sample chat application using reactive programming.

### Built With

* Kotlin
* Java 17
* Spring WebFlux
* ReactJS

### How to use
#### Backend

To run backend application you need Java 14 to be installed. Then you can use gradlew wrapper to run it directly :
```
gradlew.sh bootRun
```
from `chat-server` directory. This will run the application on `localhost:8080` and weboscket will be exposed on `/event-emitter` endpoint.
#### Frontend

To run chat client frontend you will need to have NodeJS and npm installed. Then you can use :
```
npm run start
```
from `chat-client` directory to start the application on `localhost:3000`

#### Docker

If you prefer you can run everything with docker.
1. Build the docker image using JIB inside `chat-server` directory :
    ```
    gradlew.bat jibDockerBuild
    ```
2. Run the docker compose from root directory :
   ```
   docker-compose up
   ```

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Your Name - [@twitter_handle](https://twitter.com/m_krzyw) - michal.krzywanski1@gmail.com

Project Link: [https://github.com/mkrzywanski/reactive-chat](https://github.com/mkrzywanski/reactive-chat)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/github_username
