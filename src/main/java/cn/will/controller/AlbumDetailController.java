package cn.will.controller;

import cn.will.service.MusicService;
import cn.will.view.MusicListCell;
import cn.will.vo.MusicResultVO;
import fxml.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created on 2018-01-12 7:58 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class AlbumDetailController implements ViewController{

    @Autowired private MusicService musicService;

    @FXML private TableView musicList;

    @FXML private Label playTimes;

    @FXML private Label containNum;

    @FXML private Label albumName;

    @FXML private ImageView albumImg;

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
        List<MusicResultVO> musics = musicService.listNewest();
        ObservableList<MusicListCell> data = FXCollections.observableArrayList();
        for (int i = 0; i < musics.size(); i++) {
            MusicListCell music = Main.BootFX.getContext().getBean(MusicListCell.class,musics.get(i));
//                    new MusicListCell(musics.get(i));
            data.add(music);
        }
        musicList.setItems(data);
    }

    @FXML
    private void showMusicList(){
        ObservableList<MusicResultVO> musics = FXCollections.observableList(musicService.listNewest());
        musicList.getItems().add(musics);
    }

    private void showRecommend(){
        albumName.setText("Today Recommend");
        containNum.setText("16");
        playTimes.setText("0");
        creatorLabel.setText("WILL");
        createLabel.setText(new Date(System.currentTimeMillis()).toString());
    }

    @Override
    public void initPrimaryStage(Stage primaryStage) {

    }

    @Override
    public void setBorderPane(BorderPane loader) {

    }
}
