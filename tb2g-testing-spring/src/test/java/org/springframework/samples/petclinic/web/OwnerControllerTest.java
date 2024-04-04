package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {


    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService clinicService;

    MockMvc mockMvc;

    static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @AfterEach
    void tearDown(){
        reset(clinicService);
    }

    @Test
    void processUpdateOwnerFormValid() throws Exception{
        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                    .param("firstName", "Cristian")
                    .param("lastName", "Lopez")
                    .param("address", "Kr 5 AV jimenez")
                    .param("city", "Ibague")
                    .param("telephone", "000000000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void processUpdateOwnerFormNotValid() throws Exception{
        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                        .param("firstName", "Cristian")
                        .param("lastName", "Lopez")
                        .param("telephone", "000000000"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processCreationForm() throws Exception {
        mockMvc.perform(post("/owners/new")
                    .param("firstName", "Cristian")
                    .param("lastName", "Lopez")
                    .param("address", "Kr 13 No 33")
                    .param("city", "Medellin")
                    .param("telephone", "3133221232"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void processCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/new")
                        .param("firstName", "Cristian")
                        .param("lastName", "Lopez")
                        .param("Address", "Kr 13 No 33")
                )
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "city"))
                .andExpect(model().attributeHasFieldErrors("owner","telephone"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }


    @Test
    void processFindFormList() throws Exception {
        given(clinicService.findOwnerByLastName("")).willReturn(Lists.newArrayList(new Owner(),
                new Owner()));
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"));

        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());
        assertEquals("", stringArgumentCaptor.getValue());
    }

    @Test
    void processFindFormNotFound() throws  Exception{
        mockMvc.perform(get("/owners").param("lastName", "Don't find me!"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void processFindFormOneMatch() throws Exception {
        Owner owner = new Owner();
        owner.setId(1);
        String lastName = "justOneMatch";
        owner.setLastName(lastName);
        given(clinicService.findOwnerByLastName("justOneMatch")).willReturn(Lists.newArrayList(owner));

        mockMvc.perform(get("/owners").param("lastName", "justOneMatch"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        then(clinicService).should().findOwnerByLastName(anyString());
    }



    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }


}