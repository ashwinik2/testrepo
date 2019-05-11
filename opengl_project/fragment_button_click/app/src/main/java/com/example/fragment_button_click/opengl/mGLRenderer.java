package com.example.fragment_button_click.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES20;

import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;
import java.util.Random;
import java.nio.ByteBuffer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.example.fragment_button_click.R;

import java.util.concurrent.TimeUnit;

import static android.os.SystemClock.sleep;

public class mGLRenderer implements GLSurfaceView.Renderer {

    static {
        System.loadLibrary("Datarenderer");
    }
    private static final String TAG = "MyGLRenderer";

    public volatile float mAngle;
    private static native void initGL();
    private static native void resizeGL(int width, int height);
    private static native void drawGL();

    private Context mContext;
    public  mGLRenderer(Context context)
    {
        this.mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        Log.i("onSurfaceCreated","In mGLRenderer Class");

        Log.i("onSurfaceCreated", GLES20.glGetString(GLES20.GL_VERSION));
        Log.i("onSurfaceCreated", GLES20.glGetString(GLES20.GL_RENDERER));
        Log.i("onSurfaceCreated", GLES20.glGetString(GLES20.GL_SHADING_LANGUAGE_VERSION));
        Log.i("onSurfaceCreated", GLES20.glGetString(GLES20.GL_EXTENSIONS));
        initGL();

    }
    @Override
    public void onDrawFrame(GL10 unused)
    {
        Log.i("onDrawFrame()","In mGLRenderer Class");
        drawGL();
    }
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        Log.i("onSurfaceChanged()","In mGLRenderer Class");

        System.out.println("GL Renderer Surface Width  = " + width);
        System.out.println("GL Renderer Surface Height  = " + height);
        resizeGL(width, height);
    }
    public float getAngle()
    {
        Log.i("getAngle()","In mGLRenderer Class");
        return mAngle;
    }

    public void setAngle(float angle)
    {
        Log.i("setAngle()","In mGLRenderer Class");
        mAngle = angle;
    }
}


