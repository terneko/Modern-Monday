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
import java.util.Random;
import java.util.ResourceBundle;

import static sample.controller.temperature.TemperatureAPI.*;
import static sample.controller.temperature.TemperatureAPI.getLOCATION;

public class widgetTemp2Controller implements Initializable {

    public Label temperatureLabel;
    public AnchorPane mainPane;
    public Pane gradiantPane;
    public Label weather;
    public ImageView dayNight;
    public ImageView cImage;
    public ImageView fImage;
    public ImageView closeId;
    public Pane randomPane;
    private boolean celcius = true;
    private boolean farenheit = false;
    Random r = new Random();


    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    private static String generateColor(Random r) {
        final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] s = new char[7];
        int n = r.nextInt(0x1000000);

        s[0] = '#';
        for (int i = 1; i < 7; i++) {
            s[i] = hex[n & 0xf];
            n >>= 4;
        }
        return new String(s);
    }

    public void setColorPane() {
        Stop[] stops = new Stop[]{new Stop(0, Color.web(generateColor(r))), new Stop(1, Color.web(generateColor(r)))};
        LinearGradient lgcolor = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill bgfill = new BackgroundFill(lgcolor, CornerRadii.EMPTY, Insets.EMPTY);
        gradiantPane.setBackground(new Background(bgfill));
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
        weather.setText(getWeather());
        if (celcius == true) {
            temperatureLabel.setText(String.valueOf((int) (getTemperature() - 273.15)));
            cImage.setOpacity(1);
            fImage.setOpacity(0.5);
        } else {
            temperatureLabel.setText(String.valueOf((int) (((getTemperature() - 273.15) * 9 / 5) + 32)));
            cImage.setOpacity(0.5);
            fImage.setOpacity(1);
        }

        setIconDayAndNight();
    }

    public void setVisibleTrue() {
        closeId.setVisible(true);
        randomPane.setVisible(true);
    }

    public void setVisibleFalse() {
        closeId.setVisible(false);
        randomPane.setVisible(false);
    }

    public void setMouseAction() {
        closeId.setVisible(false);
        randomPane.setVisible(false);
        mainPane.setOnMouseEntered(e -> setVisibleTrue());
        mainPane.setOnMouseExited(e -> setVisibleFalse());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setColorPane();
        setRandomPane();
        setInfomation();
        checkCurrentInformation();
        setMouseAction();
    }

    private void setRandomPane() {
        randomPane.setOnMouseEntered(e -> randomPane.setOpacity(0.8));
        randomPane.setOnMouseExited(e -> randomPane.setOpacity(0.4));
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

    public void randomColorPane(MouseEvent mouseEvent) {
        setColorPane();
    }
}
