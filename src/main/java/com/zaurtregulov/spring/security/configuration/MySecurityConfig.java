package com.zaurtregulov.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.AbstractCollection;
import java.util.List;

import static org.springframework.security.core.userdetails.User.*;

@EnableWebSecurity
@Configuration
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                        request.requestMatchers("/")
                                .hasAnyRole("EMPLOYEE","HR","MANAGER")
                                .requestMatchers("/hr_info").hasRole("HR")
                                .requestMatchers("/manager_info").hasRole("MANAGER").anyRequest().authenticated()
                ).formLogin()
                .defaultSuccessUrl("/")
                .and()
                .logout();
        return httpSecurity.build();
    }

@Bean
    protected UserDetailsService userDetailsService() throws Exception {

    return new InMemoryUserDetailsManager((User.withDefaultPasswordEncoder()
            .username("zaur").password("zaur").roles("EMPLOYEE")
            .build()), (User.withDefaultPasswordEncoder()
            .username("vladimir").password("vladimir").roles("HR")
            .build()), (User.withDefaultPasswordEncoder()
            .username("anna").password("anna").roles("MANAGER").build()));
    }
}
