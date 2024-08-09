package com.codecake.spotify_clone_be.usercontext.repository;

import com.codecake.spotify_clone_be.usercontext.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

}