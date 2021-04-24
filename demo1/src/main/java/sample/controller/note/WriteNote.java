package sample.controller.note;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.item.Note;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static sample.controller.daycounter.DaycounterController.dragWidget;

public class WriteNote implements Initializable {

    public TextArea textEdit;
    public BorderPane mainNote;
    //initial variable
    private Note note = new Note();

    private static String textNote;
    private FileWriter file;

    private List<Note> allNote = new ArrayList<>();

    public void getList() {
        allNote = NotePageController.getData();
    }

    public void descripeNote(KeyEvent keyEvent) {
        deleteNote(note);
        initializeNote();
    }

    public void initializeNote() {
        String text;
        text = textEdit.getText();
        note.setDescription(text);
        note.setSaveDate(String.valueOf(LocalDate.now()));
        setTextEdit(text);
        // NotePageController.initialize();
        //note.openAndSaveJSON();
        //System.out.println(note);
        note.SaveJSON();
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

//    private void deleteNote(Note note) {
//        JSONObject obj = new JSONObject();
//        obj.put("Description", note.getDescription());
//        obj.put("Day", note.getSaveDate());
//
//        JSONParser parser = new JSONParser();
//        Object object = null;
//        try {
//            object = parser.parse(new FileReader("FileNote/noteDemo.json"));
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(obj);
//        JSONArray noteArray = (JSONArray) object;
//        for (int i = 0; i < noteArray.size(); i++) {
//            if (noteArray.get(i).equals(obj)) {
//                noteArray.remove(i);
//            }
//        }
//
//        //Save by new
//        try {
//            // Constructs a FileWriter given a file name, using the platform's default charset
//            file = new FileWriter("FileNote/noteDemo.json");
//            file.write(noteArray.toJSONString());
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void close(MouseEvent mouseEvent) {
        String text;
        text = textEdit.getText();
        note.setDescription(text);
        note.setSaveDate(String.valueOf(LocalDate.now()));
        //note.SaveJSON();
        deleteNote(note);
        note.SaveJSON();
        if (text.equals("")) {
            deleteNote(note);
        }
        //checkCount();
        Stage stage = (Stage) mainNote.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setHoverController();

        textEdit.setText(textNote);
    }

    public void addNote(MouseEvent mouseEvent) {
        setTextNote("");
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

    public static void setTextNote(String textNote) {
        WriteNote.textNote = textNote;
    }

    public void setTextEdit(String text) {
        note.setDescription(text);
    }
}
