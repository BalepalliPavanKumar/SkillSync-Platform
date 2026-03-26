
package com.skillsync.group_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.skillsync.group_service.entity.Group;
import com.skillsync.group_service.entity.GroupMember;
import com.skillsync.group_service.entity.GroupMessage;
import com.skillsync.group_service.service.GroupService;
import com.skillsync.group_service.dto.GroupMessageRequest;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService service;

    // Create group
    @PostMapping
    public Group create(@Valid @RequestBody Group group) {
        return service.createGroup(group);
    }

    // Join group
    @PostMapping("/{id}/join")
    public GroupMember join(@PathVariable Long id,
                            @RequestParam Long userId) {
        return service.joinGroup(id, userId);
    }

    // Leave group
    @PostMapping("/{id}/leave")
    public void leave(@PathVariable Long id,
                      @RequestParam Long userId) {
        service.leaveGroup(id, userId);
    }

    // Get all groups
    @GetMapping
    public List<Group> getAll() {
        return service.getAllGroups();
    }

    // Get group by ID
    @GetMapping("/{id}")
    public Group getById(@PathVariable Long id) {
        return service.getGroupById(id);
    }

    // Get members of group
    @GetMapping("/{id}/members")
    public List<GroupMember> members(@PathVariable Long id) {
        return service.getMembers(id);
    }

    // Post message to group discussion
    @PostMapping("/{id}/messages")
    public GroupMessage postMessage(@PathVariable Long id,
                                    @Valid @RequestBody GroupMessageRequest request) {
        return service.postMessage(id, request.getUserId(), request.getContent());
    }

    // Get group discussion messages
    @GetMapping("/{id}/messages")
    public List<GroupMessage> getMessages(@PathVariable Long id) {
        return service.getMessages(id);
    }
}
