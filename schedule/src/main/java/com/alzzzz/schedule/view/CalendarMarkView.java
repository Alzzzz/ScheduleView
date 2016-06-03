package com.alzzzz.schedule.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.alzzzz.schedule.R;
import com.alzzzz.schedule.provider.CalendarProvider;

import java.util.List;

/**
 * Created by gaohaoqing
 * Date : 16/5/19
 * Time : 10:09
 */
public class CalendarMarkView extends LinearLayout {

    private List<String> mDataList;
    private ListAdapter mAdapter;
    private Context context;
    private int rowsNum = 0;//行数 不包括表格列头
    private int columNum = 0;//列数  不包括表格行头
    private int rowHeaderHeight = 0;
    private int columHeaderWidth = 0;
    private String[] rowTitles;
    private String[] columTitles;

    public CalendarMarkView(Context context) {
        this(context, null);
    }

    public CalendarMarkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarMarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CalendarMarkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CalendarMarkView, defStyleAttr, defStyleRes);
        columNum = a.getInt(R.styleable.CalendarMarkView_cmv_columnum, 0);
        rowsNum = a.getInt(R.styleable.CalendarMarkView_cmv_rowsnum, 0);
        rowHeaderHeight = a.getDimensionPixelSize(R.styleable.CalendarMarkView_cmv_rowheader_height, dp2px(100));
        columHeaderWidth = a.getDimensionPixelSize(R.styleable.CalendarMarkView_cmv_columheader_width, dp2px(50));
        a.recycle();
        initView();
    }

    /** call before setAdapter.set title of rows */
    public CalendarMarkView setRowTitle(String... rowTitles){
        this.rowTitles = rowTitles;
        return this;
    }

    /** call before setAdapter.set title of colum */
    public CalendarMarkView setColumTitle(String... columTitles){
        this.columTitles = columTitles;
        return this;
    }


    public void setAdapter(ListAdapter mAdapter){
        this.mAdapter = mAdapter;
        setupView();
    }

    public int getSize(){
        int count = getColumTitleCount()+getRowTitleCount();
        return count;
    }

    private void setupView() {
        initRowsHeader();
        setupTable();
        addView(getLineView());
    }

    /** 获取表头样式 */
    private View getTableHeader() {
        // TODO: 16/6/2 增加表头分割样式
        View oneLineTitleView = getLineTitleView();
        updateViewBG(oneLineTitleView);
        LayoutParams lp = new LayoutParams(columHeaderWidth, LayoutParams.MATCH_PARENT);
        oneLineTitleView.setLayoutParams(lp);
        return oneLineTitleView;
    }

    private void initView() {
        setOrientation(VERTICAL);

    }

    private int getRowTitleCount(){
        int count = columNum;
        if (rowTitles != null && count <= 0){
            count = rowTitles.length;
        }
        return count;
    }

    private int getColumTitleCount(){
        int count = rowsNum;
        if (columTitles != null && count <= 0){
            count = columTitles.length;
        }
        return count;
    }

    private void initRowsHeader() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        // TODO: 16/6/2 自定义表格的第一格

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, rowHeaderHeight);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setLayoutParams(lp);
        linearLayout.addView(getTableHeader());

        int size = getRowTitleCount();
        for (int i = 0; i < size; i++) {
            View item = getItemView();
            item.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
            updateViewBule(item);
            if (rowTitles.length > i)
                setItemText(item, rowTitles[i]);
            linearLayout.addView(item);
        }
        addView(linearLayout);
    }

    public void setupTable(){
        int rowsCount = getColumTitleCount();
        for (int i = 0; i < rowsCount; i++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            View lineTitleView = getLineTitleView();
            if (columTitles.length > i)
                setLineTitleText(lineTitleView, columTitles[i]/*.get(i), lineTitleList.get(i + 1)*/);

            LayoutParams lp = new LayoutParams(columHeaderWidth, LayoutParams.MATCH_PARENT);
            lineTitleView.setLayoutParams(lp);
            linearLayout.addView(lineTitleView);
            int size = getRowTitleCount();
            for (int j = 0; j < size; j++) {
                if (mAdapter != null && mAdapter.getCount()>0){
                    int pos = i*size+j;
                    View itemView = mAdapter.getView(pos, null, this);
                    itemView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
                    linearLayout.addView(itemView);
                }
            }
            addView(linearLayout);
        }
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

    private void setLineTitleText(View view, String one/*, String two*/) {
        TextView one_item = (TextView) view.findViewById(R.id.tv_ilt_title_one);

        one_item.setText(one);
//        two_item.setText(two);
    }

    private int dp2px(float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}

