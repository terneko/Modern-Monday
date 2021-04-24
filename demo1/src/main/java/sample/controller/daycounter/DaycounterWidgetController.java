package sample.controller.daycounter;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class DaycounterWidgetController implements Initializable {
    public ImageView imageChange;
    public Label dayLabel;
    public Label title;
    public static String dayLeft;
    public static String staticTitle;
    public static String imagePath;

    public static void setDaycounter(long day, String texTitle,String imagePath) {
        DaycounterWidgetController.dayLeft = String.valueOf(day);
        DaycounterWidgetController.staticTitle = texTitle;
        DaycounterWidgetController.imagePath = imagePath;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dayLabel.setText(dayLeft);
        title.setText(staticTitle);
        System.out.println("YO +>"+imagePath);
        imageChange.setImage(new Image(imagePath));
    }
}
