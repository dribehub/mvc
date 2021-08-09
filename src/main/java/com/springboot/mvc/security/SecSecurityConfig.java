package com.springboot.mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("sydney@gmail.com")
                .password(passwordEncoder().encode("aretha"))
                .roles("user").and()
            .withUser("hall@gmail.com")
                .password(passwordEncoder().encode("michael"))
                .roles("user").and()
            .withUser("reed@gmail.com")
                .password(passwordEncoder().encode("david"))
                .roles("user").and()
            .withUser("baker@gmail.com")
                .password(passwordEncoder().encode("linda"))
                .roles("user").and()
            .withUser("ford@gmail.com")
                .password(passwordEncoder().encode("william"))
                .roles("user").and()
            .withUser("butka@gmail.com")
                .password(passwordEncoder().encode("beder"))
                .roles("user", "admin");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated().and()
            .formLogin().loginPage("/signup").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
