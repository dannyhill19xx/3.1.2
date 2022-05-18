package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDao;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    public List<User> getAllRoles(){
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String roleName){
        return roleDao.getRoleByName(roleName);
    }
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public void updateRole(Role updatedRole) {
        roleDao.updateRole(updatedRole);
    }

    @Override
    public void deleteRole(int id) {
        roleDao.deleteRole(id);
    }

    @Override
    public Set<Role> getRolesSet(Set<String> roles) {
        return roleDao.getRolesSet(roles);
    }

}