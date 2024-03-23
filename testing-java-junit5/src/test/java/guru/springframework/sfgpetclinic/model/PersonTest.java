package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PersonTest implements ModelTest {

  Person person;

  @BeforeEach
  void setUp(){
    person = new Person (1L, "Cristian", "Lopez");
  }

  @Test
  void testAllAssertions(){
    assertAll("Test Props Set",
            () -> assertEquals("Cristian", person.getFirstName()),
            () -> assertEquals("Lopez", person.getLastName())
    );
  }

  @Test
  void testAllAssertionsMessages() {
    assertAll("Test Props Set",
            () -> assertEquals("Cristian", person.getFirstName(), "First Name Failed"),
            () -> assertEquals("Lopez", person.getLastName(), "Last Name Failed")
    );

  }

  @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
  @DisplayName("My Repeated Test")
  @Test
  void myRepeatedTest(){
    // todo - implement
  }

}