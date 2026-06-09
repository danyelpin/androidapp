package com.example.appandroid;

import static java.lang.Math.max;
import static java.lang.Math.min;

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

import java.util.ArrayList;

public class SimplePaint extends View {
    //Path mPath;
    Path courrentPath;
    //Paint mPaint;
    Paint courrentPaint;
    ArrayList<Path> listPath;
    ArrayList<Paint> listPaint;
    float x0, y0;
    private Ferramenta ferramenta = Ferramenta.LINHA;

    public void setFerramenta(Ferramenta ferramenta){
        this.ferramenta = ferramenta;
    }

    public enum Ferramenta{
        LINHA,
        CIRCULO,
        RETANGULO
    }

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
        listPath = new ArrayList<>();
        listPaint = new ArrayList<>();
        courrentPath = new Path();
        courrentPaint = new Paint();
        courrentPaint.setColor(Color.BLACK);
        courrentPaint.setStrokeWidth(10);
        courrentPaint.setStyle(Paint.Style.STROKE);
        //listPath = new ArrayList<>();

        //...
    }

    public void mudarCor(int color){
        courrentPaint.setColor(color);
    }

    public void addCamada(){
        listPaint.add(courrentPaint);
        listPath.add(courrentPath);

        courrentPath = new Path();

        Paint novoPaint = new Paint(courrentPaint);
        courrentPaint = novoPaint;

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0; i <=listPath.size()-1; i++) {
            //canvas de baixo (listPath.get(i), listPaint[i]
            canvas.drawPath(listPath.get(i), listPaint.get(i));
        }
        canvas.drawPath(courrentPath, courrentPaint);

    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d("coordenadas",Float.toString(event.getX())+Float.toString(event.getY() ));
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x0=event.getX();
                y0=event.getY();
                courrentPath.moveTo(x0,y0);

                break;
            case MotionEvent.ACTION_MOVE:
                switch (ferramenta){
                    case LINHA:
                        courrentPath.lineTo(event.getX(),event.getY());

                    break;

                    case CIRCULO:
                        float dx = event.getX() - x0;
                        float dy = event.getY() - y0;

                        float r = dx * dx + dy * dy; //distancia euclidiana
                        courrentPath.reset();
                        courrentPath.addCircle(x0, y0, r, Path.Direction.CCW);

                    break;

                    case RETANGULO:
                         float left = min(x0, event.getX());
                         float top = min(y0, event.getY());
                         float right = max(x0, event.getX());
                         float bottom = max(y0, event.getY());
                         courrentPath.reset();
                         courrentPath.addRect(left,top,right,bottom,Path.Direction.CCW);


                    break;
                }
                break;
            case MotionEvent.ACTION_UP:
                addCamada();
                break;
        }
        invalidate();
        return true;
    }
}
