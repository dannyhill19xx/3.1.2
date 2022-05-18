package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    List<User> getAllRoles();
    Role getRoleById(int id);
    Role getRoleByName(String name);
    void saveRole(Role role);
    void updateRole(Role updatedRole);
    void deleteRole(int id);
    Set<Role> getRolesSet(Set<String> roles);
}
