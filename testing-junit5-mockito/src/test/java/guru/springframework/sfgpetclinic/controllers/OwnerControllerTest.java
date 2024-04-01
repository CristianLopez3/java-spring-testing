package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController ownerController;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;


    @Test
    void estProcessFindFormWildcardString() {
        // GIVEN
        Owner owner = new Owner(5l, "Cristian", "Lopez");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        // THEN
        String view = ownerController.processFindForm(owner, bindingResult, null);

        // WHEN
        assertThat("%Lopez%").isEqualToIgnoringCase(captor.getValue());
    }

    @Test
    void estProcessFindFormWildcardStringWithAnnotations() {
        // GIVEN
        Owner owner = new Owner(5l, "Cristian", "Lopez");
        List<Owner> ownerList = new ArrayList<>();
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        // THEN
        String view = ownerController.processFindForm(owner, bindingResult, null);

        // WHEN
        assertThat("%Lopez%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());

    }

    @Test
    void testProcessCreationFormNoErrors(){
        // GIVEN
        Owner owner = new Owner(5L, "Cristian", "Lopez");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);
        // WHEN
        String view = ownerController.processCreationForm(owner, bindingResult);
        // THEN
        assertThat(view).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }


    @Test
    void testProcessCreationFormHasErrors() {
        // GIVEN
        Owner owner = new Owner(1L,"Cristian", "Lopez");
        given(bindingResult.hasErrors()).willReturn(true);
        // WHEN
        String view = ownerController.processCreationForm(owner, bindingResult);
        // THEN
        assertThat(view).isEqualToIgnoringCase(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);

    }
}