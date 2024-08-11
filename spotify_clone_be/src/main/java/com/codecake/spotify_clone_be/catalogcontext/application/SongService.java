package com.codecake.spotify_clone_be.catalogcontext.application;

import com.codecake.spotify_clone_be.catalogcontext.application.dto.FavouriteSongDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.dto.ReadSongInfoDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.dto.SaveSongDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.dto.SongContentDTO;
import com.codecake.spotify_clone_be.catalogcontext.application.mapper.SongContentMapper;
import com.codecake.spotify_clone_be.catalogcontext.application.mapper.SongMapper;
import com.codecake.spotify_clone_be.catalogcontext.domain.Favourite;
import com.codecake.spotify_clone_be.catalogcontext.domain.FavouriteId;
import com.codecake.spotify_clone_be.catalogcontext.domain.Song;
import com.codecake.spotify_clone_be.catalogcontext.domain.SongContent;
import com.codecake.spotify_clone_be.catalogcontext.repository.FavouriteRepository;
import com.codecake.spotify_clone_be.catalogcontext.repository.SongContentRepository;
import com.codecake.spotify_clone_be.catalogcontext.repository.SongRepository;
import com.codecake.spotify_clone_be.infrastructure.config.service.dto.State;
import com.codecake.spotify_clone_be.infrastructure.config.service.dto.StateBuilder;
import com.codecake.spotify_clone_be.usercontext.ReadUserDTO;
import com.codecake.spotify_clone_be.usercontext.application.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SongService {
    private final SongMapper songMapper;

    private final SongRepository songRepository;

    private final SongContentRepository songContentRepository;
    private final FavouriteRepository favouriteRepository;

    private final UserService userService;
    private final SongContentMapper songContentMapper;

    public SongService(SongMapper songMapper, SongRepository songRepository, SongContentRepository songContentRepository, FavouriteRepository favouriteRepository, UserService userService, SongContentMapper songContentMapper) {
        this.songMapper = songMapper;
        this.songRepository = songRepository;
        this.songContentRepository = songContentRepository;
        this.favouriteRepository = favouriteRepository;
        this.userService = userService;
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
    public Optional<SongContentDTO> getOneByPublicId(UUID publicId) {
        Optional<SongContent> songByPublicId = songContentRepository.findOneBySongPublicId(publicId);
        return songByPublicId.map(songContentMapper::songContentToSongContentDTO);
    }

    public List<ReadSongInfoDTO> search(String searchTerm) {
        List<ReadSongInfoDTO> searchedSongs = songRepository.findByTitleOrAuthorContaining(searchTerm)
                .stream()
                .map(songMapper::songToReadSongInfoDTO)
                .collect(Collectors.toList());

        if(userService.isAuthenticated()) {
            return fetchFavoritesStatusForSongs(searchedSongs);
        } else {
            return searchedSongs;
        }
    }

    public State<FavouriteSongDTO, String> addOrRemoveFromFavorite(FavouriteSongDTO favoriteSongDTO, String email) {
        StateBuilder<FavouriteSongDTO, String> builder = State.builder();
        Optional<Song> songToLikeOpt = songRepository.findOneByPublicId(favoriteSongDTO.publicId());
        if (songToLikeOpt.isEmpty()) {
            return builder.forError("Song public id doesn't exist").build();
        }

        Song songToLike = songToLikeOpt.get();

        ReadUserDTO userWhoLikedSong = userService.getByEmail(email).orElseThrow();

        if (favoriteSongDTO.favourite()) {
            Favourite favorite = new Favourite();
            favorite.setSongPublicId(songToLike.getPublicId());
            favorite.setUserEmail(userWhoLikedSong.email());
            favouriteRepository.save(favorite);
        } else {
            FavouriteId favoriteId = new FavouriteId(songToLike.getPublicId(), userWhoLikedSong.email());
            favouriteRepository.deleteById(favoriteId);
            favoriteSongDTO = new FavouriteSongDTO(false, songToLike.getPublicId());
        }

        return builder.forSuccess(favoriteSongDTO).build();
    }
//
    public List<ReadSongInfoDTO> fetchFavoriteSongs(String email) {
        return songRepository.findAllFavoriteByUserEmail(email)
                .stream()
                .map(songMapper::songToReadSongInfoDTO)
                .toList();
    }

    private List<ReadSongInfoDTO> fetchFavoritesStatusForSongs(List<ReadSongInfoDTO> songs) {
        ReadUserDTO authenticatedUser = userService.getAuthenticatedUserFromSecurityContext();

        List<UUID> songPublicIds = songs.stream().map(ReadSongInfoDTO::getPublicId).toList();

        List<UUID> userFavoriteSongs = favouriteRepository.findAllByUserEmailAndSongPublicIdIn(authenticatedUser.email(), songPublicIds)
                .stream().map(Favourite::getSongPublicId).toList();

        return songs.stream().peek(song -> {
            if (userFavoriteSongs.contains(song.getPublicId())) {
                song.setFavourite(true);
            }
        }).toList();
    }
}
