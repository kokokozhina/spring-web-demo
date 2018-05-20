package com.kokokozhina.service;

import com.kokokozhina.model.NotificationProperty;
import com.kokokozhina.repository.NotificationPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationPropertyServiceImpl implements NotificationPropertyService {

    @Autowired
    private NotificationPropertyRepository notificationPropertyRepository;


    @Override
    public void save(NotificationProperty notificationProperty) {
        notificationPropertyRepository.save(notificationProperty);
    }

    @Override
    public List<NotificationProperty> getAll() {
        return notificationPropertyRepository.findAll();
    }

    @Override
    public List<List<String>> getAllInStrings() {
        return notificationPropertyRepository.findAll().stream()
                .map(s -> Arrays.asList(s.getGitlabGroup(), s.getGitlabProject(), s.getSlackChannel()))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationProperty findById(Long Id) {
        return notificationPropertyRepository.findById(Id);
    }

    @Override
    public NotificationProperty findByGitlabGroupAndGitlabProjectAndSlackChannel(String gitlabGroup, String gitlabProject, String slackChannel) {
        return notificationPropertyRepository.findByGitlabGroupAndGitlabProjectAndSlackChannel(gitlabGroup, gitlabProject, slackChannel);
    }

    @Override
    public void deleteById(Long Id) {
        notificationPropertyRepository.deleteById(Id);
    }


}
