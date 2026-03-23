
package com.skillsync.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillsync.user_service.entity.UserProfile;

public interface UserRepository extends JpaRepository<UserProfile, Long> {
}