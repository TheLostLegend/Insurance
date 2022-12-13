package com.example.insurance.pages;

import com.example.insurance.entities.ConnectorDB;
import com.example.insurance.entities.Employee;
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

public class DeleteEmployeeController extends FXbasic {
    public Text outputInfo;
    public TableView<Employee> employeeTable;
    public TableColumn<Employee, Integer> idColumn;
    public TableColumn<Employee, String> phone_numberColumn;
    public TableColumn<Employee, String> nameColumn;
    public TableColumn<Employee, String> name2Column;
    public TableColumn<Employee, String> name3Column;
    public TableColumn<Employee, String> sexColumn;
    public TableColumn<Employee, String> appointmentColumn;
    public TableColumn<Employee, Integer> branchColumn;
    private Employee selectedEmployee;
    private Boolean isSelected = false;
    int employee_id;

    public ObservableList<Employee> tableData = FXCollections.observableArrayList();

    public void setdTC(Scene scene, Stage stage, int employee_id) {
        this.scene = scene;
        this.stage = stage;
        this.employee_id = employee_id;
    }

    @FXML
    void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employee_id"));
        phone_numberColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone_number"));
        branchColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("branch_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("first_name"));
        name2Column.setCellValueFactory(new PropertyValueFactory<Employee, String>("middle_name"));
        name3Column.setCellValueFactory(new PropertyValueFactory<Employee, String>("last_name"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("sex"));
        appointmentColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("appointment"));
        String query = "SELECT * FROM Employee";
        ResultSet resObj = ConnectorDB.getStatement().executeQuery(query);
        while (resObj.next()){
            Employee employee = new Employee(resObj.getInt("employee_id"), resObj.getString("password_emp"), resObj.getString("first_name"), resObj.getString("middle_name"), resObj.getString("last_name"), resObj.getString("phone_number"), resObj.getString("sex"), resObj.getString("appointment"), resObj.getInt("branch_id"));
            tableData.add(employee);
        }
        employeeTable.setItems(tableData);
    }

    @FXML
    void clickMouseTable(MouseEvent event) {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            selectedEmployee = selected; isSelected = true;}
        else isSelected = false;
        System.out.println(selected);
    }

    @FXML
    private void deleteBranch(){
        try{
            if(isSelected){
                if (selectedEmployee.getEmployee_id() == employee_id){
                    outputInfo.setText("Ты не можешь удалить сам себя :3");
                    Logger.getGlobal().info("Ты не можешь удалить сам себя :3");
                }
                else {
                    if (!ConnectorDB.ifExist("SELECT * FROM Insurance_contract WHERE employee_id="+ selectedEmployee.getEmployee_id())){
                        String query = "DELETE FROM Employee WHERE employee_id=" + selectedEmployee.getEmployee_id();
                        ConnectorDB.getStatement().executeUpdate(query);
                        tableData.remove(selectedEmployee);
                        outputInfo.setText("Сотрудник " + selectedEmployee.getEmployee_id() + " успешно удален!");
                        Logger.getGlobal().info("Сотрудник " + selectedEmployee.getEmployee_id() + " успешно удален!");
                    }
                    else{
                        outputInfo.setText("Сотрудник " + selectedEmployee.getEmployee_id() + " упоминается в таблице котрактов и не может быть удален!");
                        Logger.getGlobal().info("Сотрудник " + selectedEmployee.getEmployee_id() + " упоминается в таблице котрактов и не может быть удален!");
                    }
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

