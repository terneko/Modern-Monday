package sample.controller.note;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.item.Note;
import sample.main.MyListener;


public class NoteControl {


    private Note note;
    private MyListener myListener;

    public Label descripNote;
    public Label dateNote;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(note);
        System.out.println("helllo");
    }

    public void setData(Note note,MyListener myListener) {
        this.note = note;
        this.myListener = myListener;
        dateNote.setText(String.valueOf(note.getSaveDate()));
        descripNote.setText(note.getDescription());
    }
}
