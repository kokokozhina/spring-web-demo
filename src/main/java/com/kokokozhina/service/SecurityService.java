package com.kokokozhina.service;

/**
 * Service that deals with security
 */
public interface SecurityService {

    /**
     * Return username of a logged in user
     * @return username of a logged in user and null otherwise
     */
    String findLoggedInUsername();

    /**
     * Login into system using username and password
     * @param username username
     * @param password password
     */
    void autoLogin(String username, String password);
}
