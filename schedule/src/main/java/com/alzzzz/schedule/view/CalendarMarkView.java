package com.alzzzz.schedule.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alzzzz.schedule.R;
import com.alzzzz.schedule.provider.CalendarProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaohaoqing
 * Date : 16/5/19
 * Time : 10:09
 */
public class CalendarMarkView extends LinearLayout {

    private List<String> weekList = new ArrayList<String>();
    private List<String> lineTitleList = new ArrayList<String>();
    private List<String> mDataList;

    public CalendarMarkView(Context context) {
        super(context);
        initView();
    }

    public CalendarMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CalendarMarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public CalendarMarkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        setOrientation(VERTICAL);

        initData();
//        initTableTitle();
//        initTableLine();

    }

    public void showCalendar(List<String> list) {
        this.mDataList = list;
        initTableTitle();
        initTableLine();
        addView(getLineView());
    }

    private void initTableLine() {
        for (int i = 0; i < 6; i++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            View lineTitleView = getLineTitleView();
            setLineTitleText(lineTitleView, lineTitleList.get(i), lineTitleList.get(i + 1));
            linearLayout.addView(lineTitleView);
            for (int j = 0; j < 7; j++) {
                View item = getMarkItemView();
                showMarkView(item, lineTitleList.get(i + 1), weekList.get(j));
                linearLayout.addView(item);
            }
            addView(linearLayout);
        }
    }

    private void initTableTitle() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        View oneLineTitleView = getLineTitleView();
        updateViewBG(oneLineTitleView);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.addView(oneLineTitleView);
        for (int i = 0; i < 7; i++) {
            View item = getItemView();
            updateViewBule(item );
            setItemText(item, weekList.get(i));
            linearLayout.addView(item);
        }
        addView(linearLayout);
    }

    private void initData() {
        weekList.add("日");
        weekList.add("一");
        weekList.add("二");
        weekList.add("三");
        weekList.add("四");
        weekList.add("五");
        weekList.add("六");
        lineTitleList.add("08:00");
        lineTitleList.add("10:00");
        lineTitleList.add("12:00");
        lineTitleList.add("14:00");
        lineTitleList.add("16:00");
        lineTitleList.add("18:00");
        lineTitleList.add("20:00");
    }

    private View getLineTitleView() {
        return View.inflate(getContext(), R.layout.item_line_title, null);
    }

    private View getItemView() {
        return View.inflate(getContext(), R.layout.item_calendar, null);
    }

    private View getMarkItemView() {
        return View.inflate(getContext(), R.layout.item_calendar_mark, null);
    }

    private View getLineView() {
        return View.inflate(getContext(), R.layout.item_line, null);

    }

    private void updateViewBule(View view){
        TextView tv_item = (TextView) view.findViewById(R.id.tv_ic_content);
        tv_item.setBackgroundColor(Color.parseColor("#d4e7fb"));
    }

    private void updateViewBG(View view) {
        LinearLayout ll_ilt_line_title = (LinearLayout) view.findViewById(R.id.ll_ilt_line_title);
        ll_ilt_line_title.setBackgroundColor(Color.WHITE);
    }

    private void showMarkView(View itemMark, String timeSlot, String daySlot) {
        ImageView iv_icm_mark = (ImageView) itemMark.findViewById(R.id.iv_icm_mark);

        if (CalendarProvider.judeIsShow(CalendarProvider.viewKey(timeSlot, daySlot), mDataList)) {
            iv_icm_mark.setImageResource(R.drawable.bg_calendar_mark);
        } else {
            iv_icm_mark.setImageResource(android.R.color.transparent);
        }
    }

    private void setItemText(View view, String text) {
        TextView tv_item = (TextView) view.findViewById(R.id.tv_ic_content);
        tv_item.setText(text);
    }

    private void setLineTitleText(View view, String one, String two) {
        TextView one_item = (TextView) view.findViewById(R.id.tv_ilt_title_one);
        TextView two_item = (TextView) view.findViewById(R.id.tv_ilt_title_two);

        one_item.setText(one);
        two_item.setText(two);
    }
}

