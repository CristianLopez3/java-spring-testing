package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController ownerController;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp(){
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();
                    String name = invocation.getArgument(0);
                    if(name.equals("%Lopez%")){
                        owners.add(new Owner(1L, "Cristian", "Lopez"));
                        return owners;
                    } else if (name.equals("%NotFoundName%")){
                        return owners;
                    } else if (name.equals("%FoundMe%")){
                        owners.add(new Owner(1L, "Cristian", "Lopez"));
                        owners.add(new Owner(2L, "Camilo", "Martinez"));
                        return owners;
                    }

                    throw new RuntimeException("Invalid Argument");
                });
    }


    @Test
    void testProcessFindFormWildcardString() {
        // GIVEN
        Owner owner = new Owner(5l, "Cristian", "Lopez");
        // THEN
        String view = ownerController.processFindForm(owner, bindingResult, null);
        // WHEN
        assertThat("%Lopez%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(view);

    }

    @Test
    void testProcessFindFormWildcardStringNotFound() {
        // GIVEN
        Owner owner = new Owner(5l, "Cristian", "NotFoundName");
        // THEN
        String view = ownerController.processFindForm(owner, bindingResult, Mockito.mock(Model.class));
        // WHEN
        assertThat("%NotFoundName%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(view);
    }

    @Test
    void testProcessFindFormWildcardStringFound() {
        // GIVEN
        Owner owner = new Owner(5l, "Cristian", "FoundMe");
        InOrder inOrder = inOrder(ownerService, model);
        // THEN
        String view = ownerController.processFindForm(owner, bindingResult, model);
        // WHEN
        assertThat("%FoundMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(view);

        // in order asserts
        inOrder.verify(ownerService).findById(anyLong());
        inOrder.verify(model).addAttribute(anyString(), anyList());
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