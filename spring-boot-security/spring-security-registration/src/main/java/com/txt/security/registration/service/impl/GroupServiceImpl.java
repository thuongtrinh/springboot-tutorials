package com.txt.security.registration.service.impl;

import com.txt.security.registration.entity.authen.GroupMembers;
import com.txt.security.registration.entity.authen.Groups;
import com.txt.security.registration.repository.GroupMembersRespository;
import com.txt.security.registration.repository.GroupRespository;
import com.txt.security.registration.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRespository groupRespository;
    private final GroupMembersRespository groupMembersRespository;

    @Override
    public List<String> getRolesByUserGroups(String username) {
        List<String> roles = getNameGroupByUsername(username);
        return roles;
    }

    private List<String> getNameGroupByUsername(String username) {
        List<String> groupNames = new ArrayList<>();
        Iterable<Groups> iterableGroup = groupRespository.findAll();
        List<Groups> groups = StreamSupport.stream(iterableGroup.spliterator(), false).collect(Collectors.toList());

        List<GroupMembers> groupMembers = groupMembersRespository.findAllByUsername(username);

        if (ObjectUtils.isNotEmpty(groupMembers)) {
            List<String> groupIds = groupMembers.stream()
                    .map(groupMember -> groupMember.getGroupId())
                    .collect(Collectors.toList());

            groupNames = groups.stream().filter(group -> groupIds.contains(group.getId())).map(group -> group.getGroupName()).toList();
        }
        return groupNames;
    }

}
