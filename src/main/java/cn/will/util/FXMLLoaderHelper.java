package cn.will.util;

import fxml.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created on 2018-01-13 5:46 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@Component
public class FXMLLoaderHelper {

    private ConfigurableApplicationContext applicationContext;

    public static Parent load(String location){
        Parent parent = null;
        FXMLLoader loader = new FXMLLoader(Charset.forName("UTF-8"));
        try {
            loader.setLocation(new ClassPathResource(location).getURL());
            loader.setControllerFactory(Main.BootFX.getContext()::getBean);
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }


    public static FXMLLoader createLoader(String location){
        FXMLLoader loader = new FXMLLoader(Charset.forName("UTF-8"));
        try {
            loader.setLocation(new ClassPathResource(location).getURL());
            loader.setControllerFactory(Main.BootFX.getContext()::getBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    public static Parent load(FXMLLoader loader){
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
