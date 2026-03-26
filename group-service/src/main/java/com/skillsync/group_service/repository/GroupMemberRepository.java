
package com.skillsync.group_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillsync.group_service.entity.GroupMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    List<GroupMember> findByGroupId(Long groupId);

    List<GroupMember> findByUserId(Long userId);
    @Modifying
    @Transactional
    void deleteByGroupIdAndUserId(Long groupId, Long userId);
}