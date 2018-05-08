package com.kokokozhina.service;

import com.kokokozhina.dao.UserDao;
import com.kokokozhina.model.Role;
import com.kokokozhina.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ANONYMOUS);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findUserByUsername(username);
    }



}
