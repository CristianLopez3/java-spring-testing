# Testing with JUnit 5

Notes about testing an spring simulated app with JUNit and its assertions.


### Messages

Look at the next code: 
```java
@Test
void someTest(){
  assertEquals("index", "index"); 
  assertEquals("index2", "index", "Wrong view, return to index view");
}
```
You can see how in the first one we have two methods and that's cool, but that's kind of problematic
when the test failed in a CI shell, for that write a message to indicate what failed It's a pretty nice
practice when you write your tests.
