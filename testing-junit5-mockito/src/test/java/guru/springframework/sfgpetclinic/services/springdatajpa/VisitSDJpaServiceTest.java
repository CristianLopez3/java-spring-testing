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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();

        verify(visitRepository).findAll();
        assertEquals(1, foundVisits.size());
    }

    @DisplayName("Save Test")
    @Test
    void save(){
        Visit visit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(new Visit());
        verify(visitRepository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Find By Id Test")
    @Test
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);
        assertThat(foundVisit).isNotNull();

        verify(visitRepository).findById(anyLong());
    }

    @DisplayName("Delete Test")
    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @DisplayName("Delete By Id Test")
    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }

    
}