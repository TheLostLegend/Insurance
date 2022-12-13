package com.example.insurance.pages;

import com.example.insurance.entities.MenuItem;
import com.example.insurance.pages.AddNewBranch;
import com.example.insurance.pages.AddNewTariff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class UserController extends FXbasic {
    Scene scene;
    Scene scene1;
    Stage stage;

    int employee_id;
    public void setMainC(Scene scene, Scene scene1, Stage stage, int employee_id) {
        this.scene1 = scene1;
        this.scene = scene;
        this.stage = stage;
        this.employee_id = employee_id;

        menuItems.put("Добавить новый тариф", new AddNewTariff(scene1, stage));
        menuItems.put("Добавить новый филиал", new AddNewBranch(scene1, stage));
        menuItems.put("Добавить нового клиента", new AddNewClient(scene1, stage));
        menuItems.put("Создать новый котракт", new AddNewContract(scene1, stage, employee_id));
        menuItems.put("Удалить/отобразить тариф", new DeleteTariff(scene1, stage));
        menuItems.put("Удалить/отобразить филиал", new DeleteBranch(scene1, stage));
        menuItems.put("Удалить/отобразить сотрудника", new DeleteEmployee(scene1, stage, employee_id));
        menuItems.put("Удалить/отобразить клиента", new DeleteClient(scene1, stage));
        menuItems.put("Удалить/отобразить контракт", new DeleteContract(scene1, stage));

        ObservableList<String> list = FXCollections.observableArrayList();
        LinkedHashMap<String, MenuItem> commands = menuItems;
        for(String item : commands.keySet())
            list.add(commands.get(item).getInformation());
        listView.setItems(list);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    private static LinkedHashMap<String, MenuItem> menuItems = new LinkedHashMap<>();

    @FXML
    void clickMouse(MouseEvent event) throws IOException {
        String action = listView.getSelectionModel().getSelectedItem();
        if(action != null){
            MenuItem item = menuItems.get(action);
                if(item != null)
                    menuItems.get(action).execute();
                else
                    System.out.println("Incorrect command! Please, try again.");
        }
    }

    @FXML
    @Override
    void back(ActionEvent event) {
        Logger.getGlobal().info("Пользователь вышел в лес.");
        stage.setScene(this.scene);
        stage.show();
    }
}