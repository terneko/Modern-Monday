package sample.controller.daycounter;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.controller.Controller;
import sample.controller.note.NoteControl;
import sample.item.DayCounter;
import sample.item.Note;
import sample.main.MyListener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.controller.note.WriteNote.setTextNote;

public class DaycounterController extends Controller implements Initializable {

    public TextField textInput;
    public DatePicker dayInput;
    public Label dayLabel;
    public static LocalDate dayNow = LocalDate.now();
    public static Label static_day_label;
    public static int dateLeft;
    private static double x,y;
    public TextField titleEvent;
    public DatePicker dateEvent;
    public TextField descriptionEvent;
    public Label mainLabel;
    public Label mainDescriptionLabel;
    public BorderPane dayCounterPageMainPane;
    public HBox backHBox;
    public ImageView backButton;


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
        loadPage("addDayCounterPage");
    }

    public void loadPage(String namePage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../views/" + namePage + ".fxml")));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        dayCounterPageMainPane.setCenter(root);
    }


    public void addDaycounter(MouseEvent mouseEvent) {
        DayCounter ter = new DayCounter(titleEvent.getText(),dateEvent.getValue(),descriptionEvent.getText());
        System.out.println(ter);
        ter.calculationDayLeft();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHover();
        loadPage("daycounterShowInfoPage");
        setFadeTransitionDayCounterPage();
        static_day_label = dayLabel;
    }

    public void setFadeTransitionDayCounterPage() {
        FadeTransition mainPaneFade = new FadeTransition(Duration.millis(500), dayCounterPageMainPane);
        mainPaneFade.setFromValue(0);
        mainPaneFade.setToValue(9);
        mainPaneFade.play();

        Label[] labels = {mainLabel, mainDescriptionLabel};
        for (Label label : labels) {
            TranslateTransition labelTrans = new TranslateTransition();
            labelTrans.setByX(2);
            labelTrans.setDuration(Duration.millis(1200));
            labelTrans.setNode(label);
            labelTrans.play();
        }
    }

    public void chooseImage(MouseEvent mouseEvent) {
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.TRANSPARENT);
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.showOpenDialog(stage);
//        stage.show();
    }

    public void setHover() {
        backHBox.setOnMouseEntered((e -> backHBox.setStyle("-fx-background-color: #00d3250")));
        backHBox.setOnMouseExited((e -> backHBox.setStyle("-fx-background-color: #051522")));
    }

    public void backToShowInfo(MouseEvent mouseEvent) {
        loadPage("daycounterShowInfoPage");
    }

//    private void clearData() {
//        mainGrid.getChildren().clear();
//    }


}

