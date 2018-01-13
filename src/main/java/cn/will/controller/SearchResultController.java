package cn.will.controller;

import cn.will.po.Music;
import cn.will.view.MusicListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * Created on 2018-01-13 6:40 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class SearchResultController {
    private ObservableList<MusicListCell> musics;

    @FXML private TableView result;

    @FXML private Label resultPreview;

    @FXML
    private void initialize(){
        result.setPlaceholder(new Label("No Relative Result"));
        musics = FXCollections.observableArrayList();
    }

    public void showResult(List<Music> musics){
        if (null == musics) {
            resultPreview.setText("Not Relative Result");
            return;
        }
        this.musics.clear();
        for (int i = 0; i < musics.size(); i++) {
            MusicListCell music = new MusicListCell(musics.get(i),false);
            this.musics.add(music);
        }
        result.setItems(this.musics);
    }
}
