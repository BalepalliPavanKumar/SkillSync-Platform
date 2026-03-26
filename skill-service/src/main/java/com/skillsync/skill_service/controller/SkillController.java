package com.skillsync.skill_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillsync.skill_service.entity.Skill;
import com.skillsync.skill_service.service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService service;

    // POST /skills
    @PostMapping
    public Skill create(@RequestBody Skill skill) {
        return service.createSkill(skill);
    }

    // GET /skills
    @GetMapping
    public List<Skill> getAll() {
        return service.getAllSkills();
    }

    // GET /skills/{id}
    @GetMapping("/{id}")
    public Skill getById(@PathVariable Long id) {
        return service.getSkillById(id);
    }
}