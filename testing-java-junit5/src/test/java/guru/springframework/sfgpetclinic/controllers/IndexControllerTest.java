package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


class IndexControllerTest implements ControllerTest {

  IndexController indexController;

  @BeforeEach
  void setUp() {
    indexController = new IndexController();
  }

  @DisplayName("Test Proper View Name is Returned for Index Page")
  @Test
  void index(){
    assertEquals("index", indexController.index());
    assertEquals("index", indexController.index(), "Wrong view returned");
    assertEquals("index", indexController.index(), () -> "Another expensive message " +
        "Make me only if you have to");
    assertThat(indexController.index()).isEqualTo("index");
  }

  @DisplayName("Test Exception")
  @Test
  void oopsHandler(){
    assertThrows(ValueNotFoundException.class, () -> {
      indexController.oopsHandler();
    });
  }

  @DisplayName("Test Timeout")
  @Test
  @Disabled
  void testTimeout(){
    // This test will fail if the provided block of code takes more than 100 milliseconds to complete.
    // However, it will allow the block of code to complete, even if it takes longer.
    assertTimeout(Duration.ofMillis(100), () -> {
      Thread.sleep(5000); // This code takes 5 seconds to complete, which is more than 100 milliseconds.
      System.out.println("I got here"); // This message will be printed even if the test fails.
    });
  }

  @DisplayName("Test Timeout Preemptively")
  @Test
  @Disabled
  void testTimeoutPrempt(){
    // This test will fail if the provided block of code takes more than 100 milliseconds to complete.
    // Unlike assertTimeout, this test will stop the execution of the block of code if it takes longer.
    assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
      Thread.sleep(5000); // This code takes 5 seconds to complete, which is more than 100 milliseconds.
      System.out.println("I got here 23434234324324234"); // This message will not be printed because the test will fail before the block of code completes.
    });
  }

  @Test
  void testAssumptionTrue(){
    assumeTrue("CRISTIAN".equalsIgnoreCase(System.getenv("CRISTIAN_RUNTIME")));
  }

  @Test
  void testAssumptionTrueAssumptionIsTrue(){
    assumeTrue("CRISTIAN".equalsIgnoreCase("CRISTIAN"));
  }

  @EnabledOnOs(OS.LINUX)
  @Test
  void testMeOnLinux(){}

  @EnabledOnOs(OS.MAC)
  @Test
  void testMeOnMacOs(){}

  @EnabledOnOs(OS.WINDOWS)
  @Test
  void testMeOnWindows(){}

  @EnabledOnJre(JRE.JAVA_8)
  @Test
  void testMeOnJava8(){}


  @EnabledOnJre(JRE.OTHER)
  @Test
  void testMeOnJava21(){}

  @EnabledIfEnvironmentVariable(named = "USER", matches = "jt")
  @Test
  void testIfUserJT(){ }

  @EnabledIfEnvironmentVariable(named = "USER", matches = "cristian")
  @Test
  void testIfUserCristian(){}


}