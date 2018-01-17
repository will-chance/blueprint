package cn.will.controller;

import cn.will.service.MusicService;
import cn.will.vo.PlaylistVO;
import fxml.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018-01-15 5:35 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class Add2PlaylistController {

    @FXML private VBox playlistMenu;

    @Autowired private MusicService musicService;

    @FXML
    private void initialize(){

    }

    public void setData(List<PlaylistVO> data,int musicId){
        playlistMenu.getChildren().clear();
        for (int i = 0; i < data.size(); i++) {
            PlaylistVO playlist = data.get(i);
            String name =playlist.getTitle();
            Label label = new Label(name,new ImageView(new Image("img/play-list16.png")));
            label.setMaxWidth(150);
            label.setPrefWidth(150);
            label.setOnMouseClicked(e->{
                if (addMusic2Playlist(musicId, playlist.getId())){
                    label.getScene().getWindow().hide();
                } else {
                    showPopupMessage("The music already in this playlist");
                    label.getScene().getWindow().hide();
                }

            });
            Tooltip tip = new Tooltip(name);
            Tooltip.install(label,tip);
            label.getStyleClass().add("playlist");
            playlistMenu.getChildren().add(label);
        }
    }

    private void showPopupMessage(String msg){
        HBox box = new HBox();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(box);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        box.getStylesheets().add("css/root.css");
        box.setStyle("-fx-background-color: #000000;-fx-opacity: 50%");
        Label label = new Label(msg);
        label.setGraphic(new ImageView(new Image("img/error-f.png")));
        box.getChildren().add(label);
        stage.show();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                Platform.runLater(()-> stage.close());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 添加音乐到歌单中
     */
    private boolean addMusic2Playlist(int musicId,int playlistId){
        if (Main.getCurrentUser() == null) return false;
        return musicService.addMusic2Playlist(musicId,playlistId);
    }
}
