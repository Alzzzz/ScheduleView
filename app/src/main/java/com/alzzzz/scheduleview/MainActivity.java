package com.alzzzz.scheduleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alzzzz.schedule.adapter.BingoMarkAdapter;
import com.alzzzz.schedule.adapter.SimpleMarkAdapter;
import com.alzzzz.schedule.view.CalendarMarkView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarMarkView calendar_view;
    String[] rowsTitles = {"一","二","三","四","五","六","日"};
    String[] columTitles = {"08:00\n10:00","10:00\n12:00","12:00\n14:00",
            "14:00\n16:00","16:00\n18:00","18:00\n20:00"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> list = new ArrayList<>();
        list.add("20一");
        list.add("10日");
        list.add("14三");

        calendar_view = (CalendarMarkView) findViewById(R.id.calendar_view);
        calendar_view.setRowTitle(rowsTitles);
        calendar_view.setColumTitle(columTitles);
        BingoMarkAdapter markAdapter = new BingoMarkAdapter(this, calendar_view.getSize());
        markAdapter.setSelectedPos(1,2,3,10,33);
        calendar_view.setAdapter(markAdapter);
//        calendar_view.showCalendar(list);
    }
}
