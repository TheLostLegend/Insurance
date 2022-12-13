package com.example.insurance.pages;

import com.example.insurance.entities.ConnectorDB;
import com.example.insurance.entities.Tariff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class AddNewTariffController extends FXbasic {

    public TextField title_field;
    public TextField yearH_IR_field;
    public TextField year_IR_field;
    public TextField year3_IR_field;

    public void setATC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }
    @FXML
    private Label outputId;

    @FXML
    private Button buttonActive;

    @FXML
    private Text outputInfo;

    @FXML
    void initialize() {
        int tariffID = ConnectorDB.getValueInt(
                "SELECT MAX(Tariff_id) AS maxId FROM Tariff","maxId") + 1;
        outputId.setText("Данному тарифу будет присвоен ID = " + tariffID);
        buttonActive.setOnAction(event -> createTariff(tariffID));
    }

    private void createTariff(int id){
        try{
            String title = title_field.getText();
            float yearH_IR = Float.parseFloat(yearH_IR_field.getText());
            float year_IR = Float.parseFloat(year_IR_field.getText());
            float year3_IR = Float.parseFloat(year3_IR_field.getText());
            if ((new Tariff(id, title, yearH_IR, year_IR, year3_IR)).insertTariff()){
                outputInfo.setText("Тариф " + id + " успешно создан!");
                Logger.getGlobal().info("Тариф " + id + " успешно создан!");
            }
            else {
                outputInfo.setText("Пожалуйсте верно заполните все поля");
            }
        }
        catch (Exception e){
            outputInfo.setText("Пожалуйсте верно заполните все поля");
        }
    }
    @FXML
    @Override
    void back(ActionEvent event) {
        stage.setScene(this.scene);
        stage.show();
    }
}

