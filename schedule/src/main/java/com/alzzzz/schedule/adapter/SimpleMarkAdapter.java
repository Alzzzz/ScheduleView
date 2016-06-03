package com.alzzzz.schedule.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alzzzz.schedule.R;

/**
 * Discription:
 * Created by sz on 16/6/1.
 */
public class SimpleMarkAdapter extends BaseAdapter {
    private Context mContenxt;
    private int size;

    public SimpleMarkAdapter(Context mContenxt, int count) {
        this.mContenxt = mContenxt;
        size = count;
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContenxt).inflate(R.layout.item_calendar, null);
        return convertView;
    }

}
