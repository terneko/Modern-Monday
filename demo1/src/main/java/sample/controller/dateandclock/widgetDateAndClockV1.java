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

public class widgetDateAndClockV1 implements Initializable {
    public Label day2;
    public Label dayInWeekAndMonthAndYear2;
    public Label dayInweakAndMonthAndDay3;
    public Label year3;
    public Label dayInWeakAndDay4;
    public Label monthAndYear4;
    public Label hClock1;
    public Label minuteClock1;


    public Label dateTop1;
    public Label dateBottom1;

    public Label day2Widget;
    public Label dayInWeekAndMonthAndYear2Widget;

    public Label dayInweakAndMonthAndDay3Widget;
    public Label year3Widget;

    public Label dayInWeakAndDay4Widget;
    public Label monthAndYear4Widget;

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

        dateTop1.setText(String.valueOf(oneWeek[d.getDay()]));
        dateBottom1.setText(String.valueOf(oneYear[d.getMonth()]) + " " + day.format(d));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
    }
}
