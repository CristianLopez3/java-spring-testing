package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTest;
import org.junit.jupiter.api.*;

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


}