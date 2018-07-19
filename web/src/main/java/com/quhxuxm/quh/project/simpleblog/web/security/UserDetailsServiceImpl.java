package com.quhxuxm.quh.project.simpleblog.web.security;

import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorAuthenticateDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service("simpleblogUserDetailsService")
class UserDetailsServiceImpl implements UserDetailsService {
    private IAuthorService authorService;

    UserDetailsServiceImpl(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(String token)
            throws UsernameNotFoundException {
        try {
            AuthorAuthenticateDTO authentication = authorService
                    .findForAuthenticate(token);
            if (authentication == null) {
                throw new UsernameNotFoundException(
                        "could not find the user '" + token + "'");
            }
            Collection<GrantedAuthority> authorities = authentication.getRoles()
                    .stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return new User(authentication.getToken(),
                    authentication.getPassword(), true, true, true, true,
                    authorities);
        } catch (ServiceException e) {
            throw new UsernameNotFoundException(
                    "can not find token because of exception.");
        }
    }
}
