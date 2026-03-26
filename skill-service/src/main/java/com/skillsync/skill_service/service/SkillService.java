
package com.skillsync.skill_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillsync.skill_service.entity.Skill;
import com.skillsync.skill_service.repository.SkillRepository;

import com.skillsync.skill_service.exception.ResourceNotFoundException;

@Service
public class SkillService {

    @Autowired
    private SkillRepository repository;

    // Create Skill
    public Skill createSkill(Skill skill) {
        return repository.save(skill);
    }

    // Get All Skills
    public List<Skill> getAllSkills() {
        return repository.findAll();
    }

    // Get Skill By ID
    public Skill getSkillById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }
}