package com.codecake.spotify_clone_be.userContext.repository;

import com.codecake.spotify_clone_be.userContext.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

}