### Sample for FF4J and hystrix

This sample illustrates the problem related in (http://stackoverflow.com/questions/40161243/ff4jflip-not-work-with-hystrix) where CGLIB classes where not flipping with FF4J AOP module.

It use the version 1.6+ from FF4J which now handle propertly the cglib enhanced libraries.

