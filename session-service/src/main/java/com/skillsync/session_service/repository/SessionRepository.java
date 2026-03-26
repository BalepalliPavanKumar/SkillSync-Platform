
package com.skillsync.session_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillsync.session_service.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}