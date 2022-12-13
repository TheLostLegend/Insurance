package com.example.insurance.pages;

import com.example.insurance.MAINDOOR;
import com.example.insurance.entities.MenuItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteBranch implements MenuItem {
    Stage stage;
    Scene scene;

    public DeleteBranch(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @Override
    public String getInformation() {
        return "Удалить/отобразить филиал";
    }

    @Override
    public void execute() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MAINDOOR.class.getResource("deleteBranch_scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getInformation());
        stage.show();
        DeleteBranchController dTC = loader.getController();
        dTC.setdTC(this.scene, stage);
    }
}