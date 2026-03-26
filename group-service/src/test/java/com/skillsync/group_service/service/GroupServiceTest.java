package com.skillsync.group_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillsync.group_service.entity.Group;
import com.skillsync.group_service.repository.GroupRepository;
import com.skillsync.group_service.exception.ResourceNotFoundException;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGroup() {
        Group group = new Group();
        group.setName("Cloud Native Group");

        when(groupRepository.save(any(Group.class))).thenReturn(group);

        Group createdGroup = groupService.createGroup(group);

        assertNotNull(createdGroup);
        assertEquals("Cloud Native Group", createdGroup.getName());
        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    void testGetGroupById_Success() {
        Group group = new Group();
        group.setId(1L);
        group.setName("Cloud Native Group");

        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        Group foundGroup = groupService.getGroupById(1L);

        assertNotNull(foundGroup);
        assertEquals(1L, foundGroup.getId());
        assertEquals("Cloud Native Group", foundGroup.getName());
    }

    @Test
    void testGetGroupById_NotFound() {
        when(groupRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            groupService.getGroupById(999L);
        });
    }
}
