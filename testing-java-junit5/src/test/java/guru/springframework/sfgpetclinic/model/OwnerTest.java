package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@Tag("model")
class OwnerTest {


  @Test
  void dependentAssertions(){
    Owner owner = new Owner(1l, "Cristian", "Lopez");
    owner.setCity("Bogota");
    owner.setAddress("Street 123");

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