package com.jwz.config;


import org.apache.commons.io.input.ReaderInputStream;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**", "/login.html", "/font/**", "/*.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/index.html", "/html/**").hasRole("admin");
        http.rememberMe();
        http.formLogin();
        http.logout().logoutSuccessUrl("/login");
        http.headers().frameOptions().disable();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("admin.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(classPathResource.getFile()));
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser(username).password(new BCryptPasswordEncoder().encode(password)).roles("admin");
    }
}
