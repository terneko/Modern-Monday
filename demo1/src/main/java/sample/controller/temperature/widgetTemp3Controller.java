package sample.controller.temperature;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static sample.controller.temperature.TemperatureAPI.*;
import static sample.controller.temperature.TemperatureAPI.getLOCATION;

public class widgetTemp3Controller implements Initializable {

    public Label temperatureLabel;
    public AnchorPane mainPane;
    public ImageView cImage;
    public ImageView fImage;
    private boolean celcius = true;
    private boolean farenheit = false;


    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void checkCurrentInformation() {
        String lastTemp = String.valueOf((int) (getTemperature() - 273));
        String lastLocation = getLOCATION();
        LocalTime currentTime = LocalTime.now();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            String currentTemp = String.valueOf((int) (getTemperature() - 273));
            String currentLocation = getLOCATION();
            if (lastTemp != currentTemp) {
                setInfomation();
            }
            if (lastLocation != currentLocation) {
                setInfomation();
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void setInfomation() {
        if (celcius == true) {
            temperatureLabel.setText(String.valueOf((int) (getTemperature() - 273.15)));
            cImage.setOpacity(1);
            fImage.setOpacity(0.5);
        } else {
            temperatureLabel.setText(String.valueOf((int) (((getTemperature() - 273.15) * 9 / 5) + 32)));
            cImage.setOpacity(0.5);
            fImage.setOpacity(1);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInfomation();
        checkCurrentInformation();
    }

    public void cClick(MouseEvent mouseEvent) {
        celcius = true;
        farenheit = false;
        setInfomation();
    }

    public void fClick(MouseEvent mouseEvent) {
        celcius = false;
        farenheit = true;
        setInfomation();
    }

}
