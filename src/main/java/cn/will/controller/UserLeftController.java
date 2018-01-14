package cn.will.controller;

import cn.will.po.User;
import cn.will.service.UserService;
import cn.will.util.FXMLLoaderHelper;
import cn.will.util.ViewHelper;
import cn.will.vo.MusicResultVO;
import fxml.Main;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
//        favoritePlayListLabel.setOnMouseClicked(e->{
//            changeSelected(favoritePlayListLabel);
//            showFavoritePlayList();
//        });
    }

    private void initShowCreatePlayListMenu(){
//        createPlayListLabel.setOnMouseClicked(e->{
//            changeSelected(createPlayListLabel);
//            showCreatePlayList();
//        });
    }

    private void changeSelected(Label label) {
        if (selectedLabel != null){
            selectedLabel.getStyleClass().remove("label-selected");
        }
        selectedLabel = label;
        label.getStyleClass().add("label-selected");
    }

    private void showPurchaseMusic(){
        User currentUser = Main.getCurrentUser();
        if (null == currentUser) return;

        List<MusicResultVO> musics = userService.listPurchaseMusic(currentUser);
        rootPane.setCenter(ViewHelper.loadPlayListPane(musics));
    }

    private void showFavoriteMusic(){
        User currentUser = Main.getCurrentUser();
        if (null == currentUser) return;
        List<MusicResultVO> musics = userService.listFavoriteMusic(currentUser);
        rootPane.setCenter(ViewHelper.loadPlayListPane(musics));
    }

    private void showCreatePlayList(){
        //todo
    }

    private void showFavoritePlayList(){
        //todo
    }
    private Stage createStage;

    @FXML private TextField playlistTitle;

    @FXML
    private void showCreatePlaylistStage(){
        Stage stage = new Stage(StageStyle.UNIFIED);
        stage.setTitle("New Playlist");
        stage.initOwner(rootPane.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/music-icon16.png"));
        Parent pane = FXMLLoaderHelper.load("fxml/create-playlist.fxml");
        stage.setScene(new Scene(pane));
        createStage = stage;
        stage.show();
    }

    @FXML
    private void createPlaylist(){
        System.out.println("create list");
        if (Main.getCurrentUser() == null) return;
        String title = playlistTitle.getText();
        if (userService.createPlaylist(title)) createStage.close();
    }

    @FXML
    private void closeCreatePlaylist(){
        if (null != createStage) createStage.close();
    }

    @FXML private TitledPane favoritePlaylistPane;

    private void in(){
    }

    @Override
    public void setBorderPane(BorderPane pane) {
    }

    @Override
    public void initPrimaryStage(Stage primaryStage) {

    }
}
