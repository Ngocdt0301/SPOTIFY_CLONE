package com.codecake.spotify_clone_be.catalogcontext.repository;

import com.codecake.spotify_clone_be.catalogcontext.domain.Favourite;
import com.codecake.spotify_clone_be.catalogcontext.domain.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
}
