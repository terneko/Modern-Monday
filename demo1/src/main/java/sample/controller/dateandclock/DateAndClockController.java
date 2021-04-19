package sample.controller.dateandclock;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;


public class DateAndClockController extends Controller implements Initializable {
    private static final int DELAY = 100;
    public AnchorPane dateAndClockPageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;
    public Label Clock2;

    String time[];
    String hour;
    String munite;
    String sec;

    Calendar calendar;
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    //String time;

    Timer updateTimer;

    public void setTime() {
        Thread thread = new Thread(){

            public void run(){

                Date date;
                while (true) {
                    try {
                    //Clock2.setText(String.valueOf(Time.getTime()));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
//        PauseTransition pause = new PauseTransition(Duration.seconds(1));
//        while (true) {
//            time = timeFormat.format(Calendar.getInstance().getTime());
//            pause.setOnFinished(event -> Clock2.setText(time));
//            pause.play();
//        }
//
//            time = timeFormat.format(Calendar.getInstance().getTime());
//

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        //}
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setTime();
        setFadeTransitionDateAndClockPage();
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


}
