package com.example.appandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SimplePaint extends View {
    Path mPath;//correntPath
    Paint mPaint;//correntPaint
    //ArrayList<Path> listPath
    //ArrayList<Paint> listPaint
    float x0, yo;
    public SimplePaint(Context context) {
        super(context);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        setClickable(true);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        //listPath = new ArrayList<>();

        //...
    }

    public void mudarCor(int color){

        //addCamada();
        mPaint.setColor(color);
    }

    public void addCamada(){
        //listaPaint.add(correntePaint);
        //,,,,,
        //correntePaint = new paint(correntPaint);
        //.....path = newPath;

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        //for (int i=0, i<-listPath.size()-1;i++){
        //canvas de baixo (listPath.get(i), listPaint[i]
        canvas.drawPath(mPath,mPaint);

    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d("coordenadas",Float.toString(event.getX())+Float.toString(event.getY() ));
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //xo=event.getx();y0=event.gety();
                mPath.moveTo(event.getX(),event.getY());

                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                //float r = (xo-x)2-(yo-y)2 distancia euclidiana
                //mPath.addCircle(x0,y0,r,Path.Direction.ccw);
                //

                break;
            case MotionEvent.ACTION_UP:
                //addCamada();
                break;
        }
        invalidate();
        return true;
    }
}
