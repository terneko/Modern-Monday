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
import sample.item.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

import static sample.controller.daycounter.DaycounterController.dragWidget;

public class WriteNote implements Initializable {

    public TextArea textEdit;
    public BorderPane mainNote;
    //initial variable
    private int num;
    private Note note = new Note();

    private static String textNote;

    public void descripeNote(KeyEvent keyEvent) {
        String text;
        text = textEdit.getText();
        note.setDescription(text);
        note.setSaveDate(String.valueOf(LocalDate.now()));
        //note.openAndSaveJSON();
    }

    public void checkCount() {
        if (note.getDescription().equals("")) {

            Scanner input = null;
            {
                try {
                    input = new Scanner(new File("FileNote/Count"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            String newCount;
            int count = input.nextInt() - 1;
            newCount = String.valueOf(count);
            System.out.println(newCount);
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
    }

    public void close(MouseEvent mouseEvent) {
        String text;
        text = textEdit.getText();
        note.setDescription(text);
        note.setSaveDate(String.valueOf(LocalDate.now()));
        if(!text.equals("")) {
            note.SaveJSON();
        }
        //checkCount();
        Stage stage = (Stage) mainNote.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textEdit.setText(textNote);
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
