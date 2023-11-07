package com.txt.security.registration.service;

import java.util.List;

public interface GroupService {

    List<String> getRolesByUserGroups(String username);

}
