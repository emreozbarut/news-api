NEWS-API

API-KEY on the application.yml file is personal api key on gnews.io. For getting free api key please sign up here: https://gnews.io/register.

While running the app API-KEY must be pass as environment variable.

Example: java -DAPI-KEY=<API-KEY> -jar /path/to/news-api-0.0.1-SNAPSHOT.jar

You can see the endpoints that implemented on: http://{host}:{port}/swagger-ui.html