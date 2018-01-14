package cn.will.view;

import cn.will.controller.ArtistMusicController;
import cn.will.service.AlbumService;
import cn.will.service.UserService;
import cn.will.util.FXMLLoaderHelper;
import cn.will.util.TimeHelper;
import cn.will.util.ViewHelper;
import cn.will.vo.AlbumVO;
import cn.will.vo.MusicResultVO;
import fxml.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018-01-13 7:42 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Scope(value = "prototype")
@Component
public class MusicListCell{

    @Autowired private BorderPane rootPane;

    @Autowired private UserService userService;

    @Autowired private AlbumService albumService;

    private ImageView favoriteView = new ImageView(new Image("img/unfavorite16.png"));

    private ImageView unfavoriteView = new ImageView(new Image("img/favorite16.png"));

    private Label favoriteIcon;

    private boolean favorite;

    private Text id;

    private MusicResultVO music;

    private Text title;

    private Text artist;

    private Text album;

    private Text duration;

    public MusicListCell() {
    }

    public MusicListCell(MusicResultVO music) {
        if (null == music) return;
        int musicId = music.getMusicId();
        if (musicId <10){
            this.id = new Text("0"+musicId);
        } else {
            this.id = new Text(String.valueOf(musicId));
        }


        this.music = music;
        this.title = new Text(music.getTitle());
        this.artist = new Text(music.getArtist());
        this.album = new Text(music.getAlbum());
        this.duration = new Text(TimeHelper.Secend2Minute(music.getDuration()));
        this.favorite = music.isFavorite();
        initFavoriteIcon(favorite);
        setHoverCursor(artist);
        setHoverCursor(album);
        setHoverCursor(favoriteIcon);
        initFavoriteAction();
        initToolTip(title);
        initToolTip(artist);
        initToolTip(album);
        initShowArtistMusicAction();
        initShowAlbumMusicAction();
    }

    private void initToolTip(Text text){
        if (null == text) return;
        Tooltip tip = new Tooltip(text.getText());
        Tooltip.install(text,tip);
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

    /**
     * 处理 收藏/取消收藏 操作
     */
    private void initFavoriteAction(){
        favoriteIcon.setOnMouseClicked(e -> {
            if (Main.getCurrentUser() == null) {
                return;
            }
            if (!favorite){
                userService.favoriteMusic(music.getMusicId(), Main.getCurrentUser());
            } else {
                userService.cancelFavoriteMusic(music.getMusicId(),Main.getCurrentUser());
            }
            setFavoriteIcon(favorite);
            favorite = !favorite;
        });
    }

    /**
     * 处理 点击歌手的动作
     * 查找歌手的所有音乐并按专辑分开
     */
    private void initShowArtistMusicAction(){
        artist.setOnMouseClicked(e->{
            List<AlbumVO> albums = albumService.listArtistMusic(music.getArtistId());
            rootPane.setCenter(loadArtistDetailPane(albums));
        });
    }

    private ScrollPane loadArtistDetailPane(List<AlbumVO> data){
        FXMLLoader loader = FXMLLoaderHelper.createLoader("fxml/artist-music.fxml");
        Parent pane = FXMLLoaderHelper.load(loader);
        ArtistMusicController controller = loader.getController();
        controller.setData(data);
        return (ScrollPane) pane;
    }

    /**
     * 处理点击专辑动作 --> 打开专辑详情
     */
    private void initShowAlbumMusicAction(){
        album.setOnMouseClicked(e->{
            List<MusicResultVO> musics = albumService.listAlbumMusic(music.getAlbumId());
            rootPane.setCenter(ViewHelper.loadPlayListPane(musics));
        });
    }

    /********Setter/Getter todo replace with lomock***********/

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
