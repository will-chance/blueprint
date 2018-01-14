package fxml;

import cn.will.controller.ViewController;
import cn.will.po.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.Charset;

import static java.util.Objects.isNull;

/**
 * Created on 2018-01-12 9:06 PM
 * Author: Bowei Chan
 * E-mail: bowei_chan@163.com
 * Project: blueprint
 * Desc:
 */
@MapperScan("cn.will.mapper")
@ComponentScan(basePackages = "cn.will.service")
@ComponentScan(basePackages = "fxml")
@ComponentScan(basePackages = "cn.will.controller")
@ComponentScan(basePackages = "cn.will.view")
@SpringBootApplication
public class Main {

    private static String APP_TITLE = "Blueprint";

    private static User currentUser;

    public static User getCurrentUser(){
        return currentUser;
    }
    public static void setCurrentUser(User user){
        currentUser = user;
    }

    public static void main(String[] args) {
        Application.launch(BootFX.class,args);
    }

    @Bean
    protected static Application bootFx() {

        // I declare this bean this way so I can use
        // the static instance stored when JavaFX
        // calls the no-arg 'BootFX' constructor
        // via reflection.

        if (BootFX.instance == null) {
            BootFX.instance = new BootFX();
        }

        return BootFX.instance;
    }

    public static class BootFX extends Application {

        private static BootFX instance;

        private BorderPane root;

        public static ConfigurableApplicationContext getContext(){
            return applicationContext;
        }

        private static ConfigurableApplicationContext applicationContext;

        public BootFX() {
            BootFX.instance = this;
        }

        private Parent loadPane(String location,Stage primaryStage) throws IOException {
            FXMLLoader loader = createLoader(location);
            Parent root = loader.load();
            ((ViewController)loader.getController()).initPrimaryStage(primaryStage);
            return root;
        }

        private Parent loadPane(String location,Stage primaryStage,BorderPane borderPane) throws IOException {
            Parent parent;
            FXMLLoader loader = createLoader(location);
            parent = loader.load();
            ViewController controller = loader.getController();
            controller.initPrimaryStage(primaryStage);
            controller.setBorderPane(borderPane);
            return parent;
        }

        private FXMLLoader createLoader(String location) throws IOException {
            FXMLLoader loader = new FXMLLoader(Charset.forName("UTF-8"));
            loader.setLocation(new ClassPathResource(location).getURL());
            loader.setControllerFactory(applicationContext::getBean);
            return loader;
        }

        @Override
        public void init() throws Exception {

            String[] args = !isNull(getParameters()) ?
                    getParameters().getRaw().toArray(new String[]{}) : new String[]{};

            applicationContext = new SpringApplicationBuilder(Main.class)
                    .run(args);
        }

        @Bean
        public BorderPane getRootPane(){
            return root;
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            //加载主页面框架
            BorderPane root = (BorderPane) loadPane("fxml/player.fxml",primaryStage);
            this.root = root;
            //加载标题栏
            Parent titleBar = loadPane("fxml/titleBar.fxml", primaryStage,root);
            //设置标题栏
            root.setTop(titleBar);

            primaryStage.setTitle(APP_TITLE);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.getIcons().add(new Image("img/music-icon32.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        @Override
        public void stop() throws Exception {
            SpringApplication.exit(applicationContext);
        }

    }
}

