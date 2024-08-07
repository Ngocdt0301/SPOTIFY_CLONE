package com.codecake.spotify_clone_be.catalogcontext.application.vo;

import jakarta.validation.constraints.NotBlank;

public record SongTitleVO(@NotBlank String value) {
}
