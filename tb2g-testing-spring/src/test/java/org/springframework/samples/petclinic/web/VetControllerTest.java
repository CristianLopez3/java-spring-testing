package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService clickService;

    @Mock
    Map<String, Object> model;

    @InjectMocks
    VetController controller;

    List<Vet> vetsList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        vetsList.add(new Vet());
        given(clickService.findVets()).willReturn(vetsList);
    }

    @Test
    void testShowVetList() {
        // WHEN
        String view = controller.showVetList(model);
        // THEN
        then(clickService).should().findVets();
        then(model).should().put(anyString(), any());
        assertThat(("vets/VetList")).isEqualToIgnoringCase(view);
    }

    @Test
    void testShowResourcesVetList() {
        // WHEN
        Vets vets = controller.showResourcesVetList();


        //THEN
        then(clickService).should().findVets();
        assertEquals(1, vets.getVetList().size());
    }
}