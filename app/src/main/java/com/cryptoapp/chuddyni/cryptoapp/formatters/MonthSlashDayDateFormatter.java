package com.cryptoapp.chuddyni.cryptoapp.formatters;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MonthSlashDayDateFormatter implements IAxisValueFormatter {


    @Override
    public String getFormattedValue(float unixSeconds, AxisBase axis) {
        Date date = new Date((long)unixSeconds);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd", Locale.ENGLISH);
        return sdf.format(date);
    }
}
