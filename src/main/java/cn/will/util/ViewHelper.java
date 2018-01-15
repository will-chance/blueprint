package cn.will.util;

import cn.will.controller.AlbumDetailController;
import cn.will.vo.MusicResultVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.List;

/**
 * Created on 2018-01-15 1:49 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class ViewHelper {

    public static ScrollPane loadPlayListPane(List<MusicResultVO> data){
        FXMLLoader loader = FXMLLoaderHelper.createLoader("fxml/album-detail.fxml");
        Parent pane = FXMLLoaderHelper.load(loader);
        AlbumDetailController controller = loader.getController();
        controller.setData(data);
        return (ScrollPane) pane;
    }

    public static void showNewStage(String title,Parent root,Window owner){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        stage.getIcons().add(new Image("img/music-icon16.png"));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
