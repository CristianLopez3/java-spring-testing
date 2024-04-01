package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void deleteByObjectTest(){
        Speciality speciality = new Speciality();
        service.delete(speciality);
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findByIdTest(){

        // GIVEN
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        // WHEN
        Speciality foundSpeciality = service.findById(1L);

        // THEN
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();

    }


    @Test
    void delete() {
        service.delete(new Speciality());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);
        then(specialtyRepository).should(times(2)).deleteById(anyLong());
    }

    @Test
    void deleteByIdVerify() {
        service.deleteById(1L);
        verify(specialtyRepository).deleteById(1L);
    }

    @Test
    void deleteByIdVerifyTimes() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1L);
        then(specialtyRepository).should(atLeastOnce()).deleteById(anyLong());
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        then(specialtyRepository).should(atMost(5)).deleteById(anyLong());
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);
        then(specialtyRepository).should(times(2)).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(5L);

    }



}