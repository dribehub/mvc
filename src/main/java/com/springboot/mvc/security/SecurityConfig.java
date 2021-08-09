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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("aretha.sydney@gmail.com")
                .password(passwordEncoder().encode("aretha.sydney"))
                .roles("user").and()
            .withUser("michael.hall@gmail.com")
                .password(passwordEncoder().encode("michael.hall"))
                .roles("user").and()
            .withUser("david.reed@gmail.com")
                .password(passwordEncoder().encode("david.reed"))
                .roles("user").and()
            .withUser("linda.baker@gmail.com")
                .password(passwordEncoder().encode("linda.baker"))
                .roles("user").and()
            .withUser("william.ford@gmail.com")
                .password(passwordEncoder().encode("william.ford"))
                .roles("user").and()
            .withUser("beder.butka@gmail.com")
                .password(passwordEncoder().encode("beder.butka"))
                .roles("user", "admin");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").hasRole("admin")
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated().and()
            .formLogin().loginPage("/signup").permitAll();
//            .and().logout().logoutUrl("/logout").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
