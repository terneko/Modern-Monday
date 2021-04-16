package sample.controller.note;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.controller.daycounter.DaycounterController.dragWidget;
import static sample.controller.note.WriteNote.setTextNote;

public class NotePageController extends Controller implements Initializable {
    public BorderPane mainNote;
    public GridPane mainGrid;

    @FXML
    private ScrollPane noteScroll;

    @FXML
    private GridPane noteGrid;

    private List<Note> notes = new ArrayList<>();

    private MyListener myListener;


    private List<Note> getData() {
        // OPEN FILE JSON AND READ WITH SPECIFIC NUMBER OF COUNT
        JSONParser parser = new JSONParser();
        JSONArray noteArray = null;
        try {
            Object object = parser.parse(new FileReader("FileNote/noteDemo.json"));
            noteArray = (JSONArray) object;
            //System.out.println(noteArray.get(number - 1));
            //JSONObject noteObject = (JSONObject) noteArray.get(number - 1);
            //String description = (String) noteObject.get("Description");
            //System.out.println(description);
            //String day = (String) noteObject.get("Day");
            //System.out.println(day);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        List<Note> notes = new ArrayList<>();
        Note note;

        for (int i = 0; i < noteArray.size(); i++) {
            JSONObject noteObject = (JSONObject) noteArray.get(i);
            String description = (String) noteObject.get("Description");
            String day = (String) noteObject.get("Day");
            note = new Note(description);
            note.setSaveDate(day);
            notes.add(note);
        }
        return notes;
    }


    // for delete note
    public void deleteFile(int num) {


    }

    //0 for add and 1 for delete
    public static void addOrDelCount(int keys) {
        Scanner input = null;
        {
            try {
                input = new Scanner(new File("FileNote/Count"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        String newCount;
        int count;
        if (keys == 0) {
            count = input.nextInt() + 1;
        } else {
            count = input.nextInt() - 1;
        }
        newCount = String.valueOf(count);
        try {
            FileWriter myWriter = new FileWriter("FileNote/Count");
            myWriter.write(newCount);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainNote.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // เพิ่มข้อมูลทุกอย่างที่มี ใส่ลงไปใยลิส
        notes.addAll(getData());
        if (notes.size() > 0) {
            //setChosenNote(notes.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Note note) {
                    setChosenNote(note);
                }
            };
        }
        int columns = 0;
        int row = 0;
        //นำข้อมูลลงไปเรียงใน Grid Pane
        for (int i = 0; i < notes.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("../../views/addNote.fxml")));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                NoteControl noteController = fxmlLoader.getController();
                noteController.setData(notes.get(i), myListener);
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
    }

    private void setChosenNote(Note note) {
        System.out.println(note.getSaveDate());
        setTextNote(note.getDescription());
        openNote();
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
            addOrDelCount(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNote(MouseEvent mouseEvent) throws IOException {
        setTextNote("");
        openNote();
        // for Load page FXML name

    }

}
