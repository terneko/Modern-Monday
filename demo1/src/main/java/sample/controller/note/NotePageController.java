package sample.controller.note;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import java.util.*;

import static sample.controller.daycounter.DaycounterController.dragWidget;

public class NotePageController extends Controller implements Initializable {
    public BorderPane mainNote;

    public TextArea descripeNote;
    public TextArea textEdit;
    public GridPane mainGrid;

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
        notes.addAll(getData());
        int columns = 0;
        int row = 0;
        for (int i = 0; i < notes.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("../../views/addNote.fxml")));
            //fxmlLoader2.setLocation(Objects.requireNonNull(getClass().getResource("../../views/addStickyNote.fxml")));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                //AnchorPane anchorPane2 = fxmlLoader2.load();
                NoteControl noteController = fxmlLoader.getController();
                noteController.setData(notes.get(i));
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
//        columns++;
//        try {
//            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../../views/addStickyNote.fxml"));
//            mainGrid.add(anchorPane,columns,row);
//            mainGrid.setConstraints(anchorPane,columns,row);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //ABC.getChildren().addAll(new TextField("hehe00"));


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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
