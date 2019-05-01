package com.example.fragment_button_click.opengl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class openglOverlayGLSurfaceView extends GLSurfaceView {

        private openglOverlayRenderer mRenderer;
        private Context context;

        private final float TOUCH_SCALE_FACTOR = 180.0f / 520;
        private float mPreviousX;
        private float mPreviousY;

    public openglOverlayGLSurfaceView(Context context) {
        super(context);
         Log.i("mGLSurfaceView()","In mGLSurfaceView Class");
        this.context = context;
        setEGLContextClientVersion(2);
        mRenderer = new openglOverlayRenderer(context);
        setRenderer(mRenderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


        @Override
        public boolean onTouchEvent(MotionEvent e) {
            Log.i("onTouchEvent()","In mGLSurfaceView Class");
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


