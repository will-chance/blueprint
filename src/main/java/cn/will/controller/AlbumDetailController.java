package cn.will.controller;

import cn.will.po.Music;
import cn.will.service.MusicService;
import cn.will.view.MusicListCell;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.List;

/**
 * Created on 2018-01-12 7:58 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@ComponentScan
@FXMLController
public class AlbumDetailController {

    @Autowired private MusicService musicService;

    @FXML private TableView musicList;

    @FXML private Label playTimes;

    @FXML private Label containNum;

    @FXML private Label albumName;

    @FXML private Image albumImg;

    @FXML private Label creatorLabel;

    @FXML private Label createLabel;

    @FXML private Label descLabel;

    @FXML
    private void initialize(){
        showRecommend();
        initMusicList();
    }

    private void initMusicList(){
        musicList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        List<Music> musics = musicService.listNewest();
        ObservableList<MusicListCell> data = FXCollections.observableArrayList();
        for (int i = 0; i < musics.size(); i++) {
            MusicListCell music =  new MusicListCell(musics.get(i),false);
            data.add(music);
        }
        musicList.setItems(data);
    }

    @FXML
    private void showMusicList(){
        ObservableList<Music> musics = FXCollections.observableList(musicService.listNewest());
        System.out.println(musics.size());
        musicList.getItems().add(musics);
    }

    private void showRecommend(){
        albumName.setText("Today Recommend");
        containNum.setText("16");
        playTimes.setText("0");
        creatorLabel.setText("WILL");
        createLabel.setText(new Date(System.currentTimeMillis()).toString());
    }

}
