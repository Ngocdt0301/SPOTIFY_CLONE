package com.codecake.spotify_clone_be.catalogcontext.application.mapper;

import com.codecake.spotify_clone_be.catalogcontext.application.dto.SaveSongDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.dto.SongContentDTO;
import com.codecake.spotify_clone_be.catalogcontext.domain.SongContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongContentMapper {

    @Mapping(source = "song.publicId", target = "publicId")
    SongContentDTO songContentToSongContentDTO(SongContent songContent);

    SongContent saveSongDTOToSong(SaveSongDTO songDTO);
}