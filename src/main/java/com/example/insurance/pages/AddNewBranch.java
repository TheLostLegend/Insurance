package com.example.insurance.pages;

import com.example.insurance.MAINDOOR;
import com.example.insurance.entities.MenuItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class AddNewBranch implements MenuItem {
    Stage stage;
    Scene scene;

    public AddNewBranch(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @Override
    public String getInformation() {
        return "Добавить новый филиал";
    }

    @Override
    public void execute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("addNewBranch_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getInformation());
        stage.show();
        AddNewBranchController contr = loader.getController();
        contr.setABC(this.scene, stage);
        Logger.getGlobal().info("Пользователь перешел в окно \"Добавить новый филиал\"");
    }
}
