package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button addButton;
    public DatePicker datePicker;
    public TextField descriptionTextField;
    public ListView<LocalEvent> eventList;

    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setValue(LocalDate.now());
    }

    public void addEvent(Event e) {
        if (!descriptionTextField.getText().equals("")) {
            list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
            eventList.setItems(list);
        }
        refresh();
    }

    private void refresh() {
        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText("");
    }
}






