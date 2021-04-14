package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // for Load page FXML name
    private void loadPage(String namePage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/" + namePage + ".fxml")));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(root);
    }

    public void noteClick(MouseEvent mouseEvent) {
        System.out.println("Clicked!");
        //clear();
        loadPage("notePage");
    }

    public void dateClick(MouseEvent mouseEvent) {
    }

    public void clockClick(MouseEvent mouseEvent) {
    }

    public void daycounterClick(MouseEvent mouseEvent) {
        System.out.println("Clicked!");
        loadPage("daycounterPage");

    }

    public void temperatureClick(MouseEvent mouseEvent) {
        loadPage("temperaturePage");

    }

    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void translateClick(MouseEvent mouseEvent) {
    }
}

