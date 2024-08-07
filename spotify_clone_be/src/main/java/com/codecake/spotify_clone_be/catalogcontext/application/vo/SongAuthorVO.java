package com.codecake.spotify_clone_be.catalogcontext.application;

import jakarta.validation.constraints.NotBlank;

public record SongAuthor (@NotBlank String value){

}
