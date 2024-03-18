# Testing with JUnit 5  ♨️

Notes about testing an spring simulated app with JUNit and its assertions.

>[!IMPORTANT]
> This is a guide to understand the basics of JUnit 5, if you want to know more about JUnit 5, please visit the official documentation.


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


### Display name 💻

You can change the display name of the test with the next code:
```java
@Test
@DisplayName("Test to check the index view")
void someTest(){}
```
This is a good practice to have a good name for your tests, it's a good practice to have a good name for your tests.


### assertThrows ✅

This is a method that you can use to check if an exception is thrown, look at the next code:
```java
@Test
void someTest(){
  assertThrows(NullPointerException.class, () -> {
    throw new NullPointerException();
  });
}
```

### assertTimeout ⏱️

assertTimeout and assertTimeoutPreemptively are two methods provided by JUnit 5 for testing whether 
a block of code completes within a certain period of time. 

* assertTimeout runs the provided block 
of code and then checks if it is completed within the specified time.If the block of code takes longer 
than specified, the test will still complete, but the assertion will fail.  

* assertTimeoutPreemptively also runs the block of code and checks if it is completed within the specified time. 
However, unlike assertTimeout, if the block of code takes longer than specified, 
assertTimeoutPreemptively will stop the execution of the block of code and the test will fail.

### assumeTrue 🤔

Assumptions are being used in Java unit testing. Assumptions are statements that 
we assume to be true before executing a test. If an assumption turns out to be false during the test
execution, the test won't fail but will be simply ignored.
In the context of testing with Java, assumptions are useful for writing conditional tests based on 
certain conditions of the runtime environment or available data. Here's a more detailed explanation 
of using assumptions:
1. Environment preconditions: Assumptions can be used to verify the conditions of the environment 
before running a test. For example, in the first test method (testAssumptionTrue), it assumes that 
the environment variable CRISTIAN_RUNTIME has the value "CRISTIAN". If this condition is not met, the
test won't be executed, which can be useful to avoid running tests in undesired or inadequate environments.
2. Data or preconditions checks: Assumptions can also be used to verify data or preconditions before 
running a test. For instance, in the second test method (testAssumptionTrueAssumptionIsTrue), it assumes
that the string "CRISTIAN" is equal to itself. This is a trivial assumption that will always be true, but
it illustrates how assumptions can be used to check preconditions before executing a more complex test.

In summary, assumptions are valuable in Java testing to condition the execution of tests based on certain
conditions of the environment or available data. This can help write more robust and 
flexible tests that adapt to different execution contexts. However, it's important to use assumptions 
judiciously and ensure that the assumed conditions are relevant and necessary for the proper execution of the tests.

### Conditional Test Execution 🚀

Conditional test execution is a feature provided by JUnit 5 that allows tests to be executed based on certain conditions.
This can be useful for running tests in specific environments, with specific data, or under specific conditions.
Conditions like: 
* Operating system (OS)
* Java version (JRE)
* Environment variables (System.getenv())
* System properties (System.getProperty())