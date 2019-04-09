package com.example.multiple_gray_image;

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
    //private Triangle mTriangle;
    private Square mSquare;
    private final float[] mMVPMatrix = new float[16];
    //private final float[] mModelViewMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];
    private final  float[] mModelMatrix = new float[16];
    // private final  float[] mModel1Matrix = new float[16];
    //  private final  float[] mViewCopyMatrix = new float[16];
    public float[] mTempMatrix = new float[16];
    public int mTextureDataHandle;
    public int mTextureDataHandle2;
    public volatile float mAngle;
    private float mCubeRotation;
    private long mLastUpdateMillis;
    private static final float CUBE_ROTATION_INCREMENT = 0.1f;

    /** The refresh rate, in frames per second. */
    private static final int REFRESH_RATE_FPS = 120;

    /** The duration, in milliseconds, of one frame. */
    private static final float FRAME_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1) / REFRESH_RATE_FPS;

    private static final int cols = 3;//9201920;
    private static final int rows = 3;//;
    private static final int NUMBER_OF_TEXTURES = 2;


    public   byte[] textureBytes1 = new byte[cols *rows*3] ;
    public   byte[] textureBytes2 = new byte[cols *rows*3] ;
    final int[] textureHandle = new int[2];


    public ByteBuffer  texturebuffer1;
    public ByteBuffer  texturebuffer2;

    private Context context;
    public  mGLRenderer(Context context){
        this.context = context;
    }


    public  void   initialize_texture_array()
    {

    };

    public  int loadGLTexture1(final Context context, final int resourceId)
    // public  int loadGLTexture1(final Context context)
    {


        Random random = new Random();
        int r,g,b,k;
        int i;
        // buffer = ByteBuffer.allocate(cols *rows*3);
        texturebuffer1 = ByteBuffer.wrap(textureBytes1);
        for ( i = 0; i < (cols *rows); i++) {
            r = random.nextInt(255);
            g = random.nextInt(255);
            b = random.nextInt(255);
            k= (r+g+b)/3;
            r =k;
            g=k;
            b=k;
            texturebuffer1.put(((i*3)+0),(byte)r);
            texturebuffer1.put(((i*3)+1),(byte)g);
            texturebuffer1.put(((i*3)+2),(byte)b);

        }




        if(textureHandle[0] != 0)
        {

            /*final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),resourceId, options);
            mGLRenderer.checkGlError("BitmapFactory.decodeResource");
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
            //GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_REPEAT);
            //GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_REPEAT);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);

            //GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, cols, rows, 0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_BYTE,buffer);
            //GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D,0,bitmap,0);
            bitmap.recycle();*/
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);

            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, cols, rows, 0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_BYTE, texturebuffer1);
            GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
         /*   GLUtils.texImage2D(GLES20.GL_TEXTURE_2D,0,bitmap,0);
            bitmap.recycle();*/

        }

        if(textureHandle[0] == 0)
        {
            mGLRenderer.checkGlError("BitmapFactory.decodeResource");
            throw new RuntimeException("Error loading Texture.");
        }
        return textureHandle[0];

    }

    public  int loadGLTexture2(final Context context, final int resourceId)
    // public  int loadGLTexture1(final Context context)
    {

        Random random = new Random();
        int r,g,b,k;
        int i;
        // buffer = ByteBuffer.allocate(cols *rows*3);
        texturebuffer2 = ByteBuffer.wrap(textureBytes2);
        for ( i = 0; i < (cols *rows); i++) {
            r = random.nextInt(255);
            g = random.nextInt(255);
            b = random.nextInt(255);
           /* k= (r+g+b)/3;
            r =k;
            g=k;
            b=k;*/
            texturebuffer2.put(((i*3)+0),(byte)r);
            texturebuffer2.put(((i*3)+1),(byte)g);
            texturebuffer2.put(((i*3)+2),(byte)b);

        }


        if(textureHandle[1] != 0)
        {

           /*final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),resourceId, options);
            mGLRenderer.checkGlError("BitmapFactory.decodeResource");
            GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[1]);
            //GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_REPEAT);
            //GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_REPEAT);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);

            //GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, cols, rows, 0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_BYTE,buffer);
            //GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D,0,bitmap,0);
            bitmap.recycle();*/


           GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[1]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);

            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGB, cols, rows, 0, GLES20.GL_RGB, GLES20.GL_UNSIGNED_BYTE,texturebuffer2);
            GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
         /*   GLUtils.texImage2D(GLES20.GL_TEXTURE_2D,0,bitmap,0);
            bitmap.recycle();*/

        }

        if(textureHandle[1] == 0)
        {
            mGLRenderer.checkGlError("BitmapFactory.decodeResource");
            throw new RuntimeException("Error loading Texture.");
        }
        return textureHandle[1];

    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 0.1f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //mTriangle = new Triangle();
        mSquare   = new Square();
        initialize_texture_array();
    }
    @Override
    public void onDrawFrame(GL10 unused) {
        float[] scratch = new float[16];

        long time = SystemClock.uptimeMillis() % 4000L;
        float angle = 0.090f * ((int) time);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
        GLES20.glGenTextures(2,textureHandle,0);

        //mTextureDataHandle = loadGLTexture1(context);
        mTextureDataHandle = loadGLTexture1(context, R.drawable.heart);
        mTextureDataHandle2 = loadGLTexture2(context, R.drawable.brick);
        mGLRenderer.checkGlError("loadGLTexture");

        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 5, 0f, 0f, 0.0f, 0f, 1.0f, 0.0f);


       /* Matrix.setIdentityM(mModelMatrix, 0); // initialize to identity matrix
        Matrix.translateM(mModelMatrix, 0, -0.5f, 0.0f, 0.0f); // translation to the left
        // Matrix.translateM(mModel1Matrix, 0, 0.5f, 0.0f, 0);
        Matrix.setRotateM(mRotationMatrix, 0, angle, 0.0f, 1.0f, 0.0f);
        mTempMatrix = mModelMatrix.clone();
        Matrix.multiplyMM(mModelMatrix, 0, mTempMatrix, 0, mRotationMatrix, 0);*/
        //  Matrix.multiplyMM(mModelMatrix, 0,mRotationMatrix, 0, mTempMatrix,0);
        // Matrix.multiplyMM(mModelViewMatrix, 0, mTempMatrix, 0, mRotationMatrix, 0);

        //  mTempMatrix = mModelMatrix.clone();
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        // mTempMatrix = mMVPMatrix.clone();
        // Matrix.multiplyMM(mMVPMatrix, 0, mTempMatrix, 0, mModelMatrix, 0);
        mSquare.draw(mMVPMatrix,mTextureDataHandle,mTextureDataHandle2);
        sleep(6000 );


    }
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }


    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
    private void updateCubeRotation(){
        if (mLastUpdateMillis != 0) {
            float factor = (SystemClock.elapsedRealtime() - mLastUpdateMillis) / FRAME_TIME_MILLIS;
            mCubeRotation += CUBE_ROTATION_INCREMENT * factor;
        }
        mLastUpdateMillis = SystemClock.elapsedRealtime();
    }

    public float getAngle()
    {
        return mAngle;
    }

    public void setAngle(float angle)
    {
        mAngle = angle;
    }
}

