package com.codecake.spotify_clone_be.catalogcontext.application.dto;

import jakarta.persistence.Lob;

import java.util.UUID;

public record SongContentDTO(UUID publicID, @Lob byte[] file, String fileContentType) {
}
