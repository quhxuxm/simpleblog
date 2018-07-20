package com.quhxuxm.quh.project.simpleblog;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
@EnableGlobalAuthentication
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    public SecurityConfigure(
            @Qualifier("UserDetailsServiceImpl")
                    UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/article/create", "/article/update",
                        "/article/praise", "/article/bookmark",
                        "/article/comment/create", "/anthology/create",
                        "/anthology/update", "/anthology/bookmark",
                        "/anthology/praise", "/anthology/comment/create",
                        "/author/update", "/author/follow",
                        "/author/initialize")
                .authenticated()
                .anyRequest().permitAll();
        http.formLogin().loginPage("/authenticate")
                .successForwardUrl("/author/initialize");
        http.logout().logoutUrl("/logout");
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }
}
