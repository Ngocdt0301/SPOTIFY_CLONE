package com.codecake.spotify_clone_be.catalogcontext.repository;

import com.codecake.spotify_clone_be.catalogcontext.domain.Favourite;
import com.codecake.spotify_clone_be.catalogcontext.domain.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
    List<Favourite> findAllByUserEmailAndSongPublicIdIn(String email, List<UUID> songPublicIds);
}
