package org.springframework.samples.petclinic.sfg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class, YannyConfig.class})
public class HearingInterpreterWithYannyTest {

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Test
    public void wordIHeader() {
        String word = hearingInterpreter.wordIHeader();
        assertEquals("Yanny", word);
    }
}