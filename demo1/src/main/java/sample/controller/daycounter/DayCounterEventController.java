package sample.controller.daycounter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.controller.Controller;
import sample.controller.note.NoteControl;
import sample.controller.note.NotePageController;
import sample.item.DayCounter;
import sample.item.Note;
import sample.main.DayCounterListener;
import sample.main.MyListener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static sample.controller.daycounter.DaycounterController.dragWidget;
import static sample.controller.daycounter.DaycounterWidgetController.setDaycounter;

public class DayCounterEventController extends Controller implements Initializable {

    public GridPane incomeGrid;
    public GridPane pastGrid;
    private File fileCheck = new File("FileNote/dayCounterNote.json");
    private Date fileModified = new Date(fileCheck.lastModified());
    private List<DayCounter> dayCounterList;
    private DayCounterListener myListener;
    private DayCounterListener moveNoteToBin;

    public void checkFileChange() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            // File file = new File("FileNote/noteDemo.json");
            File file = fileCheck;
            if (file.exists()) {
                Date lastModified = new Date(file.lastModified());
                if (!fileModified.equals(lastModified)) {
                    //statement
                    //clearData();
                    openData();
                    loadPage("daycounterShowInfoPage");
                    fileModified = lastModified;
                }
            }
        }),
                new KeyFrame(Duration.seconds(0.1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    static List<DayCounter> getData() {
        // OPEN FILE JSON AND READ WITH SPECIFIC NUMBER OF COUNT
        JSONParser parser = new JSONParser();
        JSONArray dayCounter = null;
        try {
            Object object = parser.parse(new FileReader("FileNote/dayCounterNote.json"));
            dayCounter = (JSONArray) object;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        List<DayCounter> dayCounterList = new ArrayList<>();
        DayCounter simpleDayCounter;
        for (int i = dayCounter.size() - 1; i >= 0; i--) {
            JSONObject dayCounterObject = (JSONObject) dayCounter.get(i);
            String title = (String) dayCounterObject.get("Title");
            LocalDate dayEnd = LocalDate.parse((String) dayCounterObject.get("DayEnd"));
            ;
            String description = (String) dayCounterObject.get("Description");
            String imageFilePath = (String) dayCounterObject.get("ImagePath");
            simpleDayCounter = new DayCounter(title, dayEnd, description, imageFilePath);
            dayCounterList.add(simpleDayCounter);
        }
        return dayCounterList;
    }

    public void openData() {
        // เพิ่มข้อมูลทุกอย่างที่มี ใส่ลงไปในลิสต์
        dayCounterList = new ArrayList<>();
        dayCounterList.addAll(getData());
        System.out.println(dayCounterList);
        if (dayCounterList.size() > 0) {
            //setChosenNote(notes.get(0));
            myListener = new DayCounterListener() {
                @Override
                public void onClickListener(DayCounter dayCounter) {
                    setChosenNote(dayCounter);
                }
            };
            moveNoteToBin = new DayCounterListener() {
                @Override
                public void onClickListener(DayCounter dayCounter) {
                    //deleteNote(dayCounter);
                }
            };
        }
        int columnsLeft = 0;
        int rowLeft = 0;
        int columnsRight = 0;
        int rowRight = 0;
        //นำข้อมูลลงไปเรียงใน Grid Pane
//        if (dayCounterList.size() == 0) {
//            scrollMainGrid.setVisible(false);
//            Random r = new Random();
//            noNoteShowText.setText(listNoNoteText[r.nextInt(listNoNoteText.length)]);
//        }
//        if (notes.size() > 0) {
//            scrollMainGrid.setVisible(true);
//        }
        for (int i = 0; i < dayCounterList.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("../../views/daycounter/addDayCounter.fxml")));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                DayCounterItemController dayCounterItemController = fxmlLoader.getController();
                dayCounterItemController.setData(dayCounterList.get(i), myListener, moveNoteToBin);
                if (dayCounterList.get(i).getDayLeft() >= 0) {
                    incomeGrid.add(anchorPane, columnsLeft, rowLeft++);
                } else {
                    pastGrid.add(anchorPane, columnsRight, rowRight++); // (child , columns , row)
                }
                GridPane.setMargin(anchorPane, new Insets(0,2,5,2));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setChosenNote(DayCounter dayCounter) {
//        deleteNote(note);
        setDaycounter(dayCounter.getDayLeft(), dayCounter.getTitle(), dayCounter.getImageFilePath());
        openDaycounter();
    }

    public void openDaycounter() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/daycounterWidget.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Day Counter");
            dragWidget(root1, stage);
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            //addOrDelCount(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openData();
    }
}
