package com.skillsync.group_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;  // ✅ IMPORTANT

import com.skillsync.group_service.entity.Group;
import com.skillsync.group_service.entity.GroupMember;
import com.skillsync.group_service.entity.GroupMessage;
import com.skillsync.group_service.repository.GroupMemberRepository;
import com.skillsync.group_service.repository.GroupRepository;
import com.skillsync.group_service.repository.GroupMessageRepository;

import com.skillsync.group_service.exception.ResourceNotFoundException;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private GroupMemberRepository memberRepo;

    @Autowired
    private GroupMessageRepository messageRepo;

    // ✅ Create group
    public Group createGroup(Group group) {
        return groupRepo.save(group);
    }

    // ✅ Join group
    public GroupMember joinGroup(Long groupId, Long userId) {
        GroupMember member = new GroupMember();
        member.setGroupId(groupId);
        member.setUserId(userId);
        return memberRepo.save(member);
    }

    // ✅ Leave group (FIXED 🔥)
    @Transactional
    public void leaveGroup(Long groupId, Long userId) {
        memberRepo.deleteByGroupIdAndUserId(groupId, userId);
    }

    // ✅ Get all groups
    public List<Group> getAllGroups() {
        return groupRepo.findAll();
    }

    // ✅ Get group by id
    public Group getGroupById(Long id) {
        return groupRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + id));
    }

    // ✅ Get members of group
    public List<GroupMember> getMembers(Long groupId) {
        return memberRepo.findByGroupId(groupId);
    }

    public GroupMessage postMessage(Long groupId, Long userId, String content) {
        groupRepo.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + groupId));

        GroupMessage message = new GroupMessage();
        message.setGroupId(groupId);
        message.setUserId(userId);
        message.setContent(content);
        message.setCreatedAt(java.time.LocalDateTime.now());
        return messageRepo.save(message);
    }

    public List<GroupMessage> getMessages(Long groupId) {
        return messageRepo.findByGroupIdOrderByCreatedAtAsc(groupId);
    }
}
