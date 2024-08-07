package com.codecake.spotify_clone_be.catalogcontext.repository;

import com.codecake.spotify_clone_be.catalogcontext.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
