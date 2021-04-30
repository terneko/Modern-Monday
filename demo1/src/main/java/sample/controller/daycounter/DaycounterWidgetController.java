package sample.controller.daycounter;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

import static sample.item.DayCounter.calculationDayLeft;

public class DaycounterWidgetController implements Initializable {
    public ImageView imageChange;
    public Label dayLabel;
    public Label title;
    public static long dayLeft;
    public static String staticTitle;
    public static String imagePath;
    public ImageView closeBox;
    public AnchorPane widgetPane;
    public VBox vboxDCT;

    public void setTransitionWidgetPane() {
//        ScaleTransition trans = new ScaleTransition(Duration.millis(900), imageChange);
//        trans.setFromX(0.97);
//        trans.setToX(1);
//        trans.setFromY(0.97);
//        trans.setToY(1);
//        trans.play();

        FadeTransition fadeMainPane = new FadeTransition(Duration.millis(2000), imageChange);
        fadeMainPane.setFromValue(0);
        fadeMainPane.setToValue(9);
        fadeMainPane.play();

        FadeTransition fade = new FadeTransition(Duration.millis(2000), vboxDCT);
        fade.setFromValue(0);
        fade.setToValue(9);
        fade.play();
    }

    public static void setDaycounter(long day, String texTitle, String imagePath) {
        DaycounterWidgetController.dayLeft = day;
        DaycounterWidgetController.staticTitle = texTitle;
        DaycounterWidgetController.imagePath = imagePath;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (dayLeft > 1) {
            dayLabel.setText(String.valueOf(dayLeft) + " days");
        } else if (dayLeft == 1) {
            dayLabel.setText(String.valueOf(dayLeft) + " day");
        } else if (dayLeft == 0) {
            dayLabel.setText("Today!");
        } else {
            dayLabel.setText(String.valueOf((-1) * dayLeft) + " days ago");
        }
        title.setText(staticTitle);
        Path imageFile = Path.of(imagePath);
        try {
            imageChange.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        closeBox.setVisible(false);
        setVisible();

        setTransitionWidgetPane();
    }

    public void setVisible() {
        widgetPane.setOnMouseEntered(e -> closeBox.setVisible(true));
        widgetPane.setOnMouseExited(e -> closeBox.setVisible(false));
    }

    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) widgetPane.getScene().getWindow();
        stage.close();
    }
}
