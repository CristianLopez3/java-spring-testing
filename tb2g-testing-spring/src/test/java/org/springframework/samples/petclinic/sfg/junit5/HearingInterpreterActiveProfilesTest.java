package org.springframework.samples.petclinic.sfg.junit5;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = HearingInterpreterActiveProfilesTest.ConfigTest.class)
public class HearingInterpreterActiveProfilesTest {


    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class ConfigTest{}

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void wordIHeard(){
        String word = hearingInterpreter.wordIHeader();
        assertEquals("Yanny", word);
    }


}
