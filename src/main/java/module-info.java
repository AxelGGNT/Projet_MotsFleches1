module com.example.projet_motsfleches1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.brpi.projet_motsfleches1 to javafx.fxml;
    exports com.brpi.projet_motsfleches1;
}