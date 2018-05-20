package com.kokokozhina.service;

import com.kokokozhina.model.NotificationProperty;

import java.util.List;

public interface NotificationPropertyService {
    void save(NotificationProperty notificationProperty);

    List<NotificationProperty> getAll();

    List<List<String>> getAllInStrings();

    NotificationProperty findById(Long  id);

    NotificationProperty findByGitlabGroupAndGitlabProjectAndSlackChannel(String gitlabGroup,
                                                                          String gitlabProject,
                                                                          String slackChannel);


    void deleteById(Long Id);

}
