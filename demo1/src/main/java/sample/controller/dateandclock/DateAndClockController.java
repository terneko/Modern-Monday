package sample.controller.dateandclock;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.controller.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class DateAndClockController extends Controller implements Initializable {
    public AnchorPane dateAndClockPageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
