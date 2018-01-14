package cn.will.controller;

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
    private ObservableList<MusicListCell> musics;

    @Autowired private BorderPane rootPane;

    @FXML private TableView result;

    @FXML private Label resultPreview;

    @FXML
    private void initialize(){
        result.setPlaceholder(new Label("No Relative Result"));
        musics = FXCollections.observableArrayList();
    }

    public void showResult(List<MusicResultVO> musics){
        if (null == musics || musics.isEmpty()) {
            resultPreview.setText("Not Relative Result");
            return;
        }
        this.musics.clear();
        for (int i = 0; i < musics.size(); i++) {
            MusicListCell music =  Main.BootFX.getContext().getBean(MusicListCell.class,musics.get(i));
            this.musics.add(music);
        }
        result.setItems(this.musics);
    }

    @Override
    public void initPrimaryStage(Stage primaryStage) {

    }

    @Override
    public void setBorderPane(BorderPane pane) {
    }
}
