package sample.controller.daycounter;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.item.DayCounter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.Date;
import java.util.ResourceBundle;

import static sample.item.DayCounter.calculationDayLeft;

public class DayCounterEditItemController implements Initializable {
    public ImageView imageChange;
    public Label dayLabel;
    public static long dayLeft;
    public static String staticTitle;
    public static String imagePath;
    public static String description;
    public static LocalDate dayEnd;
    public Label title;
    public Label dayLeftLabel;
    public Pane sepLine;
    public AnchorPane editPane;
    public TextField eventTitle;
    public DatePicker datePicker;
    public TextField describeEvent;
    public ImageView showImage;
    private FileWriter file;
    private File selectedFile;
    private static DayCounter currentDayCounter;

    public static void editDaycounter(long day, String texTitle, String imagePath, String description, LocalDate dayEnd) {
        DayCounterEditItemController.dayLeft = day;
        DayCounterEditItemController.staticTitle = texTitle;
        DayCounterEditItemController.imagePath = imagePath;
        DayCounterEditItemController.description = description;
        DayCounterEditItemController.dayEnd = dayEnd;
        currentDayCounter = new DayCounter(texTitle, dayEnd, description, imagePath);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (dayLeft > 1) {
            dayLeftLabel.setText(String.valueOf(dayLeft) + " days");
        } else if (dayLeft == 1) {
            dayLeftLabel.setText(String.valueOf(dayLeft) + " day");
        } else if (dayLeft == 0){
            dayLeftLabel.setText("Today!");
        } else {
            dayLeftLabel.setText(String.valueOf((-1)*dayLeft) + " days ago");
        }
        title.setText(staticTitle);
        eventTitle.setText(staticTitle);
        datePicker.setValue(dayEnd);
        describeEvent.setText(description);
        Path imageFile = Path.of(imagePath);
        try {
            showImage.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        Path imageFile = Path.of(imagePath);
//        try {
//            showImage.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

    public void typeTitle(KeyEvent event) {
        title.setText(eventTitle.getText());
    }

    public void setDayLeft(ActionEvent actionEvent) {
        long dayLeft = calculationDayLeft(datePicker.getValue());
        if (dayLeft > 1) {
            dayLeftLabel.setText(String.valueOf(dayLeft) + " days");
        } else if (dayLeft == 1) {
            dayLeftLabel.setText(String.valueOf(dayLeft) + " day");
        } else if (dayLeft == 0){
            dayLeftLabel.setText("Today!");
        } else {
            dayLeftLabel.setText(String.valueOf((-1)*dayLeft) + " days ago");
        }
    }

    public void addEvent(MouseEvent mouseEvent) {
        deleteJSON(currentDayCounter);
        saveJSON();
        close();
    }


    public void openImageChoose(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        String currentPath = Paths.get("./src/main/resources/DayCounterPicture").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Path imageFile = Paths.get(selectedFile.getAbsolutePath());
            imagePath = String.valueOf(Paths.get(selectedFile.getAbsolutePath()));
            System.out.println("This is picture that you choose"+imagePath);
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

    public void close() {
        Stage stage = (Stage) editPane.getScene().getWindow();
        stage.close();
    }

    public void closeEdit(MouseEvent mouseEvent) {
        close();
    }

    public void saveJSON() {
        JSONArray noteArray = openJSON();
        JSONObject obj = new JSONObject();
        obj.put("Title", eventTitle.getText());
        obj.put("Description", describeEvent.getText());
        obj.put("DayEnd", String.valueOf(datePicker.getValue()));
        obj.put("ImagePath", imagePath);
        obj.put("DayLeft", dayLeft);
        noteArray.add(obj);
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("FileNote/dayCounterNote.json");
            file.write(noteArray.toJSONString());
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

    public void deleteDayCounter(MouseEvent mouseEvent) {
        deleteJSON(currentDayCounter);
        close();
    }

    public void deleteJSON(DayCounter dayCounter) {
        JSONObject obj = new JSONObject();
        obj.put("Title", dayCounter.getTitle());
        obj.put("Description", dayCounter.getDescription());
        obj.put("DayEnd", String.valueOf(dayCounter.getDayEnd()));
        obj.put("ImagePath", dayCounter.getImageFilePath());
        obj.put("DayLeft", dayCounter.getDayLeft());
        System.out.println(obj);
        JSONArray noteArray = openJSON();
        for (int i = 0; i < noteArray.size(); i++) {
            if (noteArray.get(i).equals(obj)) {
                noteArray.remove(i);
            }
        }
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("FileNote/dayCounterNote.json");
            file.write(noteArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
