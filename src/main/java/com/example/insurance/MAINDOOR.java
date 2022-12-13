package com.example.insurance;

import com.example.insurance.entities.ConnectorDB;
import com.example.insurance.pages.MainController;
import com.example.insurance.pages.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.*;


public class MAINDOOR extends Application {
    public static Stage stage;

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Logger logger = Logger.getGlobal();
        //logger.setUseParentHandlers(false);
        Handler handlerFile = new FileHandler("./insure_logs");
        handlerFile.setLevel(Level.ALL);
        Formatter formatter = new SimpleFormatter();
        handlerFile.setFormatter(formatter);
        logger.addHandler(handlerFile);
        logger.info("logger successfully configured.");
        ConnectorDB cdb = new ConnectorDB();
        cdb.setConnection();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("main_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage = primaryStage;
        MainController mainC = loader.getController();
        mainC.setMainC(scene, stage);
        stage.setScene(scene);
        primaryStage.setTitle("Основное меню");
        primaryStage.show();

    }
}
