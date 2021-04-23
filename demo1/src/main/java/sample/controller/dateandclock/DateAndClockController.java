package sample.controller.dateandclock;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static sample.controller.daycounter.DaycounterController.dragWidget;

public class DateAndClockController extends Controller implements Initializable {
    public AnchorPane dateAndClockPageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;
    public Label hourClock1;
    public Label minuteClock1;
    public Label hourClock2;
    public Label minuteClock2;
    public Label hourClock3;
    public Label minuteClock3;
    public Label Clock3;
    public Label Clock4;
    public Label dayInWeek1;
    public Label monthAndDay1;
    public Label day2;
    public Label dayInWeekAndMonthAndYear2;
    public Label dayInweakAndMonthAndDay3;
    public Label year3;
    public Label dayInWeakAndDay4;
    public Label monthAndYear4;
    public Label Clock2;
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
            hourClock1.setText(hourText);
            minuteClock1.setText(minutesText);
            hourClock2.setText(hourText);
            minuteClock2.setText(minutesText);
            hourClock3.setText(hourText);
            minuteClock3.setText(minutesText);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        setTime();
        setFadeTransitionDateAndClockPage();
    }

    private void setDate() {
        Date d = new Date();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        dayInWeek1.setText(String.valueOf(oneWeek[d.getDay()]));
        monthAndDay1.setText(String.valueOf(oneYear[d.getMonth()])+" "+day.format(d));
        day2.setText(day.format(d));
        dayInWeekAndMonthAndYear2.setText(oneWeek[d.getDay()].substring(0,3)+", "+oneYear[d.getMonth()]+" "+year.format(d));
        dayInweakAndMonthAndDay3.setText(oneWeek[d.getDay()].substring(0,3)+", "+oneYear[d.getMonth()]+day.format(d));
        year3.setText(year.format(d));
        dayInWeakAndDay4.setText(oneWeek[d.getDay()].substring(0,3)+", "+day.format(d));
        monthAndYear4.setText(oneYear[d.getMonth()]+" "+year.format(d));

    }

    public void setFadeTransitionDateAndClockPage() {
        FadeTransition mainPaneFade = new FadeTransition(Duration.millis(500), dateAndClockPageMainPane);
        mainPaneFade.setFromValue(0);
        mainPaneFade.setToValue(9);
        mainPaneFade.play();

        Label[] labels = {mainLabel, mainDescriptionLabel};
        for (Label label : labels) {
            TranslateTransition labelTrans = new TranslateTransition();
            labelTrans.setByX(2);
            labelTrans.setDuration(Duration.seconds(0.7));
            labelTrans.setNode(label);
            labelTrans.play();
        }
    }

    public void openWidget(String nameWidget){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/date/"+nameWidget+".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("");
            dragWidget(root1, stage);
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            //addOrDelCount(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void dateClick1(MouseEvent mouseEvent) {
        openWidget("date1");
    }

    public void dateClick2(MouseEvent mouseEvent) {
    }

    public void dateClick3(MouseEvent mouseEvent) {
    }

    public void dateClick4(MouseEvent mouseEvent) {
    }

    public void clockClick1(MouseEvent mouseEvent) {
    }

    public void clockClick2(MouseEvent mouseEvent) {
    }

    public void clockClick3(MouseEvent mouseEvent) {
    }

    public void clockClick4(MouseEvent mouseEvent) {
    }
}
