package com.tongwen.web.security;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Role;
import com.tongwen.service.api.IAuthenticationService;
import com.tongwen.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorDetailsService.class);
    private final IAuthenticationService authenticationService;

    public AuthorDetailsService(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Authentication authentication = null;
        try {
            authentication =
                this.authenticationService.authenticate(email, Authentication.Type.EMAIL);
        } catch (ServiceException e) {
            throw new UsernameNotFoundException(
                "Fail to load author from database because of system exception.", e);
        }
        if (authentication == null) {
            throw new UsernameNotFoundException("Can not find author with email: " + email);
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : authentication.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.debug("Adding role: " + role + " to author[" + email + "]");
        }
        logger.debug("Success to load author from database to security context.");
        return new AuthorDetails(authentication.getToken(), authentication.getPassword(),
            authorities, authentication.getId());
    }
}
