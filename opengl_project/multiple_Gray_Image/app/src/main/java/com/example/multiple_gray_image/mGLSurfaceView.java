package com.example.multiple_gray_image;


import android.opengl.GLSurfaceView;
import android.content.Context;
import android.view.MotionEvent;

public class mGLSurfaceView extends GLSurfaceView {
    private final mGLRenderer mRenderer;
    public mGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        mRenderer = new mGLRenderer(context);
        setRenderer(mRenderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
    private final float TOUCH_SCALE_FACTOR = 180.0f / 520;
    private float mPreviousX;
    private float mPreviousY;
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
