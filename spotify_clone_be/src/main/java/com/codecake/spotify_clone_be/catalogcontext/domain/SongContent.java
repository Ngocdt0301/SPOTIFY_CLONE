package com.codecake.spotify_clone_be.catalogcontext.domain;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "song_content")
public class SongContent implements Serializable {
    @Id
    @Column(name = "song_id")
    private Long song_id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    private Song song;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    public Long getSong_id() {
        return song_id;
    }

    public void setSong_id(Long song_id) {
        this.song_id = song_id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
