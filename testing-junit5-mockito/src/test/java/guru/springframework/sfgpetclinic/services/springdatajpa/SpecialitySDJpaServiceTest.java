package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock()
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


    @Test
    void testDoThrow(){
        doThrow(new RuntimeException("Booom")).when(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows(){
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("Boom"));
        assertThrows(RuntimeException.class, () ->  specialtyRepository.findById(1L));
        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBdd(){
        willThrow(new RuntimeException("Boom")).given(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        then(specialtyRepository).should().delete(any());
    }


    @Test
    void testSaveLambda(){
        // GIVEN
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);

        // WHEN
        Speciality returnedSpecialty = service.save(speciality);

        // THEN
        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }


    @Test
    void testSaveLambdaNotMatch(){
        // GIVEN
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not a Match");

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        lenient().when(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).thenReturn(savedSpecialty);

        // WHEN
        Speciality returnedSpecialty = service.save(speciality);

        // THEN
        assertNull(returnedSpecialty);
    }


}