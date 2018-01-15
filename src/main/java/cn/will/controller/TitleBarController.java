package cn.will.controller;

import cn.will.po.User;
import cn.will.service.MusicService;
import cn.will.util.FXMLLoaderHelper;
import cn.will.vo.MusicResultVO;
import fxml.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018-01-12 2:01 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class TitleBarController implements ViewController{

    @Autowired private MusicService musicService;

    @Autowired BorderPane rootPane;

    @FXML private HBox titleBar;

    @FXML private Button closeBtn;

    @FXML private Button minimumBtn;

    @FXML private Button searchBtn;

    @FXML private TextField searchContent;

    @FXML
    private void initialize(){
        initCloseBtn();
        initMinimumBtn();
        initSearchBtn();
        setCursorChangeOnHover(userInfo);
        initAvatarViewPosition();
    }

    private void initAvatarViewPosition(){
        double x = avatar.getX();
        double y = avatar.getY();
        Circle circle = new Circle(x+15,y+15,15);
        avatar.setClip(circle);
    }

    private void initCloseBtn(){
        closeBtn.setOnAction( e->Platform.exit());
        setCursorChangeOnHover(closeBtn);
    }

    private void initMinimumBtn(){
        minimumBtn.setOnAction(event -> {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });
        setCursorChangeOnHover(minimumBtn);
    }

    private void initSearchBtn(){
        setCursorChangeOnHover(searchBtn);
    }

    private void setCursorChangeOnHover(Button button){
        button.setOnMouseEntered(e -> button.getScene().setCursor(Cursor.HAND));
        button.setOnMouseExited(e->button.getScene().setCursor(Cursor.DEFAULT));
    }

    /**
     * 加载该标题栏后一定要被调用。
     */
    public void initTitleBarAction(final Stage primaryStage){
        final Delta delta = new Delta();
        titleBar.setOnMousePressed(event -> {
            delta.x = primaryStage.getX() - event.getScreenX();
            delta.y = primaryStage.getY() - event.getScreenY();
        });

        titleBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + delta.x);
            primaryStage.setY(event.getScreenY() + delta.y);
        });
    }

    private class Delta{
        double x;
        double y;
    }

    /***user***/
    private User currentUser;

    @FXML private HBox userInfo;

    @FXML private ImageView avatar;

    @FXML private Text usernameLabel;

    @FXML
    private void login(){
        if (Main.getCurrentUser() == null) {
            //show login stage
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNIFIED);
            loginStage.initOwner(rootPane.getScene().getWindow());
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setResizable(false);
            FXMLLoader loader = FXMLLoaderHelper.createLoader("fxml/login.fxml");
            Parent root = FXMLLoaderHelper.load(loader);
            UserController controller = loader.getController();
            controller.setAvatar(avatar);
            controller.setUsernameLabel(usernameLabel);
            loginStage.getIcons().add(new Image("img/user32.png"));
            loginStage.setScene(new Scene(root));
            loginStage.show();
        }
    }

    @FXML
    public void search(){
        String keyword = searchContent.getText();
        if (null == keyword || "".equals(keyword)) return;
        keyword = "%" + keyword + "%";
        List<MusicResultVO> musics = musicService.searchMusics(keyword);
        rootPane.setCenter(loadSearchResultPane(musics));
    }

    private ScrollPane loadSearchResultPane(List<MusicResultVO> musics){
        Parent pane = null;
        FXMLLoader loader = FXMLLoaderHelper.createLoader("fxml/search-result.fxml");
        pane = FXMLLoaderHelper.load(loader);
        SearchResultController controller = loader.getController();
        controller.setData(musics);
        return (ScrollPane) pane;
    }

    private void setCursorChangeOnHover(HBox hBox){
        hBox.setOnMouseEntered(e->{
            hBox.getScene().setCursor(Cursor.HAND);
        });
        hBox.setOnMouseExited(e->{
            hBox.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    @Override
    public void initPrimaryStage(Stage primaryStage) {
        initTitleBarAction(primaryStage);
    }

}
