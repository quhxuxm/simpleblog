package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResourceRepository extends JpaRepository<Resource, Long> {
}
