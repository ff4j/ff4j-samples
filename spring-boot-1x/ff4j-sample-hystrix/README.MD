### Sample for FF4J and hystrix

This sample illustrates the problem related in (http://stackoverflow.com/questions/40161243/ff4jflip-not-work-with-hystrix) where CGLIB classes where not flipping with FF4J AOP module.

It use the version 1.6+ from FF4J which now handle propertly the cglib enhanced libraries.

to run the sample please execute : 
```bash
mvn clean spring-boot:run
```

* First go to URL `http://localhost:7300/hello?id=10`
You should get : 
```json
{"message": "doIntegration: ok with 10"}
```
which is the default message (feature disabled)

* Go the console old `http://localhost:7300/ff4j-old` or the new one with 1.6+ `http://localhost:7300/ff4j/home`

* Enabled the feature `f1`

* Again, go to URL `http://localhost:7300/hello?id=10`

You should get : 
```json
{ "message": "doIntegration: mock with 10" }
```
The feature is now well flipped, even with HystrixCommand



