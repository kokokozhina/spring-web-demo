package com.kokokozhina.model;

import javax.persistence.*;

@Entity
@Table(name = "NotificationProperty")
public class NotificationProperty {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "gitlabGroup")
    private String gitlabGroup;

    @Column(name = "gitlabProject")
    private String gitlabProject;

    @Column(name = "slackChannel")
    private String slackChannel;

    public NotificationProperty(String gitlabGroup, String gitlabProject, String slackChannel) {
        this.gitlabGroup = gitlabGroup;
        this.gitlabProject = gitlabProject;
        this.slackChannel = slackChannel;
    }

    public NotificationProperty() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGitlabGroup() {
        return gitlabGroup;
    }

    public void setGitlabGroup(String gitlabGroup) {
        this.gitlabGroup = gitlabGroup;
    }

    public String getGitlabProject() {
        return gitlabProject;
    }

    public void setGitlabProject(String gitlabProject) {
        this.gitlabProject = gitlabProject;
    }

    public String getSlackChannel() {
        return slackChannel;
    }

    public void setSlackChannel(String slackChannel) {
        this.slackChannel = slackChannel;
    }
}
