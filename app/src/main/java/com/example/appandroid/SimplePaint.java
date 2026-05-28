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
    //string modotraco = "tracolivre"
    //public static final string TRACOLIVRE = "tracoLivre" ;
    //public static final string TRACOCIRCULO = "tracoCirculo" ;
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



    //public void mudarTracoCircula(){modotraco = TRACOCIRCULO;
    //public void mudartracolivre(){modotraco = TRACOLIVRE;}
    //public void mudarTracoQuadrado(){modotraco = TRACOQUADRADO;

    public void init(){
        setClickable(true);
        //modoTraco = TRACOLIVRE
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
                //if(modoTraco == TRACOLIVRE){
                mPath.lineTo(event.getX(), event.getY());
                //
                //if(modoTraco == TRACOCIRCULO{
                //double r = Math.pow((Double)event.getX()-x0),2)-(yo-y)2 distancia euclidiana
                //correntepath.reset();
                //correntePath.addCircle(x0,y0,r.floatvalue,Path.Direction.ccw);
                //}

                break;
            case MotionEvent.ACTION_UP:
                //addCamada();
                break;
        }
        invalidate();
        return true;
    }
}
