package com.project.pairdesksystem.User.repository;

import java.util.Optional;

import com.project.pairdesksystem.User.models.User;
import com.project.pairdesksystem.User.models.UserDTO;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUserId(long userId);
}