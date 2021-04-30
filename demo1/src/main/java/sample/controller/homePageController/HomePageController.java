package sample.controller.homePageController;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public Label welcomeText;
    //String[] listWelcomeText = {"What's CRAKINN?, my friend.","เพิ่มข้อความตรงนี้"};
    public Label settingsLabel;
    public AnchorPane mainPane;
    public TextField settingsTextField;


    String[] listWelcomeText = {"Did you know lemons contain more sugar than strawberries",
            "Did you know cats spend 66% of their life asleep",
            "Did you know a 1/4 of your bones are in your feet",
            "Did you know a crocodile can't move its tongue",
            "Did you know a snail can sleep for 3 years",
            "Did you know reindeer like bananas",
            "Did you know cows don't have upper front teeth",
            "Did you know everyday is a holiday somewhere in the world",
            "Did you know household dust is made of dead skin cells"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransition();
        setTooltipHomePage();
        //setTranslateTransition();
        setScaleTransition();
        welcomeText.setTextFill(Color.WHITE);
        Random r = new Random();
        welcomeText.setText(listWelcomeText[r.nextInt(listWelcomeText.length)]);
    }

    public void setFadeTransition() {
        FadeTransition fadeRandomText = new FadeTransition(Duration.millis(3500), welcomeText);
        fadeRandomText.setFromValue(0);
        fadeRandomText.setToValue(6);
        fadeRandomText.play();

        FadeTransition fadeSettingsLabel = new FadeTransition(Duration.millis(2000), settingsLabel);
        fadeSettingsLabel.setFromValue(0);
        fadeSettingsLabel.setToValue(9);
        fadeSettingsLabel.play();

        FadeTransition fadeSettingsTextField = new FadeTransition(Duration.millis(2000), settingsTextField);
        fadeSettingsTextField.setFromValue(0);
        fadeSettingsTextField.setToValue(9);
        fadeSettingsTextField.play();

        FadeTransition fadeMainPane = new FadeTransition(Duration.millis(1000), mainPane);
        fadeMainPane.setFromValue(0);
        fadeMainPane.setToValue(9);
        fadeMainPane.play();
    }

    public void setTooltipHomePage() {
        Tooltip.install(welcomeText, new Tooltip("Are you gon' believe me?, search it yourself, lol"));
    }

    public void setTranslateTransition() {
        TranslateTransition translate = new TranslateTransition();

        translate.setByY(20);
        translate.setByX(-20);

        translate.setDuration(Duration.millis(1200));
        translate.setNode(welcomeText);
        translate.play();
    }

    public void setScaleTransition() {
        /*ScaleTransition trans = new ScaleTransition(Duration.seconds(1.5), settingsTextField);
        trans.setFromX(0.98);
        trans.setToX(1);
        trans.setFromY(0.98);
        trans.setToY(1);
        trans.play();

        ScaleTransition trans2 = new ScaleTransition(Duration.seconds(1.5), settingsLabel);
        trans2.setFromX(0.98);
        trans2.setToX(1);
        trans2.setFromY(0.98);
        trans2.setToY(1);
        trans2.play();*/

        ScaleTransition trans3 = new ScaleTransition(Duration.seconds(1.5), welcomeText);
        trans3.setFromX(0.99);
        trans3.setToX(1);
        trans3.setFromY(0.99);
        trans3.setToY(1);
        trans3.play();

        // Let the animation run forever
        //trans.setCycleCount(ScaleTransition.INDEFINITE);
        // Reverse direction on alternating cycles
        //trans.setAutoReverse(true);
        // Play the Animation
    }

}
