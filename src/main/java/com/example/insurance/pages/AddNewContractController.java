package com.example.insurance.pages;

import com.example.insurance.entities.ConnectorDB;
import com.example.insurance.entities.Contract;
import com.example.insurance.entities.Tariff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.logging.Logger;

public class AddNewContractController extends FXbasic {

    public ListView<String> listTariffs;
    public ListView<String> listYears;
    public ListView<String> listClients;
    public TextField payment_amount;

    int passport_id;
    int Tariff_id;
    int employee_id;

    float time;
    float interest_rate;

    String years;

    public void setATC(Scene scene, Stage stage, int employee_id) {
        this.scene = scene;
        this.stage = stage;
        this.employee_id = employee_id;
    }
    @FXML
    private Label outputId;

    @FXML
    private Button buttonActive;

    @FXML
    private Text outputInfo;

    @FXML
    void initialize() {
        int contract_id = ConnectorDB.getValueInt(
                "SELECT MAX(contract_id) AS maxId FROM Insurance_contract","maxId") + 1;
        outputId.setText("Данному контракту будет присвоен ID = " + contract_id);
        ObservableList<String> list = FXCollections.observableArrayList();
        String[] types = {"Пол года", "Год", "3 года"};
        list.addAll(types);
        listYears.setItems(list);
        ObservableList<String> listDer = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT passport_id AS id FROM Client");
        listDer.addAll(arrayList);
        listClients.setItems(listDer);
        ObservableList<String> listDer1 = FXCollections.observableArrayList();
        ArrayList<String> arrayList1 = ConnectorDB.showID("SELECT Tariff_id AS id FROM Tariff");
        listDer1.addAll(arrayList1);
        listTariffs.setItems(listDer1);
        buttonActive.setOnAction(event -> createContract(contract_id));
    }

    private void createContract(int id){
        try {
            float payment = Float.parseFloat(payment_amount.getText());
            switch (years) {
                case "Пол года" -> {
                    time = 0.5F;
                    interest_rate = ConnectorDB.getValueFloat("SELECT * FROM Tariff WHERE Tariff_id = " + Tariff_id, "yearH_IR");
                }
                case "Год" -> {
                    time = 1F;
                    interest_rate = ConnectorDB.getValueFloat("SELECT * FROM Tariff WHERE Tariff_id = " + Tariff_id, "year_IR");
                }
                case "3 года" -> {
                    time = 3F;
                    interest_rate = ConnectorDB.getValueFloat("SELECT * FROM Tariff WHERE Tariff_id = " + Tariff_id, "year3_IR");
                }
                default -> {
                }
            }
            if ((new Contract(id, employee_id, passport_id, Tariff_id, null, time, interest_rate, payment)).insertContract()){
                outputInfo.setText("Контракт " + id + " успешно создан!");
                Logger.getGlobal().info("Контракт " + id + " успешно создан!");
                int contract_id = ConnectorDB.getValueInt(
                        "SELECT MAX(contract_id) AS maxId FROM Insurance_contract","maxId") + 1;
                outputId.setText("Данному контракту будет присвоен ID = " + contract_id);
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
        Logger.getGlobal().info("Пользователь вернулся в основное меню");
        stage.setScene(this.scene);
        stage.show();
    }

    public void clickMouseBranch(MouseEvent mouseEvent) {
        String action = listTariffs.getSelectionModel().getSelectedItem();
        if(action != null)
            Tariff_id = Integer.parseInt(action);
    }

    public void clickMouseBranch1(MouseEvent mouseEvent) {
        String action = listYears.getSelectionModel().getSelectedItem();
        if(action != null)
            years = action;
    }

    public void clickMouseBranch2(MouseEvent mouseEvent) {
        String action = listClients.getSelectionModel().getSelectedItem();
        if(action != null)
            passport_id = Integer.parseInt(action);
    }
}

