module com.mycompany.proyectoestructurasp2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyectoestructurasp2 to javafx.fxml;
    exports com.mycompany.proyectoestructurasp2;
}
