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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018-01-13 6:40 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class SearchResultController implements ViewController{

    @Autowired private MusicService musicService;

    @Autowired private BorderPane rootPane;

    private String keyword;

    @FXML private TableView titleResult;
    @FXML private TableView artistResult;
    @FXML private TableView albumResult;
    @FXML private TableView playlistResult;

    @FXML private Label resultPreview;

    @FXML
    private void initialize(){
        titleResult.setPlaceholder(new Label("No Music"));
        artistResult.setPlaceholder(new Label("No Music"));
        albumResult.setPlaceholder(new Label("No Music"));
        playlistResult.setPlaceholder(new Label("No Music"));
    }

    public void setData(List<MusicResultVO> musics,String keyword){
        this.keyword = keyword;
        if (null == musics || musics.isEmpty()) {
            resultPreview.setText("Not Relative Result");
            return;
        }
        ObservableList<MusicListCell> datas = getMusicListCells(musics);
        titleResult.setItems(datas);
        searchMusicByArtist();
        searchMusicByAlbum();
        searchMusicByPlaylist();
    }

    private ObservableList<MusicListCell> getMusicListCells(List<MusicResultVO> musics) {
        ObservableList<MusicListCell> datas = FXCollections.observableArrayList();
        for (int i = 0; i < musics.size(); i++) {
            MusicResultVO music = musics.get(i);
            music.setId(i+1);
            MusicListCell data =  Main.BootFX.getContext().getBean(MusicListCell.class, music);
            datas.add(data);
        }
        return datas;
    }

    @FXML
    private void searchMusicByArtist(){
        List<MusicResultVO> musics = musicService.searchMusicByArtist(keyword);
        artistResult.setItems(getMusicListCells(musics));
    }

    private void searchMusicByAlbum(){
        List<MusicResultVO> musics = musicService.searchMusicByAlbum(keyword);
        albumResult.setItems(getMusicListCells(musics));
    }

    private void searchMusicByPlaylist(){
        List<MusicResultVO> musics = musicService.searchMusicByPlaylist(keyword);
        playlistResult.setItems(getMusicListCells(musics));
    }

    @Override
    public void initPrimaryStage(Stage primaryStage) {

    }

}
