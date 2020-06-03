package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService service;

    @Test
    void findAll() {
        service.findAll();
        verify(visitRepository).findAll();
        clearInvocations(visitRepository);

        List<Visit> visits = Arrays.asList(new Visit(1L), new Visit(2L), new Visit(3L));
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();
        verify(visitRepository).findAll();
        assertTrue(foundVisits.containsAll(visits) && (foundVisits.size() == visits.size()));
    }

    @Test
    void findById() {
        assertNull(service.findById(1L));
        verify(visitRepository).findById(1L);
        clearInvocations(visitRepository);

        Visit visit = new Visit(1L);
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));
        assertEquals(visit, service.findById(1L));
        verify(visitRepository).findById(1L);
    }

    @Test
    void save() {
        Visit visit = new Visit(1L);
        assertNull(service.save(visit));
        verify(visitRepository).save(visit);
        clearInvocations(visitRepository);

        when(visitRepository.save(visit)).thenReturn(visit);
        assertEquals(visit, service.save(visit));
        verify(visitRepository).save(visit);
    }

    @Test
    void delete() {
        Visit visit = new Visit(1L);
        service.delete(visit);
        verify(visitRepository).delete(visit);
    }

    @Test
    void deleteById() {
    }
}