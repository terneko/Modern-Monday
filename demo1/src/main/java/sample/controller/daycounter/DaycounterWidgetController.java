package sample.controller.daycounter;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
