package fxml;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2018-01-12 9:06 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@MapperScan("cn.will.mapper")
@ComponentScan(basePackages = "fxml")
@ComponentScan(basePackages = "cn.will.controller")
@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launchApp(Main.class, PlayerView.class, new BlueprintSplashScreen(),args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.getIcons().add(new Image("img/music-icon32.png"));
    }

    static class BlueprintSplashScreen extends SplashScreen{
        @Override
        public boolean visible() {
            //禁用启动页面
            return false;
        }
    }
}
