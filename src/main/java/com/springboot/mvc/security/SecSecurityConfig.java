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
            .withUser("aretha.sydney")
                .password(passwordEncoder().encode("aretha.sydney"))
                .roles("USER").and()
            .withUser("michael.hall")
                .password(passwordEncoder().encode("michael.hall"))
                .roles("USER").and()
            .withUser("beder.butka@gmail.com")
                .password(passwordEncoder().encode("beder.butka"))
                .roles("USER", "ADMIN").and()
            .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/index").hasRole("ADMIN")
            .anyRequest().authenticated().and()
            .formLogin().loginPage("/login").permitAll()
            .loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/index", true)
            .failureUrl("/login?error=true");
//            .failureHandler(authenticationFailureHandler()).and()
//            .logout().logoutUrl("/perform_logout")
//            .deleteCookies("JSESSIONID")
//            .logoutSuccessHandler(logoutSuccessHandler());
    //            .antMatchers("/admin/**").hasRole("ADMIN")
    //            .antMatchers("/anonymous*").anonymous()
    //            .antMatchers("/login*").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
