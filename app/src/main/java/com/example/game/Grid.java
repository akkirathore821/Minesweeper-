package com.example.game;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class Grid extends GridView {
    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.e("CheckTheLog","Grid");

        Game.getInstance().createGrid(context);

        setNumColumns(Constants.SIDE);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public static class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Constants.SIDE * Constants.SIDE;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return Game.getInstance().getCellAtPos(i);
        }
    }
}

