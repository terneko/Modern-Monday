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

public class widgetDateAndClockV3 implements Initializable {

    public Label dayInweakAndMonthAndDay3Widget;
    public Label year3Widget;

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

    private void setDate() {
        Date d = new Date();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");

        dayInweakAndMonthAndDay3Widget.setText(oneWeek[d.getDay()].substring(0,3)+", "+oneYear[d.getMonth()]+day.format(d));
        year3Widget.setText(year.format(d));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
    }
}
