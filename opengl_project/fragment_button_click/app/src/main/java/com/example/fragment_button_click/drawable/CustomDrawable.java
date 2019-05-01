package com.example.fragment_button_click.drawable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

public class CustomDrawable extends Drawable {


    private Paint mPaint;
    private Context mContext;
    Rect mRect;

    public CustomDrawable(Context context) {
        Log.i("CustomDrawable()"," In customDrawable");
        this.mContext = context;
        initPaint();
    }

    private void initPaint()
    {
        Log.i("initPaint()"," In customDrawable");
        mPaint = new Paint();
        mRect = new Rect();
        mPaint.setColor(Color.parseColor("#4D000000"));
        mPaint.setAntiAlias(true);

    }
    @Override
    public void draw(@NonNull Canvas canvas) {

        Log.i("draw()"," In customDrawable");
        canvas.drawColor(mPaint.getColor());
        mPaint.setColor(Color.parseColor("#4D000000"));
       /* mRect.left = canvas.getWidth()/2-400;
        mRect.top = canvas.getHeight()/2;
        mRect.right = canvas.getWidth()-400;
        mRect.bottom = canvas.getHeight()/2+200;*/

        //canvas.drawARGB(255,150,150,150);
       // canvas.drawRect(canvas.getWidth()/2-400, canvas.getHeight()/2 - 300, canvas.getWidth()-400, canvas.getHeight()/2+200 , mPaint);
       //canvas.drawBitmap(mBitmap,canvas.getWidth()/2-200,canvas.getHeight()/2 - 200, mPaint);
       // canvas.drawBitmap(mBitmap,0,canvas.getHeight()/2 - 200, mPaint);

     /*   mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.heart);
        mPaint.setColor(Color.parseColor("#4D000000"));
        canvas.drawBitmap(mBitmap,canvas.getWidth()/2-200,canvas.getHeight()/2 - 200, mPaint);*/

        mPaint.setTextSize(30);
        mPaint.setColor(Color.WHITE);
        mPaint.setFakeBoldText(true);
        canvas.drawText("Left Top",16,50,mPaint);
        canvas.drawText("Left Bottom",16,canvas.getHeight() - 30,mPaint);
        canvas.drawText("Right Top",canvas.getWidth()  - 200,50,mPaint);
        canvas.drawText("Right Botton",canvas.getWidth()  - 200,canvas.getHeight() - 30,mPaint);
      //  canvas.restore();

      //  mBitmap.recycle();

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
