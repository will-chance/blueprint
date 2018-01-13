package cn.will.controller;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created on 2018-01-13 4:50 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public interface ViewController {
    void initPrimaryStage(Stage primaryStage);

    void setBorderPane(BorderPane pane);

}
