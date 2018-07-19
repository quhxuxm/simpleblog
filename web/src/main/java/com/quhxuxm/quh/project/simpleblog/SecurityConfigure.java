package com.quhxuxm.quh.project.simpleblog;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    public SecurityConfigure(
            @Qualifier("simpleblogUserDetailsService")
                    UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests()
                .antMatchers("/article/create", "/article/update",
                        "/article/praise", "/article/bookmark",
                        "/article/comment/create", "/anthology/create",
                        "/anthology/update", "/anthology/bookmark",
                        "/anthology/praise", "/anthology/comment/create",
                        "/author/update", "/author/follow").authenticated();
        http.formLogin().loginPage("/findForAuthenticate");
        http.logout().logoutUrl("/logout");
        http.rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }
}
