package sample.controller.daycounter;

import sample.item.DayCounter;

import java.util.Comparator;

public class SortByDayLeft implements Comparator<DayCounter> {

    @Override
    public int compare(DayCounter o1, DayCounter o2) {
        return (int) (o1.getDayLeft()-o2.getDayLeft());
    }
}
