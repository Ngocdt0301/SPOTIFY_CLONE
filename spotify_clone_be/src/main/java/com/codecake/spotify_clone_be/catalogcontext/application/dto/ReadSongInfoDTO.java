package com.codecake.spotify_clone_be.catalogcontext.application.dto;

import com.codecake.spotify_clone_be.catalogcontext.application.vo.SongAuthorVO;
import com.codecake.spotify_clone_be.catalogcontext.application.vo.SongTitleVO;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ReadSongInfoDTO {
    private SongTitleVO songTitleVO;

    private SongAuthorVO songAuthorVO;

    @NotNull
    private byte[] cover;

    @NotNull
    private String coverContentType;
    @NotNull
    private boolean isFavourite;
    @NotNull
    private UUID publicId;

    public SongTitleVO getSongTitleVO() {
        return songTitleVO;
    }

    public void setSongTitleVO(SongTitleVO songTitleVO) {
        this.songTitleVO = songTitleVO;
    }

    public SongAuthorVO getSongAuthorVO() {
        return songAuthorVO;
    }

    public void setSongAuthorVO(SongAuthorVO songAuthorVO) {
        this.songAuthorVO = songAuthorVO;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public String getCoverContentType() {
        return coverContentType;
    }

    public void setCoverContentType(String coverContentType) {
        this.coverContentType = coverContentType;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }
}
