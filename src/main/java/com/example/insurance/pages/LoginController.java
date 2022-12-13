package com.example.insurance.pages;

import com.example.insurance.MAINDOOR;
import com.example.insurance.entities.ConnectorDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class LoginController extends  FXbasic{
    public void setLoginC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @FXML
    private TextField logW;


    @FXML
    private PasswordField PasW1;

    @FXML
    private Text RegFinal;

    @FXML @Override
    void back(ActionEvent event) {
        Logger.getGlobal().info("Пользователь вышел в лес.");
        stage.setScene(this.scene);
        stage.show();
    }

    @FXML
    void loginMain(ActionEvent event) throws IOException {
        String realPassword = ConnectorDB.getValueString(String.format("SELECT * FROM Employee WHERE employee_id =" + logW.getText()), "password_emp");
        if (realPassword!=null && realPassword.equals(PasW1.getText())) {
            Logger.getGlobal().info("Вход успешен, перенаправление. Пользователь " + logW.getText());
            RegFinal.setText("Вход успешен, перенаправление.");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MAINDOOR.class.getResource("user_scene.fxml"));
            AnchorPane root = loader.load();
            Scene scene1 = new Scene(root);
            stage.setScene(scene1);
            stage.show();
            UserController mainC = loader.getController();
            mainC.setMainC(this.scene, scene1, stage, Integer.parseInt(logW.getText()));
        }
        else RegFinal.setText("Введен неверный логин или пароль");
    }

}
