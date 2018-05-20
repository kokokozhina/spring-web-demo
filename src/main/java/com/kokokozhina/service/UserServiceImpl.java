package com.kokokozhina.service;

import com.kokokozhina.model.Role;
import com.kokokozhina.model.User;
import com.kokokozhina.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.UNCHECKED);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateRoleByUsername(String username, String role) {
        User user = findByUsername(username);
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
    }

    @PostConstruct
    public void addFirstAdmin() {
        User user = new User();
        if (userRepository.findUserByUsername("root") == null) {
            user.setUsername("root");
            user.setPassword(bCryptPasswordEncoder.encode("password"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }


}
