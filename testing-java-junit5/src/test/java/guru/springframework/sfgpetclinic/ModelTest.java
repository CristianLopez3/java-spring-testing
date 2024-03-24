package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;


@Tag("model")
public interface ModelTest {

    @BeforeEach
    default void beforeEachMethod(TestInfo testInfo){
        System.out.println("Running Test - " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

}
