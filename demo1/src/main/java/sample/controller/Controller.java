package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.sun.glass.ui.Application;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    public Label welcomeText;
    public TextField settingsTextField;
    public Label settingsLabel;
    public BorderPane mainPane;
    public JFXButton notesButton;
    public Pane notesPane;
    public Pane datePane;
    public Pane dayCounterPane;
    public Pane tempPane;
    public Pane translatorPane;
    public VBox vBox;
    public Label noteDesLabel;

    @FXML
    private JFXButton dateClockButton;

    @FXML
    private JFXButton dayCounterButton;

    @FXML
    private JFXButton tempButton;

    @FXML
    private JFXButton translatorButton;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private Button start;

    @FXML
    private Button stop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransition();
        //setTranslateTransition();
        setScaleTransition();
        setHover();
    }

    public void setHover() {
        Pane[] panes = {notesPane, datePane, dayCounterPane, tempPane, translatorPane};

        for (Pane pane : panes) {
            pane.setOnMouseEntered(e -> pane.setStyle("-fx-background-color: #0a2942"));
            pane.setOnMouseExited(e -> pane.setStyle(""));
        }

        /*noteDesLabel.setOnMouseEntered((MouseEvent event) -> {
            TranslateTransition translateTransition = new TranslateTransition(new Duration(350), noteDesLabel);
            translateTransition.setByX(2);
            translateTransition.play();
        });

        noteDesLabel.setOnMouseExited((MouseEvent event) -> {
            TranslateTransition translateTransition = new TranslateTransition(new Duration(350), noteDesLabel);
            translateTransition.setByX(-2);
            translateTransition.play();
        });*/
    }

    public void setTooltip() {
    }

    public void setFadeTransition() {
        welcomeText.setTextFill(Color.WHITE);

        FadeTransition fadeRandomText = new FadeTransition(Duration.millis(3500), welcomeText);
        fadeRandomText.setFromValue(0);
        fadeRandomText.setToValue(9);
        fadeRandomText.play();

        FadeTransition fadeSettingsLabel = new FadeTransition(Duration.millis(2000), settingsLabel);
        fadeSettingsLabel.setFromValue(0);
        fadeSettingsLabel.setToValue(9);
        fadeSettingsLabel.play();

        FadeTransition fadeSettingsTextField = new FadeTransition(Duration.millis(2000), settingsTextField);
        fadeSettingsTextField.setFromValue(0);
        fadeSettingsTextField.setToValue(9);
        fadeSettingsTextField.play();

        FadeTransition fadeMainPane = new FadeTransition(Duration.millis(1000), mainPane);
        fadeMainPane.setFromValue(0);
        fadeMainPane.setToValue(9);
        fadeMainPane.play();
    }

    public void setTranslateTransition() {
        TranslateTransition translate = new TranslateTransition();

        translate.setByY(20);
        translate.setByX(-20);

        translate.setDuration(Duration.millis(1200));
        translate.setNode(welcomeText);
        translate.play();
    }

    public void setScaleTransition() {
        /*ScaleTransition trans = new ScaleTransition(Duration.seconds(1.5), settingsTextField);
        trans.setFromX(0.98);
        trans.setToX(1);
        trans.setFromY(0.98);
        trans.setToY(1);
        trans.play();

        ScaleTransition trans2 = new ScaleTransition(Duration.seconds(1.5), settingsLabel);
        trans2.setFromX(0.98);
        trans2.setToX(1);
        trans2.setFromY(0.98);
        trans2.setToY(1);
        trans2.play();*/

        ScaleTransition trans3 = new ScaleTransition(Duration.seconds(1.5), welcomeText);
        trans3.setFromX(0.99);
        trans3.setToX(1);
        trans3.setFromY(0.99);
        trans3.setToY(1);
        trans3.play();

        // Let the animation run forever
        //trans.setCycleCount(ScaleTransition.INDEFINITE);
        // Reverse direction on alternating cycles
        //trans.setAutoReverse(true);
        // Play the Animation

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
        loadPage("notePage");
    }

    public void dateAndClockClicked(MouseEvent mouseEvent) {
        loadPage("dateAndClock");
    }

    public void dayCounterClick(MouseEvent mouseEvent) {
        loadPage("dayCounterPage");
    }

    public void temperatureClick(MouseEvent mouseEvent) {
        loadPage("temperaturePage");
    }

    public void translateClick(MouseEvent mouseEvent) {
        loadPage("translatePage");
    }

    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void homeClick() {
        loadPage("Sample");
    }
}

