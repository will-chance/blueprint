package cn.will.po;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created on 2018-01-13 1:03 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class Music {
    private int id;

    private ImageView favorite = new ImageView(new Image("img/unfavorite16.png"));

    private String title;

    private String artist;

    private String album;

    private String duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ImageView getFavorite() {
        return favorite;
    }

}
