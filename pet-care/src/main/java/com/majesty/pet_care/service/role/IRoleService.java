package com.majesty.pet_care.service.role;

import java.util.List;
import java.util.Set;

import com.majesty.pet_care.model.Role;

public interface IRoleService {
    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleByName(String roleName);

    void saveRole(Role role);

    Set<Role> setUserRole(String userType);

    // Collection<Role> setUserRoles(List<String> roles);

}
