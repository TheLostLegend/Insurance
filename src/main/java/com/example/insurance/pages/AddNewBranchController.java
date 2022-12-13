package com.example.insurance.pages;

import com.example.insurance.entities.Branch;
import com.example.insurance.entities.ConnectorDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class AddNewBranchController extends FXbasic {

    public TextField name_field;
    public TextField address_field;
    public TextField phone_field;

    public void setABC(Scene scene, Stage stage) {
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
        int branchID = ConnectorDB.getValueInt(
                "SELECT MAX(branch_id) AS maxId FROM Branch","maxId") + 1;
        outputId.setText("Данному филиалу будет присвоен ID = " + branchID);
        buttonActive.setOnAction(event -> createBranch(branchID));
    }

    private void createBranch(int id){
        try{
            String branch_name = name_field.getText();
            String address = address_field.getText();
            String phone_number = phone_field.getText();
            if ((new Branch(id, branch_name, address, phone_number)).insertBranch()) {
                outputInfo.setText("Филиал " + id + " успешно создан!");
                Logger.getGlobal().info("Филиал " + id + " успешно создан!");
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

