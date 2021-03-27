package sample.item;

import javafx.scene.control.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DayCounter {
    private String title;
    private LocalDate dayEnd;
    private LocalDate dayStart;
    private String description;
    private LocalDate now = LocalDate.now();

    public DayCounter(String title,LocalDate  dayEnd, String description) {
        this.title = title;
        this.dayEnd = dayEnd;
        this.description = description;
        this.dayStart = now;
    }

    @Override
    public String toString() {
        return "DayCounter{" +
                "title='" + title + '\'' +
                ", dayEnd=" + dayEnd +
                ", dayStart=" + dayStart +
                ", description='" + description + '\'' +
                ", now=" + now +
                '}';
    }

    public long calculationDayLeft() {
        long diff = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputString1 = String.valueOf(dayStart);
        String inputString2 = String.valueOf(dayEnd);
        System.out.println(inputString1);
        System.out.println(inputString2);
        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            diff = date2.getTime() - date1.getTime(); //chrono unit
            diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // day unit

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(diff);
        return diff;
    }

}