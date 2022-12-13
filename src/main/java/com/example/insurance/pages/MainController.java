package com.example.insurance.pages;

import com.example.insurance.MAINDOOR;
import com.example.insurance.entities.ConnectorDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class MainController {
    Scene scene;
    Stage stage;

    public void setMainC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @FXML
    void exit(ActionEvent event) {
        ConnectorDB.closeConnection();
        Logger.getGlobal().info("Программа завершила свою работу.");
        stage.close();
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("register_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Logger.getGlobal().info("Пользователь вошел в меню регистрации");
        RegisterController intC = loader.getController();
        intC.setRegC(this.scene, stage);
    }

    @FXML
    void vhod(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("login_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Logger.getGlobal().info("Пользователь вошел в меню логина");
        LoginController intC = loader.getController();
        intC.setLoginC(this.scene, stage);

    }

}
