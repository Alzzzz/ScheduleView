package com.alzzzz.scheduleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alzzzz.schedule.adapter.SimpleMarkAdapter;
import com.alzzzz.schedule.view.CalendarMarkView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarMarkView calendar_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> list = new ArrayList<>();
        list.add("20一");
        list.add("10日");
        list.add("14三");

        calendar_view = (CalendarMarkView) findViewById(R.id.calendar_view);
        calendar_view.setAdapter(new SimpleMarkAdapter(this));
//        calendar_view.showCalendar(list);
    }
}
