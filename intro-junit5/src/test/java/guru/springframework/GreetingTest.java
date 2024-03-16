package guru.springframework;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GreetingTest {

  private Greeting greeting;

  @BeforeAll
  public static void  beforeClass(){
    System.out.println("Before - I am only called Once!! - I am Before Class");
  }

  @BeforeEach
  void setUp(){
    System.out.println("In Before Each....");
    greeting = new Greeting();
  }

  @Test
  void helloWorld() {
    System.out.println(greeting.helloWorld());
  }

  @Test
  void testHelloWorld() {
    System.out.println(greeting.helloWorld("Spring 5"));
  }

  @AfterEach
  void tearDown(){
    System.out.println("In After Each....");
  }

  @AfterAll
  public static void  afterClass(){
    System.out.println("After ***========= - I am only called Once!! - I am Before Class");
  }

}