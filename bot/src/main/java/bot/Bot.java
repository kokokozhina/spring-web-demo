package bot;

import git.client.api.GitClientApi;
import gitlab.client.impl.GitlabClientImpl;
import msgr.client.api.MsgrClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class Bot {

    @Autowired
    private GitClientApi gitClientApi;

    @Autowired
    private MsgrClientApi msgrClientApi;

    public List<String> getGroups() throws IOException {
        return gitClientApi.getGroups();
    }

    public List<String> getProjectsByGroupName(String group) throws IOException {
        return gitClientApi.getProjectsByGroupName(group);
    }

    public List<String> getChannels() throws IOException {
        return msgrClientApi.getChannels();
    }

//    @Scheduled(fixedDelay = 10000)
    public void notifyOfMergeRequests(List<List<String>> properties) throws IOException {
        for (List<String> property : properties) {
            List<String> messages = gitClientApi.getMrsByGroupAndProject(property.get(0), property.get(1));
            for (String message : messages) {
                msgrClientApi.postToSlackByChannelName(message, property.get(2));
            }

        }
//        msgrClientApi.postToSlackChannels(gitClientApi.getMergeRequests());
    }

}