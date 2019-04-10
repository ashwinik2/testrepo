package com.example.multiple_gray_image;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import android.opengl.GLES20;
import android.opengl.Matrix;

public class Square {
    private final String vertexShaderCode =


            "attribute vec3 vPosition;" +
                    "attribute vec3 vColor;" +
                    "varying vec3 v_Color; " +
                    "varying vec3 v_Position; " +
                    "uniform mat4 uMVPMatrix;" +
                    "attribute vec2 vTexture ;"+
                    "varying vec2 v_texCoordinate; " +


                    "void main() {" +
                    "gl_Position = uMVPMatrix * vec4(vPosition,1.0);" +
                    "v_Color = vColor ;" +
                    "v_texCoordinate = vTexture;"+

                    "}";

    private final String fragmentShaderCode =

            "precision mediump float;" +
                    "varying vec3 v_Color; " +
                    "varying vec2 v_texCoordinate;"+
                    "uniform sampler2D u_Texture_1;" +
                    "uniform sampler2D u_Texture_2;" +
                    "vec3 average = vec3(2,2,2);"  +

                    "void main() {" +

                         "vec3 rgb_pixel_color_texture1 = texture2D(u_Texture_1, v_texCoordinate).rgb;"+
                         "vec3 rgb_pixel_color_texture2 = texture2D(u_Texture_2, v_texCoordinate).rgb;"+

                         "vec3 rgb_value_tex1_tex2 = (rgb_pixel_color_texture1+rgb_pixel_color_texture2);"+

                         "vec3 result = rgb_value_tex1_tex2/average;"+
                        //  "vec3 result = mix(texture2D(u_Texture_1, v_texCoordinate).rgb, texture2D(u_Texture_2, v_texCoordinate).rgb, 0.5);"+

                         "gl_FragColor = vec4(result,1.0);" +

                    "}";


    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final FloatBuffer colorBuffer;

    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    private int mMVPMatrixHandle;

    private int ambientLightUniformLocation;
    public int NUMBER_OF_TEXTURES = 2;
    public int NUMBER_OF_VERTICES = 6;

    public int[] mTextureUniformHandle = new int[NUMBER_OF_TEXTURES];
    public int NUMBER_OF_TEXTURES_COUNT =0;
    private int mTextureHandle;
    private final FloatBuffer textureBuffer;


    static final int COORDS_PER_VERTEX = 3;
    static final int COLOR_COORDS_PER_VERTEX = 3;
    static final int TextCOORDS_PER_VERTEX = 2;

    private final int vertexStride = COORDS_PER_VERTEX * 4;
    private final int colorvertexStride = COLOR_COORDS_PER_VERTEX * 4;
    private final int textureStride = TextCOORDS_PER_VERTEX * 4;


    static float squareCoords[] = {
            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
    };


    private short drawOrder[] = {
            0,   1,  2,  3,  4,  5,
    };

    float color[] = {

            // R, G, B,
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,


    };
    static float textureCoords[] = {
            0.0f,0.0f,
            0.0f,1.0f,
            1.0f,0.0f,
            0.0f,1.0f,
            1.0f,1.0f,
            1.0f,0.0f,
    };

    public Square()
    {
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);



        ByteBuffer cb = ByteBuffer.allocateDirect(color.length * 4);
        cb.order(ByteOrder.nativeOrder());
        colorBuffer = cb.asFloatBuffer();
        colorBuffer.put(color);
        colorBuffer.position(0);

        ByteBuffer tbuffer = ByteBuffer.allocateDirect(textureCoords.length * 4);
        tbuffer.order(ByteOrder.nativeOrder());
        textureBuffer = tbuffer.asFloatBuffer();
        textureBuffer.put(textureCoords);
        textureBuffer.position(0);


        int vertexShader = mGLRenderer.loadShader(
                GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = mGLRenderer.loadShader(
                GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    public void draw(float[] mvpMatrix,int mTextureDataHandle_1,int mTextureDataHandle_2)
    {
        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);


        mColorHandle= GLES20.glGetAttribLocation(mProgram, "vColor");
        GLES20.glEnableVertexAttribArray(mColorHandle);
        GLES20.glVertexAttribPointer(
                mColorHandle, COLOR_COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                colorvertexStride, colorBuffer);

        mTextureHandle = GLES20.glGetAttribLocation(mProgram, "vTexture");
        GLES20.glEnableVertexAttribArray( mTextureHandle);
        GLES20.glVertexAttribPointer(
                mTextureHandle, TextCOORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                textureStride,textureBuffer);


        mTextureUniformHandle[NUMBER_OF_TEXTURES_COUNT] = GLES20.glGetUniformLocation(mProgram,"u_Texture_1");
        mGLRenderer.checkGlError("glGetUniformLocation");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0+NUMBER_OF_TEXTURES_COUNT);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,mTextureDataHandle_1);
        GLES20.glUniform1i( mTextureUniformHandle[NUMBER_OF_TEXTURES_COUNT], NUMBER_OF_TEXTURES_COUNT);

        NUMBER_OF_TEXTURES_COUNT++;

        mTextureUniformHandle[NUMBER_OF_TEXTURES_COUNT] = GLES20.glGetUniformLocation(mProgram,"u_Texture_2");
        mGLRenderer.checkGlError("glGetUniformLocation");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0+NUMBER_OF_TEXTURES_COUNT);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,mTextureDataHandle_2);
        GLES20.glUniform1i( mTextureUniformHandle[NUMBER_OF_TEXTURES_COUNT], NUMBER_OF_TEXTURES_COUNT);

        NUMBER_OF_TEXTURES_COUNT = 0;


        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        //    MyGLRenderer.checkGlError("glGetUniformLocation");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        //   MyGLRenderer.checkGlError("glUniformMatrix4fv");

        GLES20.glDrawArrays(
                GLES20.GL_TRIANGLES, 0,NUMBER_OF_VERTICES);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}


