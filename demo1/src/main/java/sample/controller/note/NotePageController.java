package sample.controller.note;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

import static sample.controller.daycounter.DaycounterController.dragWidget;
import static sample.controller.note.WriteNote.setTextNote;

public class NotePageController extends Controller implements Initializable {
    public BorderPane mainNote;

    public TextArea descripeNote;
    public TextArea textEdit;
    public GridPane mainGrid;
    public AnchorPane notePageMainPane;
    public Label mainLabel;
    public Label mainDescriptionLabel;
    private FileWriter file;

    @FXML
    private ScrollPane noteScroll;

    @FXML
    private GridPane noteGrid;

    private List<Note> notes = new ArrayList<>();

    public void setHoverController() {
        NoteControl noteControl = new NoteControl();
        noteControl.getInputPane().setOnMouseEntered(e -> noteControl.getInputPane().setStyle("-fx-background-color: #51f542"));
        noteControl.getInputPane().setOnMouseExited(e -> noteControl.getInputPane().setStyle("-fx-background-color:  #082033"));
    }

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
        for (int i = noteArray.size() - 1; i >= 0; i--) {
            String pureText;
            String convertText = "";
            JSONObject noteObject = (JSONObject) noteArray.get(i);
            pureText = (String) noteObject.get("Description");
//            //System.out.println(pureText);
//            for (int j = 0; j < pureText.length(); j++) {
//                convertText += pureText.charAt(j);
//                if (j % 13 == 0 && j != 0) {
//                    convertText += "\n";
//                }
//            }
//            System.out.println(convertText);
            //String description = (String) noteObject.get(convertText);
            String day = (String) noteObject.get("Day");
            note = new Note(pureText);
            note.setSaveDate(day);
            notes.add(note);
        }
        //System.out.println(notes);
        return notes;

    }

    // for delete note
    public void deleteFile(int num) {


    }

//    //0 for add and 1 for delete
//    public void addOrDelCount(int keys) {
//        Scanner input = null;
//        {
//            try {
//                input = new Scanner(new File("FileNote/Count"));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        String newCount;
//        int count;
//        if (keys == 0) {
//            count = input.nextInt() + 1;
//        } else {
//            count = input.nextInt() - 1;
//        }
//        newCount = String.valueOf(count);
//        try {
//            FileWriter myWriter = new FileWriter("FileNote/Count");
//            myWriter.write(newCount);
//            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }

    @Override
    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainNote.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFadeTransitionNotePage();
        //setHoverController();
        //NoteControl noteControl = new NoteControl();
        //noteControl.getInputPane().setOnMouseEntered(event -> noteControl.getInputPane().setStyle("-fx-background-color: Black"));

        // เพิ่มข้อมูลทุกอย่างที่มี ใส่ลงไปในลิสต์
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
        int row = 1;
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

//                //set grid width
//                mainGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                mainGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                mainGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);
//
//                //set grid hight
//                mainGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                mainGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                mainGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(15));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void setChosenNote(Note note) {
        System.out.println(note.getSaveDate());
        setTextNote(note.getDescription());
        JSONObject obj = new JSONObject();
        obj.put("Description", note.getDescription());
        obj.put("Day", note.getSaveDate());
        deleteJSON(obj);
        openNote();
    }

    private void deleteJSON(JSONObject currentObject) {
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader("FileNote/noteDemo.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray noteArray = (JSONArray) object;
        for (int i = 0; i < noteArray.size(); i++) {
            if (noteArray.get(i).equals(currentObject)) {
                noteArray.remove(i);
            }
        }
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
            //addOrDelCount(0);
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
