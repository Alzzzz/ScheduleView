package com.alzzzz.schedule.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

/**
 * Discription:日历表view
 *
 * Created by sz on 16/6/1.
 */
public class CalendarTableView extends AdapterView<BaseAdapter> {
    public CalendarTableView(Context context) {
        super(context);
    }

    public CalendarTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CalendarTableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public BaseAdapter getAdapter() {
        return null;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {

    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }
}
