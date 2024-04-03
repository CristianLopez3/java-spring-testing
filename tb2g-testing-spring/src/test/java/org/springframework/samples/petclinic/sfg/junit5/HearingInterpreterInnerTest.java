package org.springframework.samples.petclinic.sfg.junit5;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.LaurenConfig;
import org.springframework.samples.petclinic.sfg.LaurenWordProducer;
import org.springframework.samples.petclinic.sfg.WordProducer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("inner-class")
@SpringJUnitConfig(classes = {HearingInterpreterInnerTest.TestConfig.class})
public class HearingInterpreterInnerTest {

    @Profile("inner-class")
    @Configuration
    static class TestConfig{
        @Bean
        HearingInterpreter hearingInterpreter(){
            return new HearingInterpreter(new LaurenWordProducer());
        }
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void wordIHeard(){
        String word = hearingInterpreter.wordIHeader();
        assertEquals("Lauren", word);
    }

}
