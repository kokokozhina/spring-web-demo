package com.kokokozhina.service;

import com.kokokozhina.model.User;
import com.kokokozhina.repository.UserRepository;
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
public class UserServiceImplTest {
    //not all methods were tested (save, updateRoleByUsername)

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void findByUsernameTest() {
        User user = new User();
        user.setUsername("mary");
        when(userRepository.findUserByUsername(user.getUsername()))
                .thenReturn(user);
        assertEquals(user, userService.findByUsername(user.getUsername()));
    }

    @Test
    public void getAllTest() {
        User user = new User();
        user.setUsername("mary");
        List<User> list = new ArrayList<>();
        list.add(user);
        when(userRepository.findAll()).thenReturn(list);
        assertEquals(list, userService.getAll());
    }

}