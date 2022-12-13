module com.example.insurance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens com.example.insurance to javafx.fxml;
    exports com.example.insurance;
    exports com.example.insurance.pages;
    opens com.example.insurance.pages to javafx.fxml;
    exports com.example.insurance.entities;
    opens com.example.insurance.entities to javafx.fxml;
}