package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public    List<User> getAllRoles();
    Role getRoleById(int id);
    Role getRoleByName(String roleName);
    void saveRole(Role role);
    void updateRole(Role updatedRole);
    void deleteRole(int id);
    Set<Role> getRolesSet(Set<String> roles);
}