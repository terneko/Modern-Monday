package sample.controller.daycounter;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.controller.Controller;
import sample.item.DayCounter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DayCounterAddItemController {
    public ImageView showImage;
    public BorderPane bdPane;
    public TextField eventTitle;
    public DatePicker datePicker;
    public TextField descripeEvent;
    File selectedFile = null;

    public void openImageChoose(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            Path imageFile = Paths.get(selectedFile.getAbsolutePath());
            showImage.fitWidthProperty();
            try {
                showImage.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            //ImageView img = new ImageView(String.valueOf(imageFile));
           // bdPane.setCenter(img);
        }
    }



    public void addEvent(MouseEvent mouseEvent) {
        DayCounter dayCounter = new DayCounter(eventTitle.getText(),datePicker.getValue(),descripeEvent.getText(), selectedFile.getAbsolutePath());
        System.out.println(dayCounter);
        if (dayCounter.getTitle() != ""){
            dayCounter.saveJSON();
        }
    }
}
