package sample.controller.note;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.controller.Controller;
import sample.item.Note;
import sample.main.MyListener;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

import static sample.controller.daycounter.DaycounterController.dragWidget;
import static sample.controller.note.WriteNote.setTextNote;

public class NotePageController extends Controller implements Initializable {

    public JFXButton newNoteButton;
    String[] listNoNoteText = {"No notes to show here!", "Have you noted yet?", "Let's note!", "You haven't noted yet!", "Come and take a note!"};
    public BorderPane mainNote;

    public GridPane mainGrid;
    public AnchorPane notePageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;
    public Label noNoteShowText;
    public ScrollPane scrollMainGrid;
    private FileWriter file;
    private MyListener myListener, moveNoteToBin;
    private List<Note> notes;
    private File fileCheck = new File("FileNote/noteDemo.json");
    private Date fileModified = new Date(fileCheck.lastModified());

    public void setFadeTransitionNotePage() {
        FadeTransition mainPaneFade = new FadeTransition(Duration.millis(500), notePageMainPane);
        mainPaneFade.setFromValue(0);
        mainPaneFade.setToValue(9);
        mainPaneFade.play();

        Label[] labels = {mainLabel, mainDescriptionLabel};
        for (Label label : labels) {
            TranslateTransition labelTrans = new TranslateTransition();
            labelTrans.setByX(2);
            labelTrans.setDuration(Duration.millis(1200));
            labelTrans.setNode(label);
            labelTrans.play();
        }
    }

    public void setTooltipNotePage() {
        Tooltip.install(newNoteButton, new Tooltip("Add a new note"));
        Tooltip.install(noNoteShowText, new Tooltip("You haven't noted yet or maybe some notes are being opened on Desktop"));
    }

    public void setTransitionMainGrid() {
        ScaleTransition trans = new ScaleTransition(Duration.millis(900), mainGrid);
        trans.setFromX(0.97);
        trans.setToX(1);
        trans.setFromY(0.97);
        trans.setToY(1);
        trans.play();

        FadeTransition fadeMainPane = new FadeTransition(Duration.millis(2000), mainGrid);
        fadeMainPane.setFromValue(0);
        fadeMainPane.setToValue(9);
        fadeMainPane.play();
    }

    public static List<Note> getData() {
        // OPEN FILE JSON AND READ WITH SPECIFIC NUMBER OF COUNT
        JSONParser parser = new JSONParser();
        JSONArray noteArray = null;
        try {
            Object object = parser.parse(new FileReader("FileNote/noteDemo.json"));
            noteArray = (JSONArray) object;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        List<Note> notes = new ArrayList<>();
        Note note;
        for (int i = noteArray.size() - 1; i >= 0; i--) {
            String pureText;
            JSONObject noteObject = (JSONObject) noteArray.get(i);
            pureText = (String) noteObject.get("Description");
            String day = (String) noteObject.get("Day");
            note = new Note(pureText);
            note.setSaveDate(day);
            notes.add(note);
        }
        return notes;
    }

    public void openData() {
        // เพิ่มข้อมูลทุกอย่างที่มี ใส่ลงไปในลิสต์
        notes = new ArrayList<>();
        notes.addAll(getData());
        if (notes.size() > 0) {
            //setChosenNote(notes.get(0));
            scrollMainGrid.setVisible(true);
            myListener = new MyListener() {
                @Override
                public void onClickListener(Note note) {
                    setChosenNote(note);
                }
            };
            moveNoteToBin = new MyListener() {
                @Override
                public void onClickListener(Note note) {
                    deleteNote(note);
                }
            };
        }
        int columns = 0;
        int row = 1;
        if (notes.size() == 0) {
            scrollMainGrid.setVisible(false);
            Random r = new Random();
            noNoteShowText.setText(listNoNoteText[r.nextInt(listNoNoteText.length)]);
        }
        //นำข้อมูลลงไปเรียงใน Grid Pane
        for (int i = 0; i < notes.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("../../views/addNote.fxml")));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                NoteControl noteController = fxmlLoader.getController();
                noteController.setData(notes.get(i), myListener, moveNoteToBin);
                if (columns == 4) {
                    columns = 0;
                    row++;
                }
                mainGrid.add(anchorPane, columns++, row); // (child , columns , row)
                GridPane.setMargin(anchorPane, new Insets(15));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(columns + "," + (row - 1));
        if (notes.size() == 0) {
            scrollMainGrid.setVisible(false);
            Random r = new Random();
            noNoteShowText.setText(listNoNoteText[r.nextInt(listNoNoteText.length)]);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransitionNotePage();
        setTransitionMainGrid();
        setTooltipNotePage();
        openData();
        checkFileChange();
    }

    private void setChosenNote(Note note) {
        deleteNote(note);
        setTextNote(note.getDescription());
        openNote();
    }

    private void deleteNote(Note note) {
        JSONObject obj = new JSONObject();
        obj.put("Description", note.getDescription());
        obj.put("Day", note.getSaveDate());

        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader("FileNote/noteDemo.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println(obj);
        JSONArray noteArray = (JSONArray) object;
        for (int i = 0; i < noteArray.size(); i++) {
            if (noteArray.get(i).equals(obj)) {
                noteArray.remove(i);
            }
        }

        //Save by new
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("FileNote/noteDemo.json");
            file.write(noteArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openNote() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/showNote.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Note");
            dragWidget(root1, stage);
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNote(MouseEvent mouseEvent) {
        System.out.println("New Note Created!");
        setTextNote("");
        openNote();
    }

    private void clearData() {
        mainGrid.getChildren().clear();
    }

    public void checkFileChange() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            File file = fileCheck;
            if (file.exists()) {
                Date lastModified = new Date(file.lastModified());
                if (!fileModified.equals(lastModified)) {
                    clearData();
                    openData();
                    fileModified = lastModified;
                }
            }

        }),
                new KeyFrame(Duration.seconds(0.1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
