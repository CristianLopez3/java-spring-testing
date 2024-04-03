package org.springframework.samples.petclinic.sfg;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class LaurenWordProducer implements WordProducer{

    @Override
    public String getWord() {
        return "Lauren";
    }


}
