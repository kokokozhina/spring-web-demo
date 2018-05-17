package com.kokokozhina.service;

import com.kokokozhina.model.User;
import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> getAll();

    void updateRoleByUsername(String username, String role);
}
