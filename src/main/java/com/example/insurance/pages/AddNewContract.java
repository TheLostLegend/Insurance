package com.example.insurance.pages;

import com.example.insurance.MAINDOOR;
import com.example.insurance.entities.MenuItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class AddNewContract implements MenuItem {
    Stage stage;
    Scene scene;
    int employee_id;

    public AddNewContract(Scene scene, Stage stage, int employee_id) {
        this.scene = scene;
        this.stage = stage;
        this.employee_id = employee_id;
    }

    @Override
    public String getInformation() {
        return "Создать новый котракт";
    }

    @Override
    public void execute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("addNewContract_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getInformation());
        stage.show();
        AddNewContractController aNTC = loader.getController();
        aNTC.setATC(this.scene, stage, employee_id);
        Logger.getGlobal().info("Пользователь перешел в окно \"Создать новый договор\"");
    }
}
