package com.kokokozhina.service;

import com.kokokozhina.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> getAll();

    void updateRoleByUsername(String username, String role);
}
