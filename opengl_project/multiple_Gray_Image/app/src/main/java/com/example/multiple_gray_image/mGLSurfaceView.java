package com.example.multiple_gray_image;


import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class mGLSurfaceView extends GLSurfaceView {

    private mGLRenderer mRenderer;
    private Context context;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 520;
    private float mPreviousX;
    private float mPreviousY;

    public mGLSurfaceView(Context context) {
        super(context);
        //this.context = context;
        setEGLContextClientVersion(2);
        mRenderer = new mGLRenderer(context);
        setRenderer(mRenderer);

        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }



    public void requestRender() {

        setRenderer(mRenderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }
                mRenderer.setAngle(
                        mRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                requestRender();
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}
