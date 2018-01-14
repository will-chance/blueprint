package cn.will.controller;

import cn.will.po.User;
import cn.will.service.UserService;
import cn.will.util.ViewHelper;
import cn.will.vo.MusicResultVO;
import fxml.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018-01-13 12:46 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class UserLeftController implements ViewController {

    @Autowired private BorderPane rootPane;

    @Autowired private UserService userService;

    @FXML private Label purchaseMusicLabel;
    @FXML private Label favoriteMusicLabel;
    @FXML private Label createPlayListLabel;
    @FXML private Label favoritePlayListLabel;

    private Label selectedLabel;

    @FXML
    private void initialize(){
        initShowFavoriteMenu();
        initShowPurchaseMenu();
        initShowCreatePlayListMenu();
        initShowFavoritePlayListMenu();
    }

    private void initShowFavoriteMenu(){
        favoriteMusicLabel.setOnMouseClicked(e->{
            changeSelected(favoriteMusicLabel);
            showFavoriteMusic();
        });
    }

    private void initShowPurchaseMenu(){
        purchaseMusicLabel.setOnMouseClicked(e->{
            changeSelected(purchaseMusicLabel);
            showPurchaseMusic();
        });
    }

    private void initShowFavoritePlayListMenu(){
        favoritePlayListLabel.setOnMouseClicked(e->{
            changeSelected(favoritePlayListLabel);
            showFavoritePlayList();
        });
    }

    private void initShowCreatePlayListMenu(){
        createPlayListLabel.setOnMouseClicked(e->{
            changeSelected(createPlayListLabel);
            showCreatePlayList();
        });
    }

    private void changeSelected(Label label) {
        if (selectedLabel != null){
            selectedLabel.getStyleClass().remove("label-selected");
        }
        selectedLabel = label;
        label.getStyleClass().add("label-selected");
    }

    @FXML
    private void showPurchaseMusic(){
        User currentUser = Main.getCurrentUser();
        if (null == currentUser) return;

        List<MusicResultVO> musics = userService.listPurchaseMusic(currentUser);
        rootPane.setCenter(ViewHelper.loadPlayListPane(musics));
    }

    @FXML
    private void showFavoriteMusic(){
        User currentUser = Main.getCurrentUser();
        if (null == currentUser) return;
        List<MusicResultVO> musics = userService.listFavoriteMusic(currentUser);
        rootPane.setCenter(ViewHelper.loadPlayListPane(musics));
    }

    @FXML
    private void showCreatePlayList(){

    }

    @FXML
    private void showFavoritePlayList(){

    }

    @Override
    public void setBorderPane(BorderPane pane) {
    }

    @Override
    public void initPrimaryStage(Stage primaryStage) {

    }
}
