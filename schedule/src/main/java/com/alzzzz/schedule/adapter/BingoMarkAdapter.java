package com.alzzzz.schedule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alzzzz.schedule.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Discription:
 * Created by sz on 16/6/6.
 */
public class BingoMarkAdapter extends SimpleMarkAdapter {

    private List<Integer> selectedPos;
    private Context mContext;

    public BingoMarkAdapter(Context mContenxt, int count) {
        super(mContenxt, count);
        this.mContext = mContenxt;
    }

    public void setSelectedPos(Integer... selectedPos){
        if (selectedPos != null){
            this.selectedPos = Arrays.asList(selectedPos);
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (selectedPos.contains(position)){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar_mark, null);
        }else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, null);
        }
        return convertView;
    }
}
