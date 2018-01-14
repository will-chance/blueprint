package cn.will.view;

import cn.will.util.TimeHelper;
import cn.will.vo.AlbumMusicVO;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created on 2018-01-14 4:37 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class AlbumMusicCell extends HBox{
    private ImageView favoriteView = new ImageView(new Image("img/unfavorite16.png"));

    private ImageView unfavoriteView = new ImageView(new Image("img/favorite16.png"));

    private Label favoriteIcon;

    private boolean favorite;

    private Label title;

    private Label duration;

    private Label id;

    public AlbumMusicCell(AlbumMusicVO music) {
        int id = music.getId();
        if (id <10){
            this.id = new Label("0"+id);
        }else {
            this.id = new Label(String.valueOf(id));
        }

        this.title = new Label(music.getTitle());
        this.duration = new Label(TimeHelper.Secend2Minute(music.getDuration()));
        this.favorite = music.isFavorite();
        initFavoriteIcon(favorite);
        setHoverCursor(favoriteIcon);
        initFavoriteAction();
        initStyle();
        init();
    }

    private void init(){
          setSpacing(10);
          getChildren().addAll(id,favoriteIcon,title,duration);
    }

    private void initStyle(){
        id.getStyleClass().addAll("album-music-cell-id");
        favoriteIcon.getStyleClass().addAll("album-music-cell-icon");
        title.getStyleClass().addAll("album-music-cell-title");
        duration.getStyleClass().addAll("album-music-cell-duration");

    }

    private void initFavoriteIcon(boolean favorite){
        favoriteIcon = new Label();
        if (!favorite){
            favoriteIcon.setGraphic(favoriteView);
        } else {
            favoriteIcon.setGraphic(unfavoriteView);
        }
    }

    private void initFavoriteAction(){
        favoriteIcon.setOnMouseClicked(e -> {
            setFavoriteIcon(favorite);
            favorite = !favorite;
        });
    }

    private void setFavoriteIcon(boolean favorite){
        if (favorite){
            favoriteIcon.setGraphic(favoriteView);
        } else {
            favoriteIcon.setGraphic(unfavoriteView);
        }
    }

    private void setHoverCursor(Label label){
        if (null == label) return;
        label.setOnMouseEntered(e-> label.getScene().setCursor(Cursor.HAND));
        label.setOnMouseExited(e->label.getScene().setCursor(Cursor.DEFAULT));
    }
}
