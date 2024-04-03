package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("classpath:yanny.properties")
@ActiveProfiles("externalized")
@SpringJUnitConfig(classes = PropertiesYannyTest.ConfigTest.class )
class PropertiesYannyTest {

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class ConfigTest{}

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void getWord() {
        String word = hearingInterpreter.wordIHeader();
        assertEquals("YaNNy", word);
    }
}