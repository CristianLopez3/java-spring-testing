package guru.springframework.sfgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Disabled until we learn Mocking")
class OwnerSDJpaServiceTest {

  OwnerSDJpaService ownerSDJpaService;

  @BeforeEach
  void setUp() {
    ownerSDJpaService = new OwnerSDJpaService(null, null, null);
  }

  @Disabled
  @Test
  void findByLastName() {
    assertEquals("Cristian", ownerSDJpaService.findByLastName("Cristian").getFirstName());
  }

  @Test
  void findAllByLastNameLike() {
  }

  @Test
  void findAll() {
  }

  @Test
  void findById() {
  }

  @Test
  void save() {
  }

  @Test
  void delete() {
  }

  @Test
  void deleteById() {
  }
}