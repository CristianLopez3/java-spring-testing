package guru.springframework.brewery.web.controllers;

import guru.springframework.brewery.web.model.BeerPagedList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Java6Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerPagedListIT {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    void testListBeers(){
        BeerPagedList beerList = testRestTemplate.getForObject("/api/v1/beer", BeerPagedList.class);

        assertThat(beerList.getContent()).hasSize(3);
    }


}
