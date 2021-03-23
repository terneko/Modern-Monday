package sample.item;

import javafx.scene.control.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DayCounter {
    private DatePicker dayStart;
    private Date dayEnd;
    private String description;
    private LocalDate now = LocalDate.now();

    public DayCounter(DatePicker dayStart, Date dayEnd, String description) {
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.description = description;
    }

    public long calculationDayLeft() {
        long diff = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputString1 = String.valueOf(dayStart.getValue());
        String inputString2 = String.valueOf(dayEnd.getTime());

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            diff = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return diff;

    }

}