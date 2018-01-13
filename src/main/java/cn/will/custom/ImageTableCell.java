package cn.will.custom;

import cn.will.po.Music;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * Created on 2018-01-13 6:32 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
public class ImageTableCell<S,T> implements Callback<TableColumn.CellDataFeatures<S,T>, ObservableValue<T>> {

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<S, T> param) {
        TableCell<Music,ImageView> cell = new TableCell<Music,ImageView>(){
            @Override
            protected void updateItem(ImageView item, boolean empty) {
                setGraphic(item);
            }
        };
        return null;
    }
}
