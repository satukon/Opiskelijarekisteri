module com.satumaarit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.satumaarit to javafx.fxml;
    exports com.satumaarit;
}
