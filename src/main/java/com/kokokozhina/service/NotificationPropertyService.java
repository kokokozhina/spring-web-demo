package com.kokokozhina.service;

import com.kokokozhina.model.NotificationProperty;

import java.util.List;

/**
 * Service that is working with Notification Properties
 */
public interface NotificationPropertyService {

    /**
     * Save NotificationProperty to NotificationPropertyRepository
     * @param notificationProperty a notification property to save
     */
    void save(NotificationProperty notificationProperty);


    /**
     * Get all notification properties from NotificationPropertyRepository
     * @return {@code List<NotificationProperty>} a list of notification properties from NotificationPropertyRepository
     */
    List<NotificationProperty> getAll();


    /**
     * Return information about notification properties in list of strings
     * where the first string is a title of git group
     * the second string is a title of git project
     * the third is a messenger channel name
     * @return
     */
    List<List<String>> getAllInStrings();


    /**
     * Return a notification property with a certain id
     * @param id id of a notification property to be found
     * @return {@code NotificationProperty} if it was found and null otherwise
     */
    NotificationProperty findById(Long  id);


    /**
     * Return a notification property with certain parameters
     * @param gitlabGroup title of a gitlabGroup
     * @param gitlabProject title of a gitlabProject
     * @param slackChannel title of a slackChannel
     * @return
     */
    NotificationProperty findByGitlabGroupAndGitlabProjectAndSlackChannel(String gitlabGroup,
                                                                          String gitlabProject,
                                                                          String slackChannel);


    /**
     * Delete  a notification property by id
     * @param Id id of a notification property to be deleted
     */
    void deleteById(Long Id);

}
