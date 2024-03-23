package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Owner Map Service Test")
@Tag("services")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp(){
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);
        System.out.println("First before each");
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero(){
        int ownerCount = ownerMapService.findAll().size();

        assertEquals(0, ownerCount);
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes {


        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);
            System.out.println("Nested before each");
        }

        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();
            assertThat(petTypeCount).isNotNull().isEqualTo(2);
        }

        @DisplayName("Save Owners Test - ")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Cristian", "Lopez"));
            }

            @DisplayName("Save Owner Test")
            @Test
            void ownerAreStillOne() {
                int ownerCount = ownerMapService.findAll().size();
                assertThat(ownerCount).isOne();
            }


        }

    }


    @DisplayName("Save Owner Test")
    @Test
    void ownerAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertThat(ownerCount).isZero();
    }

}