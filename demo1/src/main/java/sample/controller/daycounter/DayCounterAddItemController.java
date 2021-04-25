package sample.controller.daycounter;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import sample.item.DayCounter;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class DayCounterAddItemController {
    public ImageView showImage;
    public BorderPane bdPane;
    public TextField eventTitle;
    public DatePicker datePicker;
    public TextField descripeEvent;
    public Label title;
    public Label dayLeft;
    public Pane sepLine;
    File selectedFile = null;

    public void openImageChoose(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            Path imageFile = Paths.get(selectedFile.getAbsolutePath());
            System.out.println(imageFile);
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
        String title = eventTitle.getText();
        LocalDate dayEnd = datePicker.getValue();
        String description = descripeEvent.getText();
        String filePath="demo1/src/main/resources/3255898.jpg";
        System.out.println("HELLO"+title+" "+dayEnd+" "+description+" "+filePath);
        try {
            filePath = selectedFile.getAbsolutePath();
        } catch (NullPointerException e){
            filePath = "demo1/src/main/resources/3255898.jpg";
        }
        DayCounter dayCounter = new DayCounter(title,dayEnd,description,filePath);
        System.out.println(dayCounter);
        if (dayCounter.getTitle() != ""){
            dayCounter.saveJSON();
        }
    }

    public void setDayLeft(ActionEvent actionEvent) {

    }

    public void typeTitle(KeyEvent event) {
        title.setText(eventTitle.getText());
    }
}
