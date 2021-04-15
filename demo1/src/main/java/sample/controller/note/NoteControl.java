package sample.controller.note;

import javafx.scene.control.Label;
import sample.item.Note;

public class NoteControl {


    public Label descripNote;
    public Label dateNote;

    private Note note;

    public void setData(Note note) {
        this.note = note ;
        dateNote.setText(String.valueOf(note.getSaveDate()));
        descripNote.setText(note.getDescription());
    }
}
