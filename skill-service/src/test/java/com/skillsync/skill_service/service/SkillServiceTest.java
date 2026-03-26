package com.skillsync.skill_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillsync.skill_service.entity.Skill;
import com.skillsync.skill_service.repository.SkillRepository;
import com.skillsync.skill_service.exception.ResourceNotFoundException;

class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSkill() {
        Skill skill = new Skill();
        skill.setName("Java");
        
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);

        Skill createdSkill = skillService.createSkill(skill);

        assertNotNull(createdSkill);
        assertEquals("Java", createdSkill.getName());
        verify(skillRepository, times(1)).save(any(Skill.class));
    }

    @Test
    void testGetSkillById_Success() {
        Skill skill = new Skill();
        skill.setId(1L);
        skill.setName("JUnit");

        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));

        Skill foundSkill = skillService.getSkillById(1L);

        assertNotNull(foundSkill);
        assertEquals(1L, foundSkill.getId());
        assertEquals("JUnit", foundSkill.getName());
    }

    @Test
    void testGetSkillById_NotFound() {
        when(skillRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            skillService.getSkillById(999L);
        });
    }
}
