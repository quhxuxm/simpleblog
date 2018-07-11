package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepository
        extends JpaRepository<Authentication, Long> {
    Authentication findByTokenAndType(String token, Authentication.Type type);

    Authentication findByTokenAndPasswordAndType(String token, String password,
            Authentication.Type type);
}
