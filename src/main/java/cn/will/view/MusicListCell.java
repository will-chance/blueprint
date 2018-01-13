package cn.will.view;

import cn.will.po.Music;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Created on 2018-01-13 7:42 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class MusicListCell {
    private ImageView favoriteView = new ImageView(new Image("img/unfavorite16.png"));

    private ImageView unfavoriteView = new ImageView(new Image("img/favorite16.png"));

    private Label favoriteIcon;

    private boolean favorite;

    private Text id;

    private Text title;

    private Text artist;

    private Text album;

    private Text duration;

    public MusicListCell() {

    }

    public MusicListCell(Music music, boolean favorite) {
        this.id = new Text(String.valueOf(music.getId()));
        this.title = new Text(music.getTitle());
        this.artist = new Text(music.getArtist());
        this.album = new Text(music.getAlbum());
        this.duration = new Text(music.getDuration());
        this.favorite = favorite;
        initFavoriteIcon(favorite);
        setHoverCursor(artist);
        setHoverCursor(album);
        setHoverCursor(favoriteIcon);
        initFavoriteAction();
    }

    private void initFavoriteIcon(boolean favorite){
        favoriteIcon = new Label();
        if (!favorite){
            favoriteIcon.setGraphic(favoriteView);
        } else {
            favoriteIcon.setGraphic(unfavoriteView);
        }
    }


    private void setHoverCursor(Text text){
        if (null == text) return;
        text.setOnMouseEntered(e->{
            text.getScene().setCursor(Cursor.HAND);
        });
        text.setOnMouseExited(e->{
            text.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    private void setHoverCursor(Label label){
        if (null == label) return;
        label.setOnMouseEntered(e->{
            label.getScene().setCursor(Cursor.HAND);
        });
        label.setOnMouseExited(e->{
            label.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    private void setFavoriteIcon(boolean favorite){
        if (favorite){
            favoriteIcon.setGraphic(favoriteView);
        } else {
            favoriteIcon.setGraphic(unfavoriteView);
        }
    }

    private void initFavoriteAction(){
        favoriteIcon.setOnMouseClicked(e -> {
            setFavoriteIcon(favorite);
            favorite = !favorite;
        });
    }

    public Label getFavoriteIcon() {
        return favoriteIcon;
    }

    public void setFavoriteIcon(Label favoriteIcon) {
        this.favoriteIcon = favoriteIcon;
    }

    public Text getId() {
        return id;
    }

    public void setId(Text id) {
        this.id = id;
    }

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getArtist() {
        return artist;
    }

    public void setArtist(Text artist) {
        this.artist = artist;
    }

    public Text getAlbum() {
        return album;
    }

    public void setAlbum(Text album) {
        this.album = album;
    }

    public Text getDuration() {
        return duration;
    }

    public void setDuration(Text duration) {
        this.duration = duration;
    }
}
