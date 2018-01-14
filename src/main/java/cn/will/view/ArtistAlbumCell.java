package cn.will.view;

import cn.will.vo.AlbumMusicVO;
import cn.will.vo.AlbumVO;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Created on 2018-01-14 2:05 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class ArtistAlbumCell extends HBox{
    private ImageView albumCover;

    private Text albumTitle;

    private ListView<AlbumMusicCell> musicList;

    public ArtistAlbumCell(AlbumVO album) {
        if (null == album) {
            throw new NullPointerException("album can not be null");
        }
        this.albumTitle = new Text(album.getTitle());
        this.albumCover = new ImageView(new Image(album.getCoverPic(),150,150,false,false));
        if (null == this.musicList){
            this.musicList = new ListView<>();
        }
        List<AlbumMusicVO> musics = album.getMusics();
        for (int i=0;i<musics.size();i++){
            AlbumMusicVO music = musics.get(i);
            music.setId(i+1);
            musicList.getItems().add(new AlbumMusicCell(music));
        }
        initListView(musicList);
        initLayout(albumCover,albumTitle,musicList);
    }

    private void initLayout(ImageView cover,Text albumTitle,ListView list){
        setSpacing(50);
        setPadding(new Insets(10,5,5,10));

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(albumTitle,list);

        getChildren().addAll(cover,vBox);
        setAlbumTitleStyle(albumTitle);
    }

    private void setAlbumTitleStyle(Text title){
        title.getStyleClass().add("album-title");
    }

    private void initListView(ListView listView){
        listView.setMinWidth(470);
        int minHeight = listView.getItems().size() * 23;
        listView.setMinHeight(minHeight);
        listView.setPrefHeight(minHeight);
    }

    public ImageView getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(ImageView albumCover) {
        this.albumCover = albumCover;
    }
}
