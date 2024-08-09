package com.codecake.spotify_clone_be.catalogcontext.application;

import com.codecake.spotify_clone_be.catalogcontext.application.dto.ReadSongInfoDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.dto.SaveSongDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.dto.SongContentDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.mapper.SongContentMapper;
import com.codecake.spotify_clone_be.catalogcontext.application.mapper.SongMapper;
import com.codecake.spotify_clone_be.catalogcontext.domain.Song;
import com.codecake.spotify_clone_be.catalogcontext.domain.SongContent;
import com.codecake.spotify_clone_be.catalogcontext.repository.SongContentRepository;
import com.codecake.spotify_clone_be.catalogcontext.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SongService {
    private final SongMapper songMapper;

    private final SongRepository songRepository;

    private final SongContentRepository songContentRepository;


    private final SongContentMapper songContentMapper;

    public SongService(SongMapper songMapper, SongRepository songRepository, SongContentRepository songContentRepository, SongContentMapper songContentMapper) {
        this.songMapper = songMapper;
        this.songRepository = songRepository;
        this.songContentRepository = songContentRepository;
        this.songContentMapper = songContentMapper;
    }
    public ReadSongInfoDTO create(SaveSongDTO saveSongDTO) {
        Song song = songMapper.saveSongDTOToSong(saveSongDTO);
        Song songSaved = songRepository.save(song);

        SongContent songContent = songContentMapper.saveSongDTOToSong(saveSongDTO);
        songContent.setSong(songSaved);

        songContentRepository.save(songContent);
        return songMapper.songToReadSongInfoDTO(songSaved);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<ReadSongInfoDTO> getAll() {

        List<ReadSongInfoDTO> allSongs = songRepository.findAll()
                .stream()
                .map(songMapper::songToReadSongInfoDTO)
                .toList();

        return allSongs;
    }

}
