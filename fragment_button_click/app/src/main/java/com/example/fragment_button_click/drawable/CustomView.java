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

    int mWidth=0;
    int mHeight =0;

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
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
        createBitmapData();

    }

    private static final int cols = 200;
    private static final int rows = 200;
    public byte[] mtextureBytes = new byte[cols * rows * 4];
    private float mCurrentPosX = 0;
    private float mCurrentPosY = 0;
    private float mPreviousPosX = 0;
    private float mPreviousPosY = 0;

    private float mOriginalPosX = 0;
    private float mOriginalPosY = 0;
    private float mCurrentPosX3 = 0;
    private float mCurrentPosY3= 0;
    private float mCurrentPosX4 = 0;
    private float mCurrentPosY4= 0;
    private float mCurrentPosX5 = 0;
    private float mCurrentPosY5= 0;
    float drawingPicOffset_x = 0, drawingPicOffset_y = 0;
    float mCircleX,mCircleY,mCircleRadius = 40f;
    Boolean mCircleTouched = false;
    Boolean mTriggerDrag = false;
    Boolean mTriggerZoom = false;
    Boolean mMouseClickedOutside = false;
    Boolean mMouseClickedOutside1 = false;
    Boolean Zoom = false;
    Boolean Drag = false;
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

        mScaledWidth = width;
        mScaledHeight = height;
        if(width !=0 && height !=0) {
            mBitmapScaled = Bitmap.createScaledBitmap(bitmap, mScaledWidth, mScaledHeight, true);
        }
        return mBitmapScaled;
    }
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Log.i("draw()", " In customView");
        mPaint.setColor(Color.WHITE);

        if (mCurrentPosX == 0f || mCurrentPosY == 0f) {
            mCurrentPosX  =  canvas.getWidth()/2 -  mBitmap.getWidth()/2 ;
            mCurrentPosY  =  canvas.getHeight()/2  - mBitmap.getHeight()/2 ;

            mOriginalPosX = mCurrentPosX ;
            mOriginalPosY = mCurrentPosY ;
            mPreviousPosX = mCurrentPosX;
            mPreviousPosY = mCurrentPosY;
            mCurrentPosX3 = mCurrentPosX;
            mCurrentPosY3 = mCurrentPosY;
            mCurrentPosX4 = mCurrentPosX;
            mCurrentPosY4 = mCurrentPosY;
        }

    

        if((mCircleTouched == true && mTriggerDrag == true) )
        {
            int width =(int)Math.round(mCurrentPosX-mPreviousPosX);
            int height = (int)Math.round(mCurrentPosY-mPreviousPosY);
            mWidth = width;
            mHeight = height;

            Log.e("mCircleTouched == true", "In onDraw() in Customview() class");
            System.out.println("mCurrentPosX= " +mCurrentPosX);
            System.out.println("mCurrentPosY= " +mCurrentPosY);

            System.out.println("mPreviousPosX= " +mPreviousPosX);
            System.out.println("mPreviousPosY= " +mPreviousPosY);

            System.out.println("bitmap width inside circletouched= " +width);
            System.out.println("bitmap height inside circletouched= " +height);
            mBitmapScaled = scaleBitmap(mBitmap,width,height);

            mPaint.setColor(Color.WHITE);
            mPaint.setColor(Color.parseColor("#4D000000"));
            if((width!=0 ) && (height != 0)) {
                canvas.drawRect(mPreviousPosX, mPreviousPosY, mCurrentPosX, mCurrentPosY, mPaint);

                mPaint.setColor(Color.parseColor("#4D000000"));
                canvas.drawBitmap(mBitmapScaled, mPreviousPosX, mPreviousPosY, mPaint);
                mPaint.setTextSize(30);
                mPaint.setColor(Color.WHITE);
                mPaint.setFakeBoldText(true);
                mCircleX = (mCurrentPosX);
                mCircleY = (mCurrentPosY);
                canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
                canvas.drawText("D", mCurrentPosX, mCurrentPosY, cPaint);
            }
            mCurrentPosX4 = mCurrentPosX;
            mCurrentPosY4 = mCurrentPosY;
            mCurrentPosX3 = mPreviousPosX;
            mCurrentPosY3 = mPreviousPosY;
        }
        // moouse move on bitmap
        else if(mTriggerDrag)
        {
            if (Zoom == true)
            {
                Log.e("Inside mTriggerDrag", "In Customview() class");
                mPaint.setColor(Color.parseColor("#4D000000"));
                System.out.println("mWidth= " + mWidth);
                System.out.println("mHeight= " + mHeight);
                canvas.drawRect(mCurrentPosX, mCurrentPosY, mCurrentPosX + mWidth, mCurrentPosY + mHeight, mPaint);
                mPaint.setColor(Color.parseColor("#4D000000"));
                canvas.drawBitmap(mBitmapScaled, mCurrentPosX, mCurrentPosY, mPaint);
                mPaint.setTextSize(30);
                mPaint.setColor(Color.WHITE);
                mPaint.setFakeBoldText(true);
                mCircleX = (mCurrentPosX + mWidth);
                mCircleY = (mCurrentPosY + mHeight);
                canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
                canvas.drawText("D", mCurrentPosX + mWidth, mCurrentPosY + mHeight, cPaint);
                mCurrentPosX3 = mCurrentPosX;
                mCurrentPosY3 = mCurrentPosY;
            }
            else
                {
                Log.e("Zoom true in last else2", "In Customview() class");
                System.out.println("mPreviousPosX last else= " + mCurrentPosX);
                System.out.println("mPreviousPosY last else= " + mCurrentPosY);
                mPaint.setColor(Color.parseColor("#4D000000"));
                canvas.drawRect(mCurrentPosX, mCurrentPosY, mCurrentPosX + mBitmap.getWidth(), mCurrentPosY + mBitmap.getWidth(), mPaint);
                mPaint.setColor(Color.parseColor("#4D000000"));
                canvas.drawBitmap(mBitmap, mCurrentPosX, mCurrentPosY, mPaint);
                mPaint.setTextSize(30);
                mPaint.setColor(Color.WHITE);
                mPaint.setFakeBoldText(true);
                mCircleX = (mCurrentPosX + mBitmap.getWidth());
                mCircleY = (mCurrentPosY + mBitmap.getHeight());
                canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
                canvas.drawText("D", mCurrentPosX + mBitmap.getWidth(), mCurrentPosY + mBitmap.getHeight(), cPaint);
                mCurrentPosX3 = mCurrentPosX;
                mCurrentPosY3 = mCurrentPosY;
                mCurrentPosX4 = mCurrentPosX + mBitmap.getWidth();
                mCurrentPosY4 = mCurrentPosY + mBitmap.getWidth();
            }

        }
            // mouse down, move and up when zoom and circle touched
        else if (mTriggerZoom)
        {
                Log.e("down,move,up", "In Customview() class");
                Log.e("Inside mTriggerDrag1", "In Customview() class");

                int width = (int) Math.round(mCurrentPosX - mPreviousPosX);
                int height = (int) Math.round(mCurrentPosY - mPreviousPosY);
                mWidth = width;
                mHeight = height;

                System.out.println("mCurrentPosX= " + mCurrentPosX);
                System.out.println("mCurrentPosY= " + mCurrentPosY);

                System.out.println("mPreviousPosX= " + mPreviousPosX);
                System.out.println("mPreviousPosY= " + mPreviousPosY);

                System.out.println("bitmap width inside circletouched= " + width);
                System.out.println("bitmap height inside circletouched= " + height);
                mBitmapScaled = scaleBitmap(mBitmap, width, height);

                mPaint.setColor(Color.WHITE);
                mPaint.setColor(Color.parseColor("#4D000000"));
                if ((width != 0) && (height != 0)) {
                    canvas.drawRect(mPreviousPosX, mPreviousPosY, mCurrentPosX, mCurrentPosY, mPaint);
                    mPaint.setColor(Color.parseColor("#4D000000"));
                    canvas.drawBitmap(mBitmapScaled, mPreviousPosX, mPreviousPosY, mPaint);
                    mPaint.setTextSize(30);
                    mPaint.setColor(Color.WHITE);
                    mPaint.setFakeBoldText(true);

                    mCircleX = (mCurrentPosX);
                    mCircleY = (mCurrentPosY);
                    canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
                    canvas.drawText("D", mCurrentPosX, mCurrentPosY, cPaint);
                }

                mCurrentPosX3 = mPreviousPosX;
                mCurrentPosY3 = mPreviousPosY;
                mCurrentPosX4 = mCurrentPosX;
                mCurrentPosY4 = mCurrentPosY;
                //  mTriggerZoom = false;
            }
        // mouse down and up when zoom
        else if (Zoom && !drawingTouch)
        {
            Log.e("Zoom true in last else1", "In Customview() class");
            System.out.println("Zoom true in last else= " + mCurrentPosX);
            int width = (int) Math.round(mCurrentPosX4 - mCurrentPosX3);
            int height = (int) Math.round(mCurrentPosY4 - mCurrentPosY3);
            mWidth = width;
            mHeight = height;

            System.out.println("mCurrentPosX3= " + mCurrentPosX3);
            System.out.println("mCurrentPosY3= " + mCurrentPosY3);

            System.out.println("mPreviousPosX4= " + mCurrentPosX4);
            System.out.println("mPreviousPosY4= " + mCurrentPosY4);

            System.out.println("bitmap width inside circletouched= " + width);
            System.out.println("bitmap height inside circletouched= " + height);
            mBitmapScaled = scaleBitmap(mBitmap, width, height);

            mPaint.setColor(Color.WHITE);
            mPaint.setColor(Color.parseColor("#4D000000"));
            if ((width != 0) && (height != 0)) {
                canvas.drawRect(mCurrentPosX3, mCurrentPosX3, mCurrentPosX4, mCurrentPosY4, mPaint);
                mPaint.setColor(Color.parseColor("#4D000000"));
                canvas.drawBitmap(mBitmapScaled, mCurrentPosX3, mCurrentPosX3, mPaint);
                mPaint.setTextSize(30);
                mPaint.setColor(Color.WHITE);
                mPaint.setFakeBoldText(true);

                mCircleX = (mCurrentPosX4);
                mCircleY = (mCurrentPosY4);
                canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
                canvas.drawText("D", mCurrentPosX4, mCurrentPosY4, cPaint);
            }
        }
        else
            {

                Log.e("last else2", "In Customview() class");
                System.out.println("mPreviousPosX last else= " + mCurrentPosX);
                System.out.println("mPreviousPosY last else= " + mCurrentPosY);
                mPaint.setColor(Color.parseColor("#4D000000"));
                canvas.drawRect(mCurrentPosX, mCurrentPosY, mCurrentPosX + mBitmap.getWidth(), mCurrentPosY + mBitmap.getWidth(), mPaint);
                mPaint.setColor(Color.parseColor("#4D000000"));
                canvas.drawBitmap(mBitmap, mCurrentPosX, mCurrentPosY, mPaint);
                mPaint.setTextSize(30);
                mPaint.setColor(Color.WHITE);
                mPaint.setFakeBoldText(true);
                mCircleX = (mCurrentPosX + mBitmap.getWidth());
                mCircleY = (mCurrentPosY + mBitmap.getHeight());
                canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaint);
                canvas.drawText("D", mCurrentPosX + mBitmap.getWidth(), mCurrentPosY + mBitmap.getHeight(), cPaint);
                mPreviousPosX = mOriginalPosX;
                mPreviousPosY = mOriginalPosY;
            }

       }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("onTouchEvent()", "In Customview() class");
        boolean value = super.onTouchEvent(event);
        float x,dx=0 ;
        float y,dy =0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                Log.i("Inside ACTIONDOWN", "In Customview() class");
                System.out.println("ACTIONDOWN x = " +x);
                System.out.println("ACTIONDOWN y = " +y);
                System.out.println("ACTIONDOWN mCircleX = " +mCircleX);
                System.out.println("ACTIONDOWN mCircleY = " +mCircleY);

                dx = (x-mCircleX);
                dy = (y-mCircleY);
                System.out.println("ACTIONDOWN dx = " +dx);
                System.out.println("ACTIONDOWN dy = " +dy);
              //  if(Math.abs(dx+dy) < mCircleRadius)
                if(Math.sqrt(dx * dx + dy * dy) < mCircleRadius)
                {
                    Log.i("Inside Circle Touched" +
                            "", "In Customview() class");
                    mCircleTouched = true;
                    if(Zoom == true){
                        mTriggerZoom = true;
                        mCurrentPosX = x;
                        mCurrentPosY = y;
                    }

                    mPreviousPosX = mCurrentPosX3;
                    mPreviousPosY = mCurrentPosY3;
                    System.out.println("mCurrentPosX3 in action up circle touched= " + mCurrentPosX3);
                    System.out.println("mCurrentPosY3 in action up circle touched = " + mCurrentPosY3);
                }

                else if ((x > mCurrentPosX3 && y > mCurrentPosY3) && (x < mCurrentPosX3 + mWidth && y < mCurrentPosX3 +mHeight))
                {
                    Log.e("Inside ACTIONDOWN777711", "In Customview() class");
                    System.out.println("x in action up circle touched= " + x);
                    System.out.println("y action up circle touched = " + y);
                    System.out.println("mCurrentPosX3 in action up circle touched= " + mCurrentPosX3);
                    System.out.println("mCurrentPosY3in action up circle touched = " + mCurrentPosY3);
                    drawingPicOffset_x = (int) x - mPreviousPosX;
                    drawingPicOffset_y = (int) y - mPreviousPosY;
                    drawingTouch = true;
                    //mCircleTouched = false; //added
                }


              /*  if ((x < mCurrentPosX && y < mCurrentPosY)) {
                     drawingPicOffset_x = (int) x - mCurrentPosX;
                     drawingPicOffset_y = (int) y - mCurrentPosY;
                    drawingTouch = true;
                }*/

                break;

            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                if(mCircleTouched)
                {
                    Log.i("mCircleTouched", "Action Move class");
                    if(x > mCurrentPosX3 && y > mCurrentPosY3) {
                        mCurrentPosX = x;
                        mCurrentPosY = y;
                        mPreviousPosX = mCurrentPosX3;
                        mPreviousPosY = mCurrentPosY3;
                        System.out.println("x in Action and Circle Touched= " + x);
                        System.out.println("y in Action and Circle Touched = " + y);

                        mTriggerDrag = true;
                        Zoom = true;
                        drawingTouch = false;
                      //  mTriggerDrag = false;
                    }
                }

                else if(drawingTouch)
                {
                    Log.i("drawingTouch", "Action Move class");
                    System.out.println("x in Action Move= " + x);
                    System.out.println("y in Action Move = " + y);


                    mCurrentPosX = x;
                    mCurrentPosY = y;
                    mTriggerDrag = true;
                 //   mCircleTouched = false;//added
                }
                else {
                    mTriggerDrag = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                y = event.getY();
                Log.e("Inside Action Up" +
                        "", "In Customview() class........................");
                if(Zoom && mCircleTouched) {
                    Log.e("Inside Action Up1" +
                            "", "In Customview() class........................");
                    if (x > mCurrentPosX3 && y > mCurrentPosY3)
                    {
                        Log.e("Inside Action Up2" +
                                "", "In Customview() class........................");
                        System.out.println("mCurrentPosX  in Action up= " + mCurrentPosX);
                        System.out.println("mCurrentPosY in Action up = " + mCurrentPosY);
                        mCurrentPosX = x;
                        mCurrentPosY = y;
                        mTriggerZoom = true;
                        drawingTouch = false;
                        mCircleTouched = false;
                        mTriggerDrag = false;
                    }
                }

                break;
            default:

                Log.e("Default" +
                        "", "In Customview() class........................");
                drawingTouch = false;
                touching = false;
                mTriggerDrag = false;
                mTriggerZoom = false;
                mCircleTouched = false;
        }

        postInvalidate();
        return true;
    }
}

