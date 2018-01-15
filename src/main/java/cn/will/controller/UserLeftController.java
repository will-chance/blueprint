package cn.will.controller;

import cn.will.po.User;
import cn.will.service.UserService;
import cn.will.util.FXMLLoaderHelper;
import cn.will.util.ViewHelper;
import cn.will.vo.MusicResultVO;
import cn.will.vo.PlaylistVO;
import fxml.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018-01-13 12:46 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Scope(value = "singleton")
@Component
public class UserLeftController implements ViewController {

    @Autowired private BorderPane rootPane;

    @Autowired private UserService userService;

    @FXML private Label purchaseMusicLabel;
    @FXML private Label favoriteMusicLabel;

    @FXML private VBox createdPlaylist;
    @FXML private VBox favoritePlaylist;

    @FXML
    private void initialize(){
        initShowFavoriteMenu();
        initShowPurchaseMenu();
    }

    private void initShowFavoriteMenu(){
        favoriteMusicLabel.setOnMouseClicked(e->{
            showFavoriteMusic();
        });
    }

    private void initShowPurchaseMenu(){
        purchaseMusicLabel.setOnMouseClicked(e->{
            showPurchaseMusic();
        });
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
        if (Main.getCurrentUser() == null) return;
        String title = playlistTitle.getText();
        if (userService.createPlaylist(title)) createStage.close();
        updateCreatePlaylist();
    }

    @FXML
    private void closeCreatePlaylist(){
        if (null != createStage) createStage.close();
    }

    public void showPlaylist(int musicId){
        FXMLLoader loader = FXMLLoaderHelper.createLoader("fxml/add-2-playlist.fxml");
        Parent pane = FXMLLoaderHelper.load(loader);
        Add2PlaylistController controller = loader.getController();
        controller.setData(userService.listCreatedPlaylist(),musicId);
        ViewHelper.showNewStage("Playlist",pane,rootPane.getScene().getWindow());
    }

    public void updateCreatePlaylist(){
        List<PlaylistVO> data = userService.listCreatedPlaylist();
        List<Label> labels = new ArrayList<>();
        for (PlaylistVO playlist:data) {
            Label label = new Label(playlist.getTitle(),new ImageView(new Image("img/play-list16.png")));
            label.setId(String.valueOf(playlist.getId()));
            Tooltip tip = new Tooltip(playlist.getTitle());
            Tooltip.install(label,tip);
            label.getStyleClass().add("playlist");
            labels.add(label);
        }
        createdPlaylist.getChildren().clear();
        createdPlaylist.getChildren().addAll(labels);
    }

    public void updateFavoritePlaylist(){
        List<Label> labels = new ArrayList<>();
        List<PlaylistVO> data = userService.listFavoritePlaylist();
        for (PlaylistVO playlist:data) {
            Label label = new Label(playlist.getTitle(),new ImageView(new Image("img/favorite16.png")));
            Tooltip tip = new Tooltip(playlist.getTitle());
            Tooltip.install(label,tip);
            label.setId(String.valueOf(playlist.getId()));
            label.getStyleClass().add("playlist");
            labels.add(label);
        }
        favoritePlaylist.getChildren().clear();
        favoritePlaylist.getChildren().addAll(labels);
    }

    @Override
    public void initPrimaryStage(Stage primaryStage) {

    }
}
