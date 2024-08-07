package com.codecake.spotify_clone_be.catalogcontext.application.dto;

import com.codecake.spotify_clone_be.catalogcontext.application.vo.SongAuthorVO;
import com.codecake.spotify_clone_be.catalogcontext.application.vo.SongTitleVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SaveSongDTO (@Valid SongTitleVO songTitleVO,
                           @Valid SongAuthorVO songAuthorVO,
                           @NotNull byte[] cover,
                           @NotNull String coverContentType,
                           @NotNull byte[] file,
                           @NotNull String fileContentType
                           ){
}
