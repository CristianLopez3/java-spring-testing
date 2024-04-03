package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.LaurenWordProducer;
import org.springframework.samples.petclinic.sfg.WordProducer;

@Configuration
public class LaurenConfig {

    @Bean
    public WordProducer getWordProducer(){
        return new LaurenWordProducer();
    }

}
