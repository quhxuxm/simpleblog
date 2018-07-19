package com.quhxuxm.quh.project.simpleblog;

import com.quhxuxm.quh.project.simpleblog.web.filter.InitializeAuthenticatedAuthorDetailHolderFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
@EnableGlobalAuthentication
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfigure(
            @Qualifier("UserDetailsServiceImpl")
                    UserDetailsService userDetailsService,
            AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/article/create", "/article/update",
                        "/article/praise", "/article/bookmark",
                        "/article/comment/create", "/anthology/create",
                        "/anthology/update", "/anthology/bookmark",
                        "/anthology/praise", "/anthology/comment/create",
                        "/author/update", "/author/follow").authenticated()
                .anyRequest().permitAll();
        http.formLogin().loginPage("/authenticate")
                .successHandler(this.authenticationSuccessHandler);
        http.logout().logoutUrl("/logout");
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }

    @Bean
    public FilterRegistrationBean<Filter> securityFilterChain(@Qualifier(
            AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME)
                                                                      Filter securityFilter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(
                securityFilter);
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.setName(
                AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<Filter> initializeAuthenticatedAuthorDetailHolderFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        InitializeAuthenticatedAuthorDetailHolderFilter initializeAuthenticatedAuthorDetailHolderFilter = new InitializeAuthenticatedAuthorDetailHolderFilter();
        registrationBean
                .setFilter(initializeAuthenticatedAuthorDetailHolderFilter);
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }
}
