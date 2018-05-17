package com.kokokozhina;

import bot.Bot;
import com.kokokozhina.service.NotificationPropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;


@SpringBootApplication
@ComponentScan({"com.kokokozhina", "bot", "gitlab.client.impl", "git.client.api", "slack.impl", "msgr.client.api"})
@EnableScheduling
public class WebApplication extends SpringBootServletInitializer {

    @Autowired
    private Bot bot;

    @Autowired
    NotificationPropertyServiceImpl notificationPropertyService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    @Scheduled(fixedDelay = 5000)
    public void notifyOfMergeRequests() throws IOException {
        bot.notifyOfMergeRequests(notificationPropertyService.getAllInStrings());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
}
