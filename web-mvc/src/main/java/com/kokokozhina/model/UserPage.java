package com.kokokozhina.model;

public class UserPage {

    private String gitlabGroup;

    private String gitlabProject;

    private String slackChannel;

    public UserPage(String gitlabGroup, String gitlabProject, String slackChannel) {
        this.gitlabGroup = gitlabGroup;
        this.gitlabProject = gitlabProject;
        this.slackChannel = slackChannel;
    }

    public UserPage() {
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
