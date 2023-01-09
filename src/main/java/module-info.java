module com.example.transactionregister {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.transactionregister to javafx.fxml;
    opens com.example.transactionregister.brain;
    exports com.example.transactionregister;
}