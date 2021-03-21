package sample.option;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DaycounterController implements Initializable {

    public TextField textInput;
    public Label outputLabel;
    public Label dayLabel;

    @FXML
    public void openDaycounter(MouseEvent mouseEvent) {
        System.out.println("hello");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("daycounterWidget.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("daycounter");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void onClick(MouseEvent mouseEvent) {
        System.out.println(textInput.getText());
        dayLabel.setText(textInput.getText());
    }
}
