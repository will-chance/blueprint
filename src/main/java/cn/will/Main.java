package cn.will;

import cn.will.controller.TitleBarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created on 2018-01-11 8:50 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(final Stage primaryStage) {
        BorderPane root = (BorderPane) loadLayout("fxml/player.fxml");
        HBox titleBar = loadTitleBar(primaryStage);
        root.setTop(titleBar);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("img/music-icon32.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void loadMainStage(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/player.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Parent loadLayout(String location){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(location));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    private HBox loadTitleBar(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/titleBar.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TitleBarController controller = loader.getController();
        controller.initTitleBarAction(primaryStage);
        return (HBox) root;
    }

}
