package com.kokokozhina.repository;

import com.kokokozhina.model.NotificationProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
public class NotificationPropertyRepositoryTest {

//    @Autowired
//    private NotificationPropertyRepository notificationPropertyRepository;
//
//    private NotificationProperty notificationProperty;
//
//    @Before
//    public void setUp() throws Exception {
//
//
//        notificationProperty = new NotificationProperty();
//        notificationProperty.setId(1L);
//        notificationProperty.setGitlabGroup("test");
//        notificationProperty.setSlackChannel("test");
//        notificationProperty.setGitlabProject("test");
////        notificationPropertyRepository.save(notificationProperty);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void findById() {
//        NotificationProperty notificationProperty1 =
//                notificationPropertyRepository.findById(this.notificationProperty.getId());
//        assertEquals(this.notificationProperty.getId(), notificationProperty1.getId());
//        assertEquals(this.notificationProperty.getGitlabGroup(), notificationProperty1.getGitlabGroup());
//        assertEquals(this.notificationProperty.getGitlabProject(), notificationProperty1.getGitlabProject());
//        assertEquals(this.notificationProperty.getSlackChannel(), notificationProperty1.getSlackChannel());
//    }
//
//    @Test
//    public void deleteById() {
//        notificationPropertyRepository.deleteById(1L);
//        for (NotificationProperty notificationProperty1 : notificationPropertyRepository.findAll()) {
//            assertEquals(this.notificationProperty.getId(), notificationProperty1.getId());
//        }
//    }
}