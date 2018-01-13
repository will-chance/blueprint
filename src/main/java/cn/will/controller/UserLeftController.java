package cn.will.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;

/**
 * Created on 2018-01-13 12:46 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@FXMLController
public class UserLeftController {

    @FXML private Label purchaseMusicLabel;
    @FXML private Label favoriteMusicLabel;
    @FXML private Label createPlayListLabel;
    @FXML private Label favoritePlayListLabel;

    private Label selectedLabel;

    @FXML
    private void initialize(){
        setMouseHoverCursor(purchaseMusicLabel);
        setMouseHoverCursor(favoriteMusicLabel);
        setMouseHoverCursor(createPlayListLabel);
        setMouseHoverCursor(favoritePlayListLabel);


        setOnMouseClick(purchaseMusicLabel);
        setOnMouseClick(favoriteMusicLabel);
        setOnMouseClick(createPlayListLabel);
        setOnMouseClick(favoritePlayListLabel);
    }

    private void setMouseHoverCursor(Label label){
        label.setOnMouseEntered(e->label.getScene().setCursor(Cursor.HAND));
        label.setOnMouseExited(e->label.getScene().setCursor(Cursor.DEFAULT));
    }

    private void setLabelSelected(Label label){
        label.getStyleClass().add("label-click");
    }

    private void setLabelUnSelected(Label label){
        label.getStyleClass().remove("label-click");
    }

    private void setOnMouseClick(Label label){
        label.setOnMouseClicked(e ->{
            if (selectedLabel != null){
                setLabelUnSelected(selectedLabel);
            }
            selectedLabel = label;
            setLabelSelected(label);
        });
    }

    @FXML
    private void showPurchaseMusic(Label label){

    }

    @FXML
    private void showFavoriteMusic(){

    }

    @FXML
    private void showCreatePlayList(){

    }

    @FXML
    private void showFavoritePlayList(){

    }

}
