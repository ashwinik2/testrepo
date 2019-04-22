package com.example.fragment_button_click.drawable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.fragment_button_click.R;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class CustomDrawable extends Drawable {


    private Paint mPaint;
    private Context mContext;
    Bitmap mBitmap;

    public CustomDrawable(Context context) {
        Log.i("CustomDrawable()"," In customDrawable");
        this.mContext = context;
        initPaint();
    }

    private void initPaint()
    {
        Log.i("initPaint()"," In customDrawable");
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#4D000000"));
        //paint.setColor(Color.CYAN);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        Log.i("draw()"," In customDrawable");

        canvas.drawColor(mPaint.getColor());
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.heart);
       // mPaint.setColor(Color.);
        mPaint.setColor(Color.parseColor("#4D000000"));
        canvas.drawBitmap(mBitmap,canvas.getWidth()/2-200,canvas.getHeight()/2 - 200, mPaint);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.WHITE);
        mPaint.setFakeBoldText(true);
        canvas.drawText("Left Top",16,50,mPaint);
        canvas.drawText("Left Bottom",16,canvas.getHeight() - 30,mPaint);
        canvas.drawText("Right Top",canvas.getWidth()  - 200,50,mPaint);
        canvas.drawText("Right Botton",canvas.getWidth()  - 200,canvas.getHeight() - 30,mPaint);
        mBitmap.recycle();

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
