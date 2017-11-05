package com.tongwen.web.security;

import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.dto.AuthorSecurityDTO;
import com.tongwen.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory
            .getLogger(AuthorDetailsService.class);
    private final IAuthorService authorService;

    public AuthorDetailsService(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        AuthorSecurityDTO authorSecurityDTO = null;
        try {
            authorSecurityDTO = this.authorService.loadByEmailForSecurityCheck(email);
        } catch (ServiceException e) {
            throw new UsernameNotFoundException(
                    "Fail to load author from database because of system exception.",
                    e);
        }
        if (authorSecurityDTO == null) {
            throw new UsernameNotFoundException(
                    "Can not find author with email: " + email);
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String role : authorSecurityDTO.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
            logger.debug("Adding role: " + role + " to author[" + email + "]");
        }
        logger.debug(
                "Success to load author from database to security context.");
        return new User(authorSecurityDTO.getEmail(),
                authorSecurityDTO.getPassword(), authorities);
    }
}
