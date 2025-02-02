package com.majesty.pet_care.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majesty.pet_care.model.Role;
import com.majesty.pet_care.service.role.IRoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @GetMapping("/all-roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/role/get-by-id/role")
    public Role getRoleById(Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/role/get-by-name")
    public Role getRoleByName(String roleName) {
        return roleService.getRoleByName(roleName);
    }
}