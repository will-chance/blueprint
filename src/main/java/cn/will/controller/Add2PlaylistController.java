package cn.will.controller;

import cn.will.service.MusicService;
import cn.will.vo.PlaylistVO;
import fxml.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
            label.setId(String.valueOf(playlist.getId()));
            label.setMaxWidth(150);
            label.setPrefWidth(150);
            label.setOnMouseClicked(e->{
                String id = label.getId();
                System.out.println(musicId + " playlistId:" +id);
                if (addMusic2Playlist(musicId, Integer.parseInt(id))){
                    label.getScene().getWindow().hide();
                }

            });
            Tooltip tip = new Tooltip(name);
            Tooltip.install(label,tip);
            label.getStyleClass().add("playlist");
            playlistMenu.getChildren().add(label);
        }
    }

    /**
     * 添加音乐到歌单中
     */
    private boolean addMusic2Playlist(int musicId,int playlistId){
        if (Main.getCurrentUser() == null) return false;
        //todo 1.判断该音乐是否已经在歌单中，如果已存在。不重复添加 --> 提示
        return musicService.addMusic2Playlist(musicId,playlistId);
    }
}
