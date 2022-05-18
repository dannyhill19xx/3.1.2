package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class FirstStartDBService implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public FirstStartDBService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.saveRole(role1);
        roleService.saveRole(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser1 = new HashSet<>();
        Set<Role> roleUser2 = new HashSet<>();

        roleAdmin.add(role1);
        roleUser1.add(role2);
        roleUser2.add(role2);

        User user1 = new User("Evgenii", "Tomilko", 29,"admin@mail.ru", "admin",roleAdmin);
        User user2 = new User("Ivan", "Ivanov", 21,"user1@mail.ru", "user",roleUser1);
        User user3 = new User("Petr", "Petrov", 25,"user2@mail.ru", "user",roleUser2);

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}