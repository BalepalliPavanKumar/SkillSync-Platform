
package com.skillsync.skill_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillsync.skill_service.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}