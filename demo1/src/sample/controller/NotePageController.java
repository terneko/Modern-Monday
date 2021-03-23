package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import sample.item.Note;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotePageController implements Initializable {

    @FXML
    private ScrollPane noteScroll;

    @FXML
    private GridPane noteGrid;

    private List<Note> notes = new ArrayList<>();

    private List<Note> getData(){
        List<Note> notes = new ArrayList<>();
        Note note;

        for (int i = 0; i < 10; i++) {
            note = new Note();
            note.setDescription("hi, I'm ter");
            notes.add(note);
        }
        return notes;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notes.addAll(getData());

        for (int i = 0; i < notes.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/showNote.fxml"));

        }
    }
}
