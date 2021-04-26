package sample.controller.dateandclock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class widgetDateAndClockV6 implements Initializable {

    public Label hourClock2;
    public Label minuteClock2;

    String[] oneWeek = {
            "Sunday"
            , "Monday"
            , "Tuesday"
            , "Wednesday"
            , "Thursday"
            , "Friday"
            , "Saturday"};
    String[] oneYear = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    public void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            String hourText = "00" + currentTime.getHour();
            String minutesText = "00" + (currentTime.getMinute());
            hourText = hourText.substring(hourText.length() - 2);
            minutesText = minutesText.substring(minutesText.length() - 2);
            hourClock2.setText(hourText);
            minuteClock2.setText(minutesText);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTime();
    }
}
