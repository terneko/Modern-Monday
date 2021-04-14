package sample.controller.note;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.controller.Controller;
import sample.item.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static sample.controller.daycounter.DaycounterController.dragWidget;

public class NotePageController extends Controller implements Initializable {
    public BorderPane mainNote;
    public GridPane MainGrid;
    public TextArea descripeNote;
    public TextArea textEdit;

    @FXML
    private ScrollPane noteScroll;

    @FXML
    private GridPane noteGrid;

    private List<Note> notes = new ArrayList<>();

    private List<Note> getData() {
        List<Note> notes = new ArrayList<>();
        Note note;

        for (int i = 0; i < 10; i++) {
            note = new Note("hello");
            note.setDescription("วันนี้เป็นวันสงกรานต์");
            notes.add(note);
        }
        return notes;
    }


    public void addNote(MouseEvent mouseEvent) {
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
            deleteFile(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // for delete note
    public void deleteFile(int num) {


    }

    //0 for add and 1 for delete
    public void addOrDelCount(int keys) {
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

    }


}
