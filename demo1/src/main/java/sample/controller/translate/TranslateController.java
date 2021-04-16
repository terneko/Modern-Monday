package sample.controller.translate;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.controller.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends Controller implements Initializable {

    public AnchorPane translatePageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransitionTranslatePage();
    }

    public void setFadeTransitionTranslatePage() {
        FadeTransition mainPaneFade = new FadeTransition(Duration.millis(500), translatePageMainPane);
        mainPaneFade.setFromValue(0);
        mainPaneFade.setToValue(9);
        mainPaneFade.play();

        Label[] labels = {mainLabel, mainDescriptionLabel};
        /*for (Label label : labels) {
            TranslateTransition labelTrans = new TranslateTransition();
            labelTrans.setByX(2);
            labelTrans.setDuration(Duration.millis(1200));
            labelTrans.setNode(label);
            labelTrans.play();
        }*/

        for (Label label : labels) {
            ScaleTransition labelTrans = new ScaleTransition(Duration.seconds(0.6), label);
            labelTrans.setFromX(0.98);
            labelTrans.setToX(1);
            labelTrans.setFromY(0.98);
            labelTrans.setToY(1);
            labelTrans.play();
        }
    }
}
