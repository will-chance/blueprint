package cn.will.controller;

import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created on 2018-01-12 2:01 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@FXMLController
public class TitleBarController {

    @FXML private HBox root;

    @FXML private Button closeBtn;

    @FXML private Button minimumBtn;

    @FXML private Button searchBtn;

    @FXML
    private void initialize(){
        initCloseBtn();
        initMinimumBtn();
        initSearchBtn();
        initTitleBarAction(GUIState.getStage());
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

    /**
     * 加载该标题栏后一定要被调用。
     */

    private void initTitleBarAction(final Stage primaryStage){
        final Delta delta = new Delta();
        root.setOnMousePressed(event -> {
            delta.x = primaryStage.getX() - event.getScreenX();
            delta.y = primaryStage.getY() - event.getScreenY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + delta.x);
            primaryStage.setY(event.getScreenY() + delta.y);
        });
    }

    private class Delta{
        double x;
        double y;
    }
}
