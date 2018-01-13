package cn.will.controller;

import cn.will.po.Music;
import cn.will.service.MusicService;
import cn.will.util.FXMLLoaderHelper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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

    private BorderPane root;

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
    }

    private void initCloseBtn(){
        closeBtn.setOnAction( e->Platform.exit());
        setOnMouseHoverAction(closeBtn);
    }

    private void initMinimumBtn(){
        minimumBtn.setOnAction(event -> {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });
        setOnMouseHoverAction(minimumBtn);
    }

    private void initSearchBtn(){
        setOnMouseHoverAction(searchBtn);
    }

    private void setOnMouseHoverAction(Button button){
        button.setOnMouseEntered(e -> button.getScene().setCursor(Cursor.HAND));
        button.setOnMouseExited(e->button.getScene().setCursor(Cursor.DEFAULT));
    }

    @FXML
    public void search(){
        String keyword = "%"+searchContent.getText()+"%";
        if (null == keyword || "".equals(keyword)) return;
        List<Music> musics = musicService.searchMusics(keyword);
        root.setCenter(loadSearchResultPane(musics));
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

    @Override
    public void initPrimaryStage(Stage primaryStage) {
        initTitleBarAction(primaryStage);
    }


    @Override
    public void setBorderPane(BorderPane pane) {
        this.root = pane;
    }

    private ScrollPane loadSearchResultPane(List<Music> musics){
        Parent pane = null;
        FXMLLoader loader = FXMLLoaderHelper.createLoader("fxml/searchResult.fxml");
        pane = FXMLLoaderHelper.load(loader);
        SearchResultController controller = loader.getController();
        controller.showResult(musics);
        return (ScrollPane) pane;
    }

}
