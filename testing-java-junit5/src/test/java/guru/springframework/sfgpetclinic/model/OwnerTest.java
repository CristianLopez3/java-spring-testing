package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;


class OwnerTest implements ModelTest {


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


  @DisplayName("Test Value Source")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @ValueSource(strings = {"Spring", "Framework", "Crisitan"})
  void parametrizedTest(String value){
    System.out.println(value);
  }


  @DisplayName("Owner Type Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @EnumSource(OwnerType.class)
  void OwnerTypeTest(OwnerType values){
    System.out.println(values);
  }


  @DisplayName("Csv Input Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @CsvSource({
          "FL, 1, 1",
          "OH, 2, 2",
          "MI, 3, 1"
  })
  void csvInputTest(String stateName, int val1, int val2){
    System.out.println(stateName + " = " + val1 + ":" + val2);
  }


  @DisplayName("Csv From File Test")
  @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
  @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
  void csvFromFileTest(String stateName, int val1, int val2){
    System.out.println(stateName + " = " + val1 + ":" + val2);
  }

}