package com.kokokozhina.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       // auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
       // auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
       // auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/welcome","/registration", "/login", "/console/**").permitAll()
            .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/admin/**").hasAuthority("ADMIN")
//            .anyRequest().authenticated();
            .and()
            .logout()
            .permitAll();
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll();
        http.csrf().disable(); //needed for h2 /console
        http.headers().frameOptions().disable(); //needed for h2 /console
    }

}


