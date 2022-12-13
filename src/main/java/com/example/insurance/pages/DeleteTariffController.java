package com.example.insurance.pages;

import com.example.insurance.entities.ConnectorDB;
import com.example.insurance.entities.Tariff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DeleteTariffController extends FXbasic {
    public TableView<Tariff> tariffTable;
    public TableColumn<Tariff, Integer> idColumn;
    public TableColumn<Tariff, String> titleColumn;
    public TableColumn<Tariff, Float> yearH_IRColumn;
    public TableColumn<Tariff, Float> year_IRColumn;
    public TableColumn<Tariff, Float> year3_IRColumn;
    public Text outputInfo;
    private Tariff selectedTariff;
    private Boolean isSelected = false;

    public ObservableList<Tariff> tableData = FXCollections.observableArrayList();

    public void setdTC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @FXML
    void initialize() throws SQLException {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Tariff, String>("title"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Tariff, Integer>("ID"));
        yearH_IRColumn.setCellValueFactory(new PropertyValueFactory<Tariff, Float>("yearH_IR"));
        year_IRColumn.setCellValueFactory(new PropertyValueFactory<Tariff, Float>("year_IR"));
        year3_IRColumn.setCellValueFactory(new PropertyValueFactory<Tariff, Float>("year3_IR"));
        String query = "SELECT * FROM Tariff";
        ResultSet resObj = ConnectorDB.getStatement().executeQuery(query);
        while (resObj.next()){
            Tariff tariff = new Tariff(resObj.getInt("Tariff_id"), resObj.getString("title"), resObj.getFloat("yearH_IR"), resObj.getFloat("year_IR"), resObj.getFloat("year3_IR"));
            tableData.add(tariff);
        }
        tariffTable.setItems(tableData);
    }

    @FXML
    void clickMouseTable(MouseEvent event) {
        Tariff selected = tariffTable.getSelectionModel().getSelectedItem();
        if(selected != null) {selectedTariff = selected; isSelected = true;}
        else isSelected = false;
        System.out.println(selected);
    }

    @FXML
    private void deleteTariff(){
        try{
            if(isSelected){
                if (!ConnectorDB.ifExist("SELECT * FROM Insurance_contract WHERE Tariff_id="+ selectedTariff.getID())){
                    String query = "DELETE FROM Tariff WHERE Tariff_id=" + selectedTariff.getID();
                    ConnectorDB.getStatement().executeUpdate(query);
                    tableData.remove(selectedTariff);
                    Logger.getGlobal().info("Тариф " + selectedTariff.getID() + " успешно удален!");
                }
                else{
                    outputInfo.setText("Тариф " + selectedTariff.getID() + " упоминается в договорах и не может быть удален!");
                    Logger.getGlobal().info("Тариф " + selectedTariff.getID() + " упоминается в договорах и не может быть удален!");
                }
            }

        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(0);
        }
    }

    @FXML
    @Override
    void back(ActionEvent event) {
        stage.setScene(this.scene);
        stage.show();
    }

}

