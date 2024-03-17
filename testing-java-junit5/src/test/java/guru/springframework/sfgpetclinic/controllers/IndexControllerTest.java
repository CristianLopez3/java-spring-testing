package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

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
  }

  @DisplayName("Test Exception")
  @Test
  void oopsHandler(){
    assertThrows(ValueNotFoundException.class, () -> {
      indexController.oopsHandler();
    });
  }

}