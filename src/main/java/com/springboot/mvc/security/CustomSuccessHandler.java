package com.springboot.mvc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
//        // fixme -> spring security doesn't grant authorities
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
//            System.out.println("User principal name =" + userPrincipal.getUsername());
//            System.out.println("Is user enabled =" + userPrincipal.isEnabled());
//        }

        String redirectUrl = null;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            String authority = grantedAuthority.getAuthority();
            System.out.println("role " + authority);
            if (authority.equals("USER") || authority.equals("ADMIN")) {
                redirectUrl = "/";
                break;
            }
        }
        System.out.println("redirectUrl: " + redirectUrl);
        if (redirectUrl == null) throw new IllegalStateException();
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}