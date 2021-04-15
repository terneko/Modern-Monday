package sample.controller.daycounter;

import javafx.event.EventHandler;
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
import javafx.stage.StageStyle;
import sample.item.DayCounter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DaycounterController implements Initializable {

    public TextField textInput;
    public Label outputLabel;
    public DatePicker dayInput;
    public Label dayLabel;
    public static LocalDate dayNow = LocalDate.now();
    public static Label static_day_label;
    public Label todayNow;
    public static int dateLeft;
    private static double x,y;
    public TextField titleEvent;
    public DatePicker dateEvent;
    public TextField descriptionEvent;

    public static void dragWidget(Parent root,Stage primaryStage) {

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX()-x);
                primaryStage.setY(event.getScreenY()-y);
            }
        });
    }

    @FXML
    public void openDaycounter(MouseEvent mouseEvent) {
        System.out.println("hello");
        System.out.println(textInput.getText());
        System.out.println(dayInput.getValue());
        System.out.println(dayNow);
        System.out.println(dateLeft);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../resources/sample/views/daycounterWidget.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            dragWidget(root1,stage);
            stage.setTitle("daycounter");
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            static_day_label.setText(textInput.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(MouseEvent mouseEvent) {
        System.out.println(textInput.getText());
        System.out.println(dayInput.getValue());
        System.out.println(dayNow);
        System.out.println(dateLeft);
    }

    public void addPageDaycounter(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../resources/sample/views/addDayCounterEvents.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Day Counter");
            dragWidget(root1,stage);
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDaycounter(MouseEvent mouseEvent) {
        DayCounter ter = new DayCounter(titleEvent.getText(),dateEvent.getValue(),descriptionEvent.getText());
        System.out.println(ter);
        ter.calculationDayLeft();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        static_day_label = dayLabel;
    }
}

