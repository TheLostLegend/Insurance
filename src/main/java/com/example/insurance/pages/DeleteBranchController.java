package com.example.insurance.pages;

import com.example.insurance.entities.Branch;
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

public class DeleteBranchController extends FXbasic {
    public TableView<Branch> branchTable;
    public TableColumn<Branch, Integer> idColumn;
    public TableColumn<Branch, String> titleColumn;
    public Text outputInfo;
    public TableColumn<Branch, String> addressColumn;
    public TableColumn<Branch, String> phone_numberColumn;
    private Branch selectedBranch;
    private Boolean isSelected = false;

    public ObservableList<Branch> tableData = FXCollections.observableArrayList();

    public void setdTC(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    @FXML
    void initialize() throws SQLException {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Branch, String>("branch_name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Branch, Integer>("branch_id"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Branch, String>("address"));
        phone_numberColumn.setCellValueFactory(new PropertyValueFactory<Branch, String>("phone_number"));

        String query = "SELECT * FROM Branch";
        ResultSet resObj = ConnectorDB.getStatement().executeQuery(query);
        while (resObj.next()){
            Branch branch = new Branch(resObj.getInt("branch_id"), resObj.getString("branch_name"), resObj.getString("address"), resObj.getString("phone_number"));
            tableData.add(branch);
        }
        branchTable.setItems(tableData);
    }

    @FXML
    void clickMouseTable(MouseEvent event) {
        Branch selected = branchTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            selectedBranch = selected; isSelected = true;}
        else isSelected = false;
        System.out.println(selected);
    }

    @FXML
    private void deleteBranch(){
        try{
            if(isSelected){
                if (!ConnectorDB.ifExist("SELECT * FROM Employee WHERE branch_id="+ selectedBranch.getBranch_id())){
                    String query = "DELETE FROM Branch WHERE branch_id=" + selectedBranch.getBranch_id();
                    ConnectorDB.getStatement().executeUpdate(query);
                    tableData.remove(selectedBranch);
                    outputInfo.setText("Филиал " + selectedBranch.getBranch_id() + " успешно удален!");
                    Logger.getGlobal().info("Филиал " + selectedBranch.getBranch_id() + " успешно удален!");
                }
                else{
                    outputInfo.setText("Филиал " + selectedBranch.getBranch_id() + " упоминается в таблице сотрудников и не может быть удален!");
                    Logger.getGlobal().info("Филиал " + selectedBranch.getBranch_id() + " упоминается в таблице сотрудников и не может быть удален!");
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

