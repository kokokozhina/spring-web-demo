package com.kokokozhina.repository;


import com.kokokozhina.model.NotificationProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationPropertyRepository extends JpaRepository<NotificationProperty, Long> {
    NotificationProperty findById(Long id);

    NotificationProperty findByGitlabGroupAndGitlabProjectAndSlackChannel(String gitlabGroup,
                                                                          String gitlabProject, String slackChannel);

    void deleteById(Long id);
}
