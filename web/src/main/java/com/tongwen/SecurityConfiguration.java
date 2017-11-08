package com.tongwen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService,
            AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/register", "/login").and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/fonts/**", "/js/**", "/image/**",
                        "/login", "/index", "/api/**", "/register",
                        "/dimage/**",
                        "/article/allPublishedArticleSummariesCollection",
                        "/article/anthologyArticleSummariesCollection/**",
                        "/article/*/view", "/author/**", "/anthology/*/view",
                        "/error").permitAll().antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')").anyRequest()
                .access("hasRole('ROLE_AUTHOR') and isAuthenticated()").and()
                .formLogin().loginPage("/login")
                .successHandler(this.authenticationSuccessHandler).and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").clearAuthentication(true)
                .invalidateHttpSession(true).and().securityContext()
                .securityContextRepository(
                        new HttpSessionSecurityContextRepository());
    }
}
