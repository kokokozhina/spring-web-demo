package com.kokokozhina.service;

import com.kokokozhina.model.Role;
import com.kokokozhina.model.User;
import com.kokokozhina.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class UserDetailsServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setRole(Role.USER);

        Set<GrantedAuthority> grantedAuthorityList = new HashSet<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(Role.USER.toString()));

        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        assertEquals(userDetails.getUsername(), user.getUsername());
        assertEquals(userDetails.getAuthorities(), grantedAuthorityList);
    }
}