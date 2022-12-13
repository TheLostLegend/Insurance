package com.example.insurance.pages;

import com.example.insurance.entities.ConnectorDB;
import com.example.insurance.entities.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class DeleteContractController extends FXbasic {
    public TableView<Contract> contractTable;
    public TableColumn<Contract, Integer> idColumn;
    public Text outputInfo;
    public TableColumn<Contract, Integer>  ID2Column;
    public TableColumn<Contract, Integer>  ID3Column;
    public TableColumn<Contract, Integer>  ID4Column;
    public TableColumn<Contract, Date>  dateColumn;
    public TableColumn<Contract, Float>  timeColumn;
    public TableColumn<Contract, Float>  interest_rateColumn;
    public TableColumn<Contract, Float>  paymentColumn;
    private Contract selectedContract;
    private Boolean isSelected = false;

    public ObservableList<Contract> tableData = FXCollections.observableArrayList();

    public void setdTC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @FXML
    void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<Contract, Integer>("contract_id"));
        ID2Column.setCellValueFactory(new PropertyValueFactory<Contract, Integer>("employee_id"));
        ID3Column.setCellValueFactory(new PropertyValueFactory<Contract, Integer>("passport_id"));
        ID4Column.setCellValueFactory(new PropertyValueFactory<Contract, Integer>("Tariff_id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Contract, Date>("date_start"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Contract, Float>("time"));
        interest_rateColumn.setCellValueFactory(new PropertyValueFactory<Contract, Float>("interest_rate"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Contract, Float>("payment_amount"));

        String query = "SELECT * FROM Insurance_contract";
        ResultSet resObj = ConnectorDB.getStatement().executeQuery(query);
        while (resObj.next()){
            Contract contract = new Contract(resObj.getInt("contract_id"), resObj.getInt("employee_id"), resObj.getInt("passport_id"), resObj.getInt("Tariff_id"), resObj.getDate("date_start"), resObj.getFloat("time"), resObj.getFloat("interest_rate"), resObj.getFloat("payment_amount"));
            tableData.add(contract);
        }
        contractTable.setItems(tableData);
    }

    @FXML
    void clickMouseTable(MouseEvent event) {
        Contract selected = contractTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            selectedContract = selected; isSelected = true;}
        else isSelected = false;
        System.out.println(selected);
    }

    @FXML
    private void deleteContract(){
        try{
            if(isSelected){
                String query = "DELETE FROM Insurance_contract WHERE contract_id=" + selectedContract.getContract_id();
                ConnectorDB.getStatement().executeUpdate(query);
                tableData.remove(selectedContract);
                outputInfo.setText("Контракт " + selectedContract.getContract_id() + " успешно удален!");
                Logger.getGlobal().info("Контракт " + selectedContract.getContract_id() + " успешно удален!");
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
        Logger.getGlobal().info("Пользователь вернулся в основное меню");
        stage.setScene(this.scene);
        stage.show();
    }

}

