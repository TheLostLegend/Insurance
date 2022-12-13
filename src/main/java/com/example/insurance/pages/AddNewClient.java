package com.example.insurance.pages;

import com.example.insurance.MAINDOOR;
import com.example.insurance.entities.MenuItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class AddNewClient implements MenuItem {
    Stage stage;
    Scene scene;

    public AddNewClient(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @Override
    public String getInformation() {
        return "Добавить нового клиента";
    }

    @Override
    public void execute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("addNewClient_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getInformation());
        stage.show();
        AddNewClientController contr = loader.getController();
        contr.setABC(this.scene, stage);
        Logger.getGlobal().info("Пользователь перешел в окно \"Добавить нового клиента\"");
    }
}
