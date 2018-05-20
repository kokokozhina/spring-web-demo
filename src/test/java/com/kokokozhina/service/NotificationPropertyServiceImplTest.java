package com.kokokozhina.service;

import com.kokokozhina.model.NotificationProperty;
import com.kokokozhina.repository.NotificationPropertyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class NotificationPropertyServiceImplTest {

    @MockBean
    private NotificationPropertyRepository notificationPropertyRepository;

    @InjectMocks
    private NotificationPropertyServiceImpl notificationPropertyService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getAllInStringsTest() {
        NotificationProperty notificationProperty = new NotificationProperty();
        notificationProperty.setGitlabGroup("gitlabGroup");
        notificationProperty.setGitlabProject("gitlabProject");
        notificationProperty.setSlackChannel("slackChannel");
        List<NotificationProperty> notificationPropertyList = new ArrayList<>();
        notificationPropertyList.add(notificationProperty);

        List<List<String>> propertyList = new ArrayList<>();
        List<String> aux = new ArrayList<>();
        aux.add("gitlabGroup");
        aux.add("gitlabProject");
        aux.add("slackChannel");
        propertyList.add(aux);

        when(notificationPropertyRepository.findAll()).thenReturn(notificationPropertyList);
        assertEquals(notificationPropertyService.getAllInStrings(), propertyList);

    }
}