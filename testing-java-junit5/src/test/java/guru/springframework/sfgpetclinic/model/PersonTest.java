package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("model")
class PersonTest {

  Person person;

  @BeforeEach
  void setUp(){
    person = new Person (1l, "Cristian", "Lopez");
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