package com.example.insurance.pages;

import com.example.insurance.entities.Client;
import com.example.insurance.entities.ConnectorDB;
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
import java.util.logging.Logger;

public class DeleteClientController extends FXbasic {
    public TableView<Client> clientTable;
    public TableColumn<Client, Integer> idColumn;
    public Text outputInfo;
    public TableColumn<Client, String> addressColumn;
    public TableColumn<Client, String> phone_numberColumn;
    public TableColumn<Client, String> nameColumn;
    public TableColumn<Client, String> name2Column;
    public TableColumn<Client, String> name3Column;
    private Client selectedClient;
    private Boolean isSelected = false;

    public ObservableList<Client> tableData = FXCollections.observableArrayList();

    public void setdTC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @FXML
    void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("passport_id"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
        phone_numberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phone_number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("first_name"));
        name2Column.setCellValueFactory(new PropertyValueFactory<Client, String>("middle_name"));
        name3Column.setCellValueFactory(new PropertyValueFactory<Client, String>("last_name"));

        String query = "SELECT * FROM Client";
        ResultSet resObj = ConnectorDB.getStatement().executeQuery(query);
        while (resObj.next()){
            Client client = new Client(resObj.getInt("passport_id"), resObj.getString("first_name"), resObj.getString("middle_name"), resObj.getString("last_name"), resObj.getString("address"), resObj.getString("phone_number") );
            tableData.add(client);
        }
        clientTable.setItems(tableData);
    }

    @FXML
    void clickMouseTable(MouseEvent event) {
        Client selected = clientTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            selectedClient = selected; isSelected = true;}
        else isSelected = false;
        System.out.println(selected);
    }

    @FXML
    private void deleteClient(){
        try{
            if(isSelected){
                if (!ConnectorDB.ifExist("SELECT * FROM Insurance_contract WHERE passport_id="+ selectedClient.getPassport_id())){
                    String query = "DELETE FROM Client WHERE passport_id=" + selectedClient.getPassport_id();
                    ConnectorDB.getStatement().executeUpdate(query);
                    tableData.remove(selectedClient);
                    outputInfo.setText("Клиент " + selectedClient.getPassport_id() + " успешно удален!");
                    Logger.getGlobal().info("Клиент " + selectedClient.getPassport_id() + " успешно удален!");
                }
                else{
                    outputInfo.setText("Клиент " + selectedClient.getPassport_id() + " упоминается в таблице котрактов и не может быть удален!");
                    Logger.getGlobal().info("Клиент " + selectedClient.getPassport_id() + " упоминается в таблице котрактов и не может быть удален!");
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
        Logger.getGlobal().info("Пользователь вернулся в основное меню");
        stage.setScene(this.scene);
        stage.show();
    }

}

