package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleService roleService;
    private final ApplicationContext context;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleService roleService, ApplicationContext context) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.context = context;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        setEncryptedPassword(user);
        userDAO.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User updatedUser) {
        setEncryptedPassword(updatedUser);
        userDAO.updateUser(updatedUser);
    }


    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }


    @Override
    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public void setEncryptedPassword(User user) {
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user =  userDAO.findUserByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not found!", email));
        }
        return user;
    }
}
