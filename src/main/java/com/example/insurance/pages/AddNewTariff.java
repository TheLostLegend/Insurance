package com.example.insurance.pages;

import com.example.insurance.MAINDOOR;
import com.example.insurance.entities.MenuItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class AddNewTariff implements MenuItem {
    Stage stage;
    Scene scene;

    public AddNewTariff(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @Override
    public String getInformation() {
        return "Добавить новый тариф";
    }

    @Override
    public void execute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("addNewTariff_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getInformation());
        stage.show();
        AddNewTariffController aNTC = loader.getController();
        aNTC.setATC(this.scene, stage);
        Logger.getGlobal().info("Пользователь перешел в окно \"Добавить новый тариф\"");
    }
}
