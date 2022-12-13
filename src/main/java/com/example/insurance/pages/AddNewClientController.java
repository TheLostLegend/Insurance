package com.example.insurance.pages;

import com.example.insurance.entities.Client;
import com.example.insurance.entities.ConnectorDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class AddNewClientController extends FXbasic {

    public TextField address1;
    public TextField first_name1;
    public TextField middle_name1;
    public TextField last_name1;
    public TextField phone_number1;
    public TextField passport_id1;

    public void setABC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @FXML
    private Button buttonActive;

    @FXML
    private Text outputInfo;

    @FXML
    void initialize() {
        buttonActive.setOnAction(event -> createClient());
    }

    private void createClient(){
        try{
            int passport_id = Integer.parseInt(passport_id1.getText());
            String first_name = first_name1.getText();
            String middle_name = middle_name1.getText();
            String last_name = last_name1.getText();
            String address = address1.getText();
            String phone_number = phone_number1.getText();
            if (!ConnectorDB.ifExist("SELECT passport_id FROM Client WHERE passport_id = " + passport_id)){
                if ((new Client(passport_id, first_name, middle_name, last_name, address, phone_number)).insertClient()) {
                    outputInfo.setText("Клиент " + passport_id + " успешно добавлен!");
                    Logger.getGlobal().info("Клиент " + passport_id + " успешно добавлен!");
                }
                else {
                    outputInfo.setText("Пожалуйсте верно заполните все поля");
                }
            }
            else {
                outputInfo.setText("Пользователь с таким пасспортом уже существет в БД");
            }
        }
        catch (Exception e){
            outputInfo.setText("Пожалуйсте верно заполните все поля");
        }
    }
    @FXML
    @Override
    void back(ActionEvent event) {
        Logger.getGlobal().info("Пользователь вернулся в основное меню");
        stage.setScene(this.scene);
        stage.show();
    }
}

