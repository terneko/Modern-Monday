package sample.controller.note;

import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.item.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class WriteNote extends NotePageController implements Initializable {

    //initial variable
    private int num;
    private Note note = new Note("");


    public void descripeNote(KeyEvent keyEvent) {
        String text;
        text = textEdit.getText();
        note.setDescription(text);
        note.setSaveDate(LocalDate.now());
        note.saveJSON();
    }


    @Override
    public void close(MouseEvent mouseEvent) {
        //
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
        Stage stage = (Stage) mainNote.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
