package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    public HBox titleBar;

    private double x, y;
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";


    public BorderPane mainPane;

    @FXML
    private JFXButton notesButton;

    @FXML
    private JFXButton dateClockButton;

    @FXML
    private JFXButton dayCounterButton;

    @FXML
    private JFXButton tempButton;

    @FXML
    private JFXButton translatorButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void loadPage(String namePage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/" + namePage + ".fxml")));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(root);
    }

    public void noteClick(MouseEvent mouseEvent) {
        //clear();
        loadPage("notePage");
    }

    public void dateAndClockClicked(MouseEvent mouseEvent) {
        loadPage("dateAndClock");
    }

    public void daycounterClick(MouseEvent mouseEvent) {
        loadPage("daycounterPage");

    }

    public void temperatureClick(MouseEvent mouseEvent) {
        loadPage("temperaturePage");
    }

    public void translateClick(MouseEvent mouseEvent) {
        loadPage("addTraslate");
    }

    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void dragTitle(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setX(mouseEvent.getScreenX() - x);
        primaryStage.setY(mouseEvent.getScreenY() - y);
    }

    public void pressTitle(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    public BorderPane getBorder(){
        return mainPane;
    }
}

