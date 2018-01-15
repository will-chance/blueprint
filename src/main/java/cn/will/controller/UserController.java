package cn.will.controller;

import cn.will.po.User;
import cn.will.service.UserService;
import fxml.Main;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018-01-14 4:30 AM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class UserController {
    @Autowired private UserService userService;

    private ImageView avatar;

    private Text usernameLabel;

    @FXML private Button loginBtn;

    @FXML private TextField usernameField;

    @FXML private PasswordField passwordField;

    @FXML
    private void initialize(){
        loginBtn.setOnMouseEntered(e->loginBtn.getScene().setCursor(Cursor.HAND));
        loginBtn.setOnMouseExited(e->loginBtn.getScene().setCursor(Cursor.DEFAULT));
    }

    @FXML
    private void login(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = userService.login(new User(username, password));
        if (null == user){
            //todo login fail
            return;
        }
        Main.setCurrentUser(user);
        loginBtn.getScene().getWindow().hide();
        updateUserInfo(avatar,usernameLabel,user);
        UserLeftController controller = Main.BootFX.getContext().getBean(UserLeftController.class);
        controller.updateCreatePlaylist();
        controller.updateFavoritePlaylist();
    }

    private void updateUserInfo(ImageView view,Text text,User user){
        text.setText(user.getUsername());
        view.setImage(new Image(user.getAvatarPic(),30,30,false,false));
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public void setUsernameLabel(Text usernameLabel) {
        this.usernameLabel = usernameLabel;
    }
}
