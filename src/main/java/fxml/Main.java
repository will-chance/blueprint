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
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    @Component
    public static class BootFX extends Application {

        private static BootFX instance;

        @Resource(name = "rootPane") BorderPane rootPane;

        public static ConfigurableApplicationContext getContext(){
            return applicationContext;
        }

        private static ConfigurableApplicationContext applicationContext;

        public BootFX() {
            BootFX.instance = this;
        }

        /**
         * 获取根布局
         * 获取controller没有使用spring工厂
         * @param location
         * @return
         * @throws IOException
         */
        private Parent loadPaneWithoutSpring(String location) throws IOException {
            FXMLLoader loader = new FXMLLoader(Charset.forName("UTF-8"));
            loader.setLocation(new ClassPathResource(location).getURL());
            Parent root = loader.load();
            return root;
        }

        private Parent loadPaneWithSpring(String location, Stage primaryStage, BorderPane borderPane) throws IOException {
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
        @Scope(value = "singleton")
        public BorderPane rootPane(){
            //加载主页面框架
            BorderPane root = null;
            try {
                root = (BorderPane) loadPaneWithoutSpring("fxml/player.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return root;
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            this.rootPane = (BorderPane) BootFX.getContext().getBean("rootPane");

            //加载标题栏
            Parent titleBar = loadPaneWithSpring("fxml/titleBar.fxml", primaryStage,rootPane);
            //设置标题栏
            rootPane.setTop(titleBar);

            //加载中间面板
            Parent center = loadPaneWithSpring("fxml/album-detail.fxml",primaryStage,rootPane);
            rootPane.setCenter(center);

            //加载左侧面板
            Parent left = loadPaneWithSpring("fxml/user-left.fxml",primaryStage,rootPane);
            rootPane.setLeft(left);

            primaryStage.setTitle(APP_TITLE);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.getIcons().add(new Image("img/music-icon32.png"));
            primaryStage.setScene(new Scene(rootPane));
            primaryStage.show();
        }

        @Override
        public void stop() throws Exception {
            SpringApplication.exit(applicationContext);
        }

    }
}

