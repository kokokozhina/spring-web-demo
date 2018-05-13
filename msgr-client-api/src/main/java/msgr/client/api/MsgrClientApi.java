package msgr.client.api;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

public interface MsgrClientApi {
    void postToSlackChannels(List<String> messages) throws IOException;

    List<String> getChannels() throws IOException;

    void postToSlackByChannelName(String message, String s) throws IOException;
}
