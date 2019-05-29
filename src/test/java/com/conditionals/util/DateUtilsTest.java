package com.conditionals.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;

public class DateUtilsTest {
    @Test
    public void createDateTest() {
        Date date = DateUtils.createDate(2019, 1, 31);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        assertEquals(2019, cal.get(Calendar.YEAR));
        assertEquals(0, cal.get(Calendar.MONTH));
        assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void convertDateToMonthDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 5, 27);

        MonthDay monthDay = DateUtils.dateToMonthDay(cal.getTime());

        assertEquals(6, monthDay.getMonthValue());
        assertEquals(27, monthDay.getDayOfMonth());
    }
}
