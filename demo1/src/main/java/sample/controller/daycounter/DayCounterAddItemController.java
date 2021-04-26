package sample.controller.daycounter;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.item.DayCounter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import sample.item.DayCounter.*;
import sample.item.Note;

import static sample.item.DayCounter.calculationDayLeft;

public class DayCounterAddItemController {
    public ImageView showImage;
    public BorderPane bdPane;
    public TextField eventTitle;
    public DatePicker datePicker;
    public TextField descripeEvent;
    public Label title;
    public Label dayLeft;
    public Pane sepLine;
    public AnchorPane editPane;
    File selectedFile = null;
    private FileWriter file;

    public void openImageChoose(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        String currentPath = Paths.get("./src/main/resources/DayCounterPicture").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
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
        String title = eventTitle.getText();
        LocalDate dayEnd = datePicker.getValue();
        String description = descripeEvent.getText();
        String filePath;
        String text = "";
        try {
            filePath = selectedFile.getAbsolutePath();
        } catch (NullPointerException e) {
            filePath = showImage.getImage().getUrl().substring(6);
            for (int i = 0; i < filePath.length(); i++) {
                if (filePath.charAt(i) == '/') {
                    text += "\\";
                } else {
                    text += filePath.charAt(i);
                }
            }
            filePath=text;
        }
        DayCounter dayCounter = new DayCounter(title, dayEnd, description,filePath);
        if (dayCounter.getTitle() != "") {
            dayCounter.saveJSON();
        }
    }

    public void setDayLeft(ActionEvent actionEvent) {
        calculationDayLeft(datePicker.getValue());
        if (calculationDayLeft(datePicker.getValue()) > 1) {
            dayLeft.setText(calculationDayLeft(datePicker.getValue()) + " days");
        } else if (calculationDayLeft(datePicker.getValue()) == 1) {
            dayLeft.setText(calculationDayLeft(datePicker.getValue()) + " day");
        } else if (calculationDayLeft(datePicker.getValue()) == 0){
            dayLeft.setText("Today!");
        } else {
            dayLeft.setText(String.valueOf((-1)*calculationDayLeft(datePicker.getValue())) + " days ago");
        }
    }

    public void typeTitle(KeyEvent event) {
        title.setText(eventTitle.getText());
    }

    public void deleteDayCounter(MouseEvent mouseEvent) {

    }

    private void delete(DayCounter dayCounter) {
        JSONArray dayCounterArray = openJSON();
        for (int i = 0; i < dayCounterArray.size(); i++) {
            if (dayCounterArray.get(i).equals(dayCounter)) {
                dayCounterArray.remove(i);
            }
        }
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("FileNote/dayCounterNote.json");
            file.write(dayCounterArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray openJSON() {
        JSONParser parser = new JSONParser();
        JSONArray noteArray = null;
        try {
            Object object = parser.parse(new FileReader("FileNote/dayCounterNote.json"));
            noteArray = (JSONArray) object;
            for (int i = 0; i < noteArray.size(); i++) {
                System.out.println(noteArray.get(i));
                JSONObject noteObject = (JSONObject) noteArray.get(i);
                String title = (String) noteObject.get("Title");
                System.out.println(title);
                String description = (String) noteObject.get("Description");
                System.out.println(description);
                String dayEnd = (String) noteObject.get("End");
                System.out.println(dayEnd);
            }
        } catch (org.json.simple.parser.ParseException | IOException e) {
            e.printStackTrace();
        }
        return noteArray;
    }

    public void closeEdit(MouseEvent mouseEvent) {
        Stage stage = (Stage) editPane.getScene().getWindow();
        stage.close();
    }
}
