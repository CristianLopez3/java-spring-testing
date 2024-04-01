package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;



@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void findAll(){
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        given(visitRepository.findAll()).willReturn(visits);
        Set<Visit> foundVisits = service.findAll();

        assertEquals(1, foundVisits.size());
        then(visitRepository).should().findAll();
    }

    @DisplayName("Save Test")
    @Test
    void save(){
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);
        Visit savedVisit = service.save(new Visit());
        then(visitRepository).should().save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Find By Id Test")
    @Test
    void findById() {
        Visit visit = new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);
        assertThat(foundVisit).isNotNull();

        then(visitRepository).should().findById(anyLong());
    }

    @DisplayName("Delete Test")
    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        then(visitRepository).should().delete(any(Visit.class));
    }

    @DisplayName("Delete By Id Test")
    @Test
    void deleteById() {
        service.deleteById(1L);
        then(visitRepository).should().deleteById(anyLong());
    }

    
}