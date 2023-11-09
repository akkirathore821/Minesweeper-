package com.example.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

@SuppressLint("ViewConstructor")
public class Cell extends BaseCell implements View.OnClickListener{
    public Cell(Context context , int x , int y) {
        super(context);

        setPosition(x,y);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View view) {

        if(Constants.getIsPlaying())
            Game.getInstance().click(getXPos(),getYPos());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("CheckTheLog","onDraw");

        if(isRevealed() && isBomb() && !isClicked()){
            showBomb(canvas);
        }else{
            if(isClicked()){
                if(getValue() == -1){
                    showBombExploded(canvas);
                }else{
                    showNumber(canvas);
                }
            }else{
                showButton(canvas);
            }
        }

    }
    private void showButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void showBomb(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.bomb_normal);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void showBombExploded(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.bomb_exploded);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void showNumber(Canvas canvas){
        Drawable drawable = null;

        switch (getValue()){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_8);
                break;
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
}
