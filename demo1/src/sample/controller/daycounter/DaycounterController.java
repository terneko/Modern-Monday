package sample.controller.daycounter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DaycounterController implements Initializable {

    public TextField textInput;
    public Label outputLabel;
    public DatePicker dayInput;
    public static LocalDate dayNow = LocalDate.now();
    public Label todayNow;
    public static int dateLeft;
    public static Label static_day_label;
    public Label dayLabel;

    @FXML
    public void openDaycounter(MouseEvent mouseEvent) {
        System.out.println("hello");
        System.out.println(textInput.getText());
        System.out.println(dayInput.getValue());
        System.out.println(dayNow);
        System.out.println(dateLeft);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/daycounterWidget.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("daycounter");
            stage.setScene(new Scene(root1));
            stage.show();
            static_day_label.setText(textInput.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        static_day_label = dayLabel;
    }

    public void onClick(MouseEvent mouseEvent) {
        System.out.println(textInput.getText());
        System.out.println(dayInput.getValue());
        System.out.println(dayNow);
        System.out.println(dateLeft);
    }
}
