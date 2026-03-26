package com.skillsync.mentor_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillsync.mentor_service.entity.Mentor;
import java.util.Optional;
import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByUserId(Long userId);
    List<Mentor> findByStatus(String status);
}
