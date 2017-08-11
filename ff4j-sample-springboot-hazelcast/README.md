# FF4j Spring Boot Hazelcast sample #

This example is best demonstrated by running multiple instances of the app.
One way to do this is to use docker. To start the app with 3 replicas using docker run.

`docker-compose up --scale app=3`

Then you can access the api at [app.localtest.me](http://app.localtest.me) and the console app at [app.localtest.me/ff4j-web-console/](http://app.localtest.me/ff4j-web-console/).
The api will return the container id of the container which was used to serve the request so it may be different each time due to load balancing.
When you change a feature in the console the change will be replicated to all containers thanks to Hazelcast.
