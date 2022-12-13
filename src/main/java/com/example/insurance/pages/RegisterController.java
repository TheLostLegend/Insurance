package com.example.insurance.pages;

import com.example.insurance.entities.ConnectorDB;
import com.example.insurance.entities.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RegisterController extends  FXbasic{

    public Button buttonActive;
    public ListView listBranches;
    public TextField first_name1;
    public TextField middle_name1;
    public TextField last_name1;
    public TextField sex1;
    public TextField phone_number1;
    public TextField appointment1;

    public void setRegC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }
    private String password1;
    private String password2;

    private int branch_id;
    private Boolean Reg2 = false, Reg3 = false;

    @FXML
    private Text outputId;
    @FXML
    private Text passw1;

    @FXML
    private Text passw2;

    @FXML
    private Text RegFinal;

    @FXML
    private PasswordField PasW1;

    @FXML
    private PasswordField PasW2;

    @FXML
    void initialize() {
        ObservableList<String> listDer = FXCollections.observableArrayList();
        ArrayList<String> arrayList = ConnectorDB.showID("SELECT branch_id AS id FROM Branch");
        listDer.addAll(arrayList);
        listBranches.setItems(listDer);
        int employeeID = ConnectorDB.getValueInt(
                "SELECT MAX(employee_id) AS maxId FROM Employee","maxId") + 1;
        outputId.setText("В качестве логина вам будет присвоен ID = " + employeeID);
        buttonActive.setOnAction(event -> registerMain(employeeID));
    }

    @FXML
    void passw1Scan(KeyEvent event) {
        password2 = PasW1.getText();
        if (password2.length() > 20) {passw1.setText("Пароль слишком длинный"); Reg2 = false;}
        else {passw1.setText("Вы можете исспользовать этот пароль"); Reg2 = true;}
    }

    @FXML
    void passw2Scan(KeyEvent event) {
        password1 = PasW2.getText();
        if (!password1.equals(password2)) {passw2.setText("Пароли не совпадают"); Reg3 = false;}
        else {passw2.setText("Пароли совпали"); Reg3 = true;}
    }

    public void registerMain(int empID) {
        if (Reg2 && Reg3) {
            String first_name = first_name1.getText();
            String middle_name = middle_name1.getText();
            String last_name = last_name1.getText();
            String phone_number = phone_number1.getText();
            String sex = sex1.getText();
            String appointment = appointment1.getText();
            if (new Employee(empID, password1, first_name, middle_name, last_name, phone_number, sex, appointment, branch_id).insertEmployee())
                RegFinal.setText("Регистрация успешна! Войдите в свой аккаунт через главное меню.");
            else {RegFinal.setText("Пожалуйста заполните все поля");}
        }
        else {RegFinal.setText("Пожалуйста заполните все поля");}
    }


    @FXML @Override
    void back(ActionEvent event) {
        stage.setScene(this.scene);
        stage.show();
    }

    public void clickMouseBranch(MouseEvent mouseEvent) {
        String action = (String) listBranches.getSelectionModel().getSelectedItem();
        if(action != null)
            branch_id = Integer.parseInt(action);
    }
}