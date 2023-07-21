package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface UserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    void addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    List<User> getAllUser();

    User getById(int id);
    User getByUsername(String name);

}
