package cn.will.po;

import java.util.Objects;

/**
 * Created on 2018-01-14 12:12 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class AlbumMusic {
    private int musicId;

    private int albumId;

    public AlbumMusic() {
    }

    public AlbumMusic(int musicId, int albumId) {
        this.musicId = musicId;
        this.albumId = albumId;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumMusic that = (AlbumMusic) o;
        return musicId == that.musicId &&
                albumId == that.albumId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(musicId, albumId);
    }
}
