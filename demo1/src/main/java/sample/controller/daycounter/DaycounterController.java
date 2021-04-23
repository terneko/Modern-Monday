package sample.controller.daycounter;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.controller.Controller;
import sample.item.DayCounter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaycounterController extends Controller implements Initializable {

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
    public Label mainLabel;
    public Label mainDescriptionLabel;
    public BorderPane dayCounterPageMainPane;

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
        //            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../resources/sample/views/addDayCounterEvents.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setTitle("Add Day Counter");
//            dragWidget(root1,stage);
//            stage.setScene(new Scene(root1));
//            stage.initStyle(StageStyle.TRANSPARENT);
//            stage.show();
        loadPage2("addDayCounterPage");
    }

//    @Override
//    protected void loadPage() {
//
//    }

    protected void loadPage2(String namePage) {
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
        loadPage2("daycounterShowInfoPage");
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

}

