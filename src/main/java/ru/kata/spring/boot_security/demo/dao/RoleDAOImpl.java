package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllRoles() {
        return entityManager.createQuery("FROM Role").getResultList();
    }
    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT role FROM Role role  WHERE role.roleName = :role", Role.class);
        return query
                .setParameter("role", name)
                .setMaxResults(1)
                .getSingleResult();
    }
    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role updatedRole) {
        entityManager.merge(updatedRole);
    }

    @Override
    public void deleteRole(int id) {
        entityManager.createQuery("delete from Role role where role.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Set<Role> getRolesSet(Set<String> roles) {
        return new HashSet<>(entityManager.createQuery("select role from Role role where role.roleName = :role", Role.class)
                .setParameter("role", roles)
                .getResultList());
    }
}
