package com.example.fragment_button_click;

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

import java.util.concurrent.TimeUnit;

import static android.os.SystemClock.sleep;


public class mGLRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "MyGLRenderer";
    private Square mSquare;
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];


    public volatile float mAngle;
    private float mCubeRotation;
    private long mLastUpdateMillis;
    private static final float CUBE_ROTATION_INCREMENT = 0.1f;

    /** The refresh rate, in frames per second. */
    private static final int REFRESH_RATE_FPS = 120;

    /** The duration, in milliseconds, of one frame. */
    private static final float FRAME_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1) / REFRESH_RATE_FPS;

    private static final int cols = 10;;
    private static final int rows = 10;
    public int NUMBER_OF_TEXTURES = 2;
    public int NUMBER_OF_TEXTURES_COUNT =0;
    public int BITMAP =0;
    public int RAW_IMAGE = 1;

    public   byte[][] mtextureBytes = new byte[NUMBER_OF_TEXTURES][cols *rows*3] ;

    final int[] mtextureHandle = new int[NUMBER_OF_TEXTURES];
    public int []  mTextureDataHandle = new int[NUMBER_OF_TEXTURES];

    public ByteBuffer[]  mtexturebuffer = new ByteBuffer[NUMBER_OF_TEXTURES];


    private Context context;
    public  mGLRenderer(Context context)
    {
        this.context = context;
    }



    public  int loadGLTexture1(final Context context, final int resourceId)
    {
        Log.i("loadGLTexture1()","In mGLRenderer Class");
        Random random = new Random();
        int mred,mgreen,mblue;
        int mgrayValue;

        mtexturebuffer[NUMBER_OF_TEXTURES_COUNT] = ByteBuffer.wrap(mtextureBytes[NUMBER_OF_TEXTURES_COUNT]);

        for ( int i = 0; i < (cols *rows); i++)
        {
            mred = random.nextInt(255);
            mgreen = random.nextInt(255);
            mblue = random.nextInt(255);
            //if(NUMBER_OF_TEXTURES_COUNT == 0)
            // {
            mgrayValue = (mred + mgreen + mblue)/3;

            mred = mgrayValue;
            mgreen = mgrayValue;
            mblue = mgrayValue;

            mtexturebuffer[NUMBER_OF_TEXTURES_COUNT].put(((i*3)+0),(byte)mred);
            mtexturebuffer[NUMBER_OF_TEXTURES_COUNT].put(((i*3)+1),(byte)mgreen);
            mtexturebuffer[NUMBER_OF_TEXTURES_COUNT].put(((i*3)+2),(byte)mblue);

        }


        if(mtextureHandle[NUMBER_OF_TEXTURES_COUNT] != 0)
        {

            if(BITMAP == 1)
            {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = false;
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),resourceId, options);
                mGLRenderer.checkGlError("BitmapFactory.decodeResource");

                GLES20.glActiveTexture(GLES20.GL_TEXTURE0+NUMBER_OF_TEXTURES_COUNT);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mtextureHandle[NUMBER_OF_TEXTURES_COUNT]);
                GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
                GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
                GLUtils.texImage2D(GLES20.GL_TEXTURE_2D,0,bitmap,0);
                bitmap.recycle();
            }

            if(RAW_IMAGE == 1) {

                GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + NUMBER_OF_TEXTURES_COUNT);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mtextureHandle[NUMBER_OF_TEXTURES_COUNT]);
                GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
                GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

                GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, cols, rows, 0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_BYTE, mtexturebuffer[NUMBER_OF_TEXTURES_COUNT]);
                GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
            }


        }

        if(mtextureHandle[NUMBER_OF_TEXTURES_COUNT] == 0)
        {
            mGLRenderer.checkGlError("BitmapFactory.decodeResource");
            throw new RuntimeException("Error loading Texture.");
        }
        return mtextureHandle[NUMBER_OF_TEXTURES_COUNT];

    }


    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        Log.i("onSurfaceCreated","In mGLRenderer Class");
        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 0.1f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        mSquare   = new Square();

    }
    @Override
    public void onDrawFrame(GL10 unused) {

        Log.i("onDrawFrame()","In mGLRenderer Class");
        float[] scratch = new float[16];

        long time = SystemClock.uptimeMillis() % 4000L;
        float angle = 0.090f * ((int) time);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
        GLES20.glGenTextures(2,mtextureHandle,0);

        mTextureDataHandle[NUMBER_OF_TEXTURES_COUNT] = loadGLTexture1(context, R.drawable.brick);
        NUMBER_OF_TEXTURES_COUNT++;
        mTextureDataHandle[NUMBER_OF_TEXTURES_COUNT] = loadGLTexture1(context, R.drawable.heart);
        mGLRenderer.checkGlError("loadGLTexture");

       /* for(int i=0; i< NUMBER_OF_TEXTURES_COUNT ;i++ )
        {
            mTextureDataHandle[NUMBER_OF_TEXTURES_COUNT] = loadGLTexture1(context, R.drawable.heart);
            NUMBER_OF_TEXTURES_COUNT++;
            mGLRenderer.checkGlError("loadGLTexture");
        }*/
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 5, 0f, 0f, 0.0f, 0f, 1.0f, 0.0f);

        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        mSquare.draw(mMVPMatrix,mTextureDataHandle[0],mTextureDataHandle[1]);
        NUMBER_OF_TEXTURES_COUNT =0;
        sleep(6000 );


    }
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        Log.i("onSurfaceChanged()","In mGLRenderer Class");
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }


    public static int loadShader(int type, String shaderCode){
        Log.i("loadshader()","In mGLRenderer Class");
        int mshader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(mshader, shaderCode);
        GLES20.glCompileShader(mshader);
        return mshader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
    private void updateCubeRotation(){
        Log.i("onSurfaceCreated()","In mGLRenderer Class");
        if (mLastUpdateMillis != 0) {
            float factor = (SystemClock.elapsedRealtime() - mLastUpdateMillis) / FRAME_TIME_MILLIS;
            mCubeRotation += CUBE_ROTATION_INCREMENT * factor;
        }
        mLastUpdateMillis = SystemClock.elapsedRealtime();
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


