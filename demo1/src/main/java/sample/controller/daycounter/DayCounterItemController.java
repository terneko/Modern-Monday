package sample.controller.daycounter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.item.DayCounter;
import sample.main.DayCounterListener;
import sample.main.MyListener;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.controller.daycounter.DaycounterController.dragWidget;

public class DayCounterItemController implements Initializable {
    public Label dayLeft;
    public Label title;
    public Pane penPane;
    public AnchorPane paneItemDayCounter;
    private DayCounterListener myListener, editListener;
    private DayCounter dayCounter;

    public void setData(DayCounter dayCounter, DayCounterListener myListener, DayCounterListener editListener) {
        this.dayCounter = dayCounter;
        this.myListener = myListener;
        this.editListener = editListener;


        //title.setText(dayCounter.getTitle());
        if (dayCounter.getDayLeft() > 1) {
            dayLeft.setText(dayCounter.getDayLeft() + " days : " +dayCounter.getTitle());
        } else if (dayCounter.getDayLeft() == 1) {
            dayLeft.setText(dayCounter.getDayLeft() + " day : "+dayCounter.getTitle());
        } else if (dayCounter.getDayLeft() == 0){
            dayLeft.setText("Today! : "+dayCounter.getTitle());
        } else {
            dayLeft.setText((-1) * dayCounter.getDayLeft() + " days ago : "+dayCounter.getTitle());
        }
    }

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(dayCounter);
    }

    public void clickEdit(MouseEvent mouseEvent) {
        editListener.onClickListener(dayCounter);
    }

    private void loadEdit() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/editDayCounterPage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit");
            dragWidget(root1, stage);
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHover() {
        penPane.setVisible(false);
        paneItemDayCounter.setOnMouseEntered(e -> penPane.setVisible(true));
        paneItemDayCounter.setOnMouseExited(e -> penPane.setVisible(false));

        penPane.setOnMouseEntered(e -> penPane.setStyle("-fx-background-color: #0a2942"));
        penPane.setOnMouseExited(e -> penPane.setStyle(""));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHover();
    }
}
