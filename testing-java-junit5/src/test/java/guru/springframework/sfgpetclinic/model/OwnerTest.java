package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {


  @Test
  void dependentAssertions(){
    Owner owner = new Owner(1l, "Cristian", "Lopez");
    owner.setCity("Bogota");
    owner.setAddress("Calle 123");

    assertAll("Properties Test",
            () -> assertAll("Person Properties",
                    () -> assertEquals("Cristian", owner.getFirstName(), "First name did not match"),
                    () -> assertEquals("Lopez", owner.getLastName(), "Last name did not match")
            ),
            () -> assertAll("Owner Properties",
                    () -> assertEquals("Bogota", owner.getCity(), "City did not match"),
                    () -> assertEquals("Calle 123", owner.getAddress(), "Address did not match"))
    );
    assertThat(owner.getCity(), is("Bogota"));
  }

}