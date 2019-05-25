package com.example.fragment_button_click.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.util.Random;


public class CustomView extends View {

    private Context mContext;
    private Paint mPaint,cPaint;
    Bitmap mBitmap;
    Canvas mCanvas;
    Rect mRect;
    private boolean touching, drawingTouch;

    public CustomView(Context context) {
        super(context);
        mContext = context;
        init(null);

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        Log.i("initPaint()", " In customView");
        mPaint = new Paint();
        mRect = new Rect();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(8);

        cPaint = new Paint();
        cPaint.setColor(Color.BLUE);
        cPaint.setFakeBoldText(true);
        cPaint.setTextSize(20f);
        cPaint.setAntiAlias(true);
        cPaint.setTextAlign(Paint.Align.CENTER);

        mBitmap = Bitmap.createBitmap(cols, rows, Bitmap.Config.ARGB_8888);
        createBitmapData();

    }

    private static final int cols = 200;
    private static final int rows = 200;
    public byte[] mtextureBytes = new byte[cols * rows * 4];
    private float mPreviousX = 0;
    private float mPreviousY = 0;
    private float mPreviousX1 = 0;
    private float mPreviousY1 = 0;

    private float mPreviousX2 = 0;
    private float mPreviousY2 = 0;
    float drawingPicOffset_x = 0, drawingPicOffset_y = 0;
    float mCircleX,mCircleY,mCircleRadius = 30f;
    Boolean mCircleTouched = false;
    Boolean mTriggerBitmap = false;
    Boolean mTriggerBitmapstop = false;
    Bitmap mBitmapScaled;
    int mScaledWidth,mScaledHeight;

    public ByteBuffer mtexturebuffer;
    Handler handler = new Handler();

    public void createBitmapData() {

        Log.i("createBitmap()", " In customView");
        Random random = new Random();
        int mRed, mGreen, mBlue, mAlpha;
        int mGrayValue;
        mtexturebuffer = ByteBuffer.wrap(mtextureBytes);
        mRed = random.nextInt(255);
        mGreen = random.nextInt(255);
        mBlue = random.nextInt(255);
        for (int i = 0; i < (cols * rows); i=i+8) {
            mAlpha = 0;
            if(i%4000 == 0) {
                mRed = random.nextInt(255);
                mGreen = random.nextInt(255);
                mBlue = random.nextInt(255);
            }
            mGrayValue = (mRed + mGreen + mBlue);

            mRed = mGrayValue;
            mGreen = mGrayValue;
            mBlue = mGrayValue;

            mtexturebuffer.put(((i * 4) + 0), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 1), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 2), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 3), (byte) mBlue);

            mtexturebuffer.put(((i * 4) + 4), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 5), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 6), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 7), (byte) mBlue);

            mtexturebuffer.put(((i * 4) + 8), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 9), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 10), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 11), (byte) mBlue);

            mtexturebuffer.put(((i * 4) + 12), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 13), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 14), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 15), (byte) mBlue);

            mRed = random.nextInt(255);
            mGreen = random.nextInt(255);
            mBlue = random.nextInt(255);

            mtexturebuffer.put(((i * 4) + 16), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 17), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 18), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 19), (byte) mBlue);

            mtexturebuffer.put(((i * 4) + 20), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 21), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 22), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 23), (byte) mBlue);

            mtexturebuffer.put(((i * 4) + 24), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 25), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 26), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 27), (byte) mBlue);

            mtexturebuffer.put(((i * 4) + 28), (byte) mAlpha);
            mtexturebuffer.put(((i * 4) + 29), (byte) mRed);
            mtexturebuffer.put(((i * 4) + 30), (byte) mGreen);
            mtexturebuffer.put(((i * 4) + 31), (byte) mBlue);

        }

        mBitmap.copyPixelsFromBuffer(mtexturebuffer);
        postInvalidate();
        handler.postDelayed(new Runnable() {
            public void run() {
                createBitmapData();
            }
        }, 2000);
    }

    public Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {

        final int bitmapWidth = bitmap.getWidth();
        final int bitmapHeight = bitmap.getHeight();

        final float scale = Math.min((float) width / (float) bitmapWidth,
                (float) height / (float) bitmapHeight);

        /*mScaledWidth = (int) (bitmapWidth * scale);
        mScaledHeight = (int) (bitmapHeight * scale);*/

        mScaledWidth = width;
        mScaledHeight = height;

        mBitmapScaled = Bitmap.createScaledBitmap(bitmap, mScaledWidth, mScaledHeight, true);

        return mBitmapScaled;
    }
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Log.i("draw()", " In customView");
       // mPaint.setColor(Color.parseColor("#4D000000"));
        mPaint.setColor(Color.WHITE);

        if (mPreviousX == 0f || mPreviousY == 0f) {
            mPreviousX  =  canvas.getWidth()/2 -  mBitmap.getWidth()/2 ;
            mPreviousY  =  canvas.getHeight()/2  - mBitmap.getHeight()/2 ;

            mPreviousX2 = mPreviousX ;
            mPreviousY2 = mPreviousY ;
        }

     /*   if(mCircleTouched == true)
        {
            mBitmapScaled = scaleBitmap(mBitmap,400,400);
            canvas.drawBitmap(mBitmapScaled, mPreviousX, mPreviousY, null);
            mPaint.setColor(Color.WHITE);
            canvas.drawLine(mPreviousX, mPreviousY , mPreviousX+mBitmapScaled.getWidth(), mPreviousY, mPaint);
            canvas.drawLine(mPreviousX, mPreviousY , mPreviousX, mPreviousY+mBitmapScaled.getHeight(), mPaint);
            canvas.drawLine(mPreviousX+mBitmapScaled.getWidth(), mPreviousY ,mPreviousX+mBitmapScaled.getWidth(), mPreviousY+mBitmapScaled.getHeight(), mPaint);
            canvas.drawLine(mPreviousX, mPreviousY+mBitmapScaled.getHeight(), mPreviousX+mBitmapScaled.getWidth(), mPreviousY+mBitmapScaled.getHeight(), mPaint);
            mPaint.setTextSize(30);
            mPaint.setColor(Color.WHITE);
            mPaint.setFakeBoldText(true);
            mCircleX = (mPreviousX+mBitmapScaled.getWidth());
            mCircleY = (mPreviousY+mBitmapScaled.getHeight());
            canvas.drawCircle(mCircleX,mCircleY,mCircleRadius,mPaint);
            canvas.drawText("D", mPreviousX+mBitmapScaled.getWidth(),mPreviousY+mBitmapScaled.getHeight(), cPaint);
        }
        else {
            canvas.drawBitmap(mBitmap, mPreviousX, mPreviousY, null);
            mPaint.setColor(Color.WHITE);
            canvas.drawLine(mPreviousX, mPreviousY, mPreviousX + mBitmap.getWidth(), mPreviousY, mPaint);
            canvas.drawLine(mPreviousX, mPreviousY, mPreviousX, mPreviousY + mBitmap.getHeight(), mPaint);
            canvas.drawLine(mPreviousX + mBitmap.getWidth(), mPreviousY, mPreviousX + mBitmap.getWidth(), mPreviousY + mBitmap.getHeight(), mPaint);
            canvas.drawLine(mPreviousX, mPreviousY + mBitmap.getHeight(), mPreviousX + mBitmap.getWidth(), mPreviousY + mBitmap.getHeight(), mPaint);
            mPaint.setTextSize(30);
            mPaint.setColor(Color.WHITE);
            mPaint.setFakeBoldText(true);
            mCircleX = (mPreviousX + mBitmap.getWidth());
            mCircleY = (mPreviousY + mBitmap.getHeight());
            canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
            canvas.drawText("D", mPreviousX+mBitmap.getWidth(),mPreviousY+mBitmap.getHeight(), cPaint);

        }*/

        if(mCircleTouched == true && mTriggerBitmap == true)
        {
            int width =(int)Math.round(mPreviousX1-mPreviousX);
            int height = (int)Math.round(mPreviousY1-mPreviousY);

            System.out.println("mPreviousX= " +mPreviousX);
            System.out.println("mPreviousY= " +mPreviousY);

            System.out.println("mPreviousX1= " +mPreviousX1);
            System.out.println("mPreviousY1= " +mPreviousY1);

            System.out.println("bitmap width inside circletouched= " +width);
            System.out.println("bitmap height inside circletouched= " +height);
            mBitmapScaled = scaleBitmap(mBitmap,width,height);

            mPaint.setColor(Color.WHITE);
            mPaint.setColor(Color.parseColor("#4D000000"));
            canvas.drawRect(mPreviousX, mPreviousY, mPreviousX1 , mPreviousY1, mPaint);
             mPaint.setColor(Color.parseColor("#4D000000"));
            canvas.drawBitmap(mBitmapScaled, mPreviousX, mPreviousY, mPaint);
            mPaint.setTextSize(30);
            mPaint.setColor(Color.WHITE);
            mPaint.setFakeBoldText(true);
           /* mCircleX = (mPreviousX+mBitmapScaled.getWidth());
            mCircleY = (mPreviousY+mBitmapScaled.getHeight());*/

            mCircleX = (mPreviousX1);
            mCircleY = (mPreviousY1);
            canvas.drawCircle(mCircleX,mCircleY,mCircleRadius,mPaint);
            canvas.drawText("D", mPreviousX1,mPreviousY1, cPaint);
            //canvas.drawText("D", mPreviousX+mBitmapScaled.getWidth(),mPreviousY+mBitmapScaled.getHeight(), cPaint);
        }
       else if(mTriggerBitmapstop)
               {
                   Log.e("Inside mTriggerbitmap", "In Customview() class");
                 //mPaint.setColor(Color.WHITE);
                    mPaint.setColor(Color.parseColor("#4D000000"));
                    canvas.drawRect(mPreviousX, mPreviousY, mPreviousX + mBitmap.getWidth(), mPreviousY + mBitmap.getWidth(), mPaint);
                    mPaint.setColor(Color.parseColor("#4D000000"));
                    canvas.drawBitmap(mBitmap, mPreviousX, mPreviousY, mPaint);
                    mPaint.setTextSize(30);
                    mPaint.setColor(Color.WHITE);
                    mPaint.setFakeBoldText(true);
                    mCircleX = (mPreviousX + mBitmap.getWidth());
                    mCircleY = (mPreviousY + mBitmap.getHeight());
                    canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
                    canvas.drawText("D", mPreviousX + mBitmap.getWidth(), mPreviousY + mBitmap.getHeight(), cPaint);
                }
        else {
            mPaint.setColor(Color.parseColor("#4D000000"));
            canvas.drawRect(mPreviousX2, mPreviousY2, mPreviousX2 + mBitmap.getWidth(), mPreviousY2 + mBitmap.getWidth(), mPaint);
            mPaint.setColor(Color.parseColor("#4D000000"));
            canvas.drawBitmap(mBitmap, mPreviousX2, mPreviousY2, mPaint);
            mPaint.setTextSize(30);
            mPaint.setColor(Color.WHITE);
            mPaint.setFakeBoldText(true);
            mCircleX = (mPreviousX2 + mBitmap.getWidth());
            mCircleY = (mPreviousY2 + mBitmap.getHeight());
            canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
            canvas.drawText("D", mPreviousX2 + mBitmap.getWidth(), mPreviousY2 + mBitmap.getHeight(), cPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("onTouchEvent()", "In Customview() class");
        boolean value = super.onTouchEvent(event);
        float x,dx=0 ;
        float y,dy =0;
        double dx1,dy1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                System.out.println("x = " +x);
                System.out.println("y = " +y);
                touching = true;
                    Log.i("Inside ACTIONDOWN", "In Customview() class");
                    dx = (x-mCircleX);
                    dy = (y-mCircleY);
                    if(Math.abs(dx+dy) < mCircleRadius)
                    {
                        Log.i("Inside Circle Touched" +
                                "", "In Customview() class");
                        mCircleTouched = true;

                    }

                if ((x > mPreviousX && y > mPreviousY) && (x > dx && y>dy)) {
                    drawingPicOffset_x = (int) x - mPreviousX;
                    drawingPicOffset_y = (int) y - mPreviousY;
                    drawingTouch = true;
                }

            break;

            case MotionEvent.ACTION_MOVE:
                 x = event.getX();
                 y = event.getY();
                if(drawingTouch) {

                        System.out.println("x in Action Move= " + x);
                        System.out.println("y in Action Move = " + y);
                        mPreviousX = x - drawingPicOffset_x;
                        mPreviousY = y - drawingPicOffset_y;


                        System.out.println("mPreviousX  in Action Move= " + mPreviousX );
                        System.out.println("mPreviousY in Action Move = " + mPreviousY);
                        mTriggerBitmapstop = true;

                }
                /*if(drawingTouch) {
                     mPreviousX = x - drawingPicOffset_x ;
                     mPreviousY = y - drawingPicOffset_y;
                    Log.i("ACTIONDOWN", "In Customview() class");
                 }*/

                if(mCircleTouched) {

                    System.out.println("x in Action and Circle Touched= " +x);
                    System.out.println("y in Action and Circle Touched = " +y);
                    mPreviousX1 = x;
                    mPreviousY1 = y;
                    mTriggerBitmap = true;
                    drawingTouch = false;
                    mTriggerBitmapstop = false;

                    //drawingTouch = true;
                    Log.i("mCircleTouched", "Action Move class");
                }
              //  mCircleTouched = false;

            break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                y = event.getY();
                Log.e("Inside Action Up" +
                        "", "In Customview() class........................");
              //  mTriggerBitmapstop = true;
                if(mCircleTouched) {

                   // mTriggerBitmapstop = true;
                    drawingTouch = false;
                    mCircleTouched = false;
                    mTriggerBitmapstop = false;
                    mTriggerBitmap= false;
                }

            default:
                drawingTouch = false;
                touching = false;
                mTriggerBitmapstop = false;
                mCircleTouched = false;
        }

        postInvalidate();
        return true;
    }
}



