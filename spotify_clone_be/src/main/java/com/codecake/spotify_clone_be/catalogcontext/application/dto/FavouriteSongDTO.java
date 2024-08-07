package com.codecake.spotify_clone_be.catalogcontext.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FavouriteSongDTO(@NotNull boolean favourite,@NotNull UUID publicId ) {
}
