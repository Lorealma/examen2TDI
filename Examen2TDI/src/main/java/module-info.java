module com.example.examen2tdi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;
    requires jasperreports;


    opens com.example.examen2tdi to javafx.fxml;
    exports com.example.examen2tdi;
}