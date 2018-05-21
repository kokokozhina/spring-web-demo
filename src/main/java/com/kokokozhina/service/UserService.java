package com.kokokozhina.service;

import com.kokokozhina.model.User;
import java.util.List;

/**
 * Service that is working with users
 */
public interface UserService {

    /**
     * Save user to UserRepository
     * @param user An instance of User to save
     */
    void save(User user);

    /**
     * Find user by his username
     * @param username username of user to find
     * @return {@code User} if user was found or null otherwise
     */
    User findByUsername(String username);


    /**
     * Get all users from UserRepository
     * @return {@code List<User>} is a list of users in UserRepository
     */
    List<User> getAll();

    /**
     * Update role of user by username
     * @param username a username of user to update
     * @param role a new role
     */
    void updateRoleByUsername(String username, String role);
}
