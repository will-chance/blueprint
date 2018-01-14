package cn.will.controller;

import cn.will.service.AlbumService;
import cn.will.view.ArtistAlbumCell;
import cn.will.vo.AlbumVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018-01-14 2:25 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class ArtistMusicController {

    @Autowired private AlbumService albumService;

    @FXML private ListView<ArtistAlbumCell> artistAlbum;

    @FXML
    private void initialize(){

    }

    public void loadArtistAlbumMusic(){
        ObservableList<ArtistAlbumCell> cells = FXCollections.observableArrayList();
        List<AlbumVO> albumMusic = albumService.listArtistMusic(1497);
        for (int i = 0; i < albumMusic.size(); i++) {
            ArtistAlbumCell cell = new ArtistAlbumCell(albumMusic.get(i));
            cells.add(cell);
        }
        artistAlbum.setItems(cells);
    }

    /**
     * 设置要显示的数据
     */
    public void setData(List<AlbumVO> data){
        ObservableList<ArtistAlbumCell> cells = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            AlbumVO album = data.get(i);
            ArtistAlbumCell cell = new ArtistAlbumCell(album);
            cells.add(cell);
        }
        artistAlbum.setItems(cells);
    }
}
