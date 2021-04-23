package sample.controller.note;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.item.Note;
import sample.main.MyListener;

import java.net.URL;
import java.util.ResourceBundle;


public class NoteControl implements Initializable {

    public Pane inputPane = new Pane();
    public AnchorPane eachNotePane;
    public ImageView deletedbutton;
    private Note note;
    private MyListener myListener;

    public Label descripNote;
    public Label dateNote;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(note);
    }

    public void setData(Note note,MyListener myListener) {
        this.note = note;
        this.myListener = myListener;
        dateNote.setText(String.valueOf(note.getSaveDate()));
        descripNote.setText(note.getConvertNewLine());
    }

    public void setHover() {
        inputPane.setOnMouseEntered(e -> inputPane.setStyle("-fx-background-color: #0a2942"));
        inputPane.setOnMouseExited(e -> inputPane.setStyle("-fx-background-color:  #082033"));

        eachNotePane.setOnMouseEntered((e -> deletedbutton.setVisible(true)));
        eachNotePane.setOnMouseExited((e -> deletedbutton.setVisible(false)));
        //setTransition();

    }

    public void setTransition() {
        ScaleTransition trans = new ScaleTransition(Duration.millis(900), eachNotePane);
        trans.setFromX(0.97);
        trans.setToX(1);
        trans.setFromY(0.97);
        trans.setToY(1);
        trans.play();

        FadeTransition fadeMainPane = new FadeTransition(Duration.millis(2000), eachNotePane);
        fadeMainPane.setFromValue(0);
        fadeMainPane.setToValue(9);
        fadeMainPane.play();
    }

    public Pane getInputPane() {
        return inputPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHover();
        setTransition();
    }

    public void deletedNote(MouseEvent mouseEvent) {

    }
}
