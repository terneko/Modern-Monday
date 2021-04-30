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

public class widgetTemp4Controller implements Initializable {

    public Label province;
    public Label temperatureLabel;
    public AnchorPane mainPane;
    public ImageView dayNight;
    public ImageView cImage;
    public ImageView fImage;
    public ImageView closeId;


    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void setIconDayAndNight() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.getHour() >= 5 && currentTime.getHour() <= 17) {
            System.out.println(currentTime.getHour());
            dayNight.setImage(new Image("@../../sun.png"));
        } else {
            System.out.println(currentTime.getHour());
            dayNight.setImage(new Image("@../../night.png"));
        }
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
        temperatureLabel.setText(String.valueOf((int) (getTemperature() - 273.15)));
        province.setText(String.valueOf(getLOCATION()).substring(0, String.valueOf(getLOCATION()).length() - 3));
        setIconDayAndNight();
    }

    public void setVisible() {
        closeId.setVisible(false);
        mainPane.setOnMouseEntered(e -> closeId.setVisible(true));
        mainPane.setOnMouseExited(e -> closeId.setVisible(false));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInfomation();
        checkCurrentInformation();
        setVisible();
    }
}
