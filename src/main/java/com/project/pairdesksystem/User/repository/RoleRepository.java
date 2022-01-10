package com.project.pairdesksystem.User.repository;

import java.util.Optional;

import com.project.pairdesksystem.User.models.ERole;
import com.project.pairdesksystem.User.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}