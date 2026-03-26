package com.skillsync.mentor_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillsync.mentor_service.entity.Mentor;
import com.skillsync.mentor_service.repository.MentorRepository;
import com.skillsync.mentor_service.exception.ResourceNotFoundException;

class MentorServiceTest {

    @Mock
    private MentorRepository mentorRepository;

    @InjectMocks
    private MentorService mentorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMentor() {
        Mentor mentor = new Mentor();
        mentor.setName("Dr. Smith");
        mentor.setSkills("Java, Spring");

        when(mentorRepository.save(any(Mentor.class))).thenReturn(mentor);

        Mentor createdMentor = mentorService.addMentor(mentor);

        assertNotNull(createdMentor);
        assertEquals("Dr. Smith", createdMentor.getName());
        verify(mentorRepository, times(1)).save(any(Mentor.class));
    }

    @Test
    void testGetMentorById_Success() {
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("Dr. Smith");

        when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));

        Mentor foundMentor = mentorService.getMentorById(1L);

        assertNotNull(foundMentor);
        assertEquals(1L, foundMentor.getId());
        assertEquals("Dr. Smith", foundMentor.getName());
    }

    @Test
    void testGetMentorById_NotFound() {
        when(mentorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            mentorService.getMentorById(999L);
        });
    }
}
