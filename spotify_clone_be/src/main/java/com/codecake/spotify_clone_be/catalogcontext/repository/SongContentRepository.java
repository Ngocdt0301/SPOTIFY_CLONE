package com.codecake.spotify_clone_be.catalogcontext.repository;

import com.codecake.spotify_clone_be.catalogcontext.domain.SongContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongContentRepository extends JpaRepository<SongContent, Long> {
}
