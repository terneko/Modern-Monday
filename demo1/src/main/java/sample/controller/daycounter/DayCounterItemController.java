package sample.controller.daycounter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.item.DayCounter;
import sample.main.DayCounterListener;
import sample.main.MyListener;

public class DayCounterItemController {
    public Label dayLeft;
    public Label title;
    private DayCounterListener myListener,moveToBin;
    private DayCounter dayCounter;

    public void setData(DayCounter dayCounter, DayCounterListener myListener, DayCounterListener moveToBin){
        this.dayCounter = dayCounter;
        this.myListener = myListener;
        this.moveToBin = moveToBin;

        dayLeft.setText(String.valueOf(dayCounter.getDayLeft()));
        title.setText(dayCounter.getTitle());
    }

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(dayCounter);
    }

    public void moveNoteToBinClicked(MouseEvent mouseEvent) {
        moveToBin.onClickListener(dayCounter);
    }
}
