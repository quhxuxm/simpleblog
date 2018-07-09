package com.quhxuxm.quh.project.simpleblog.repository;
import com.quhxuxm.quh.project.simpleblog.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
