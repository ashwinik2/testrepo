#include <cmath>
#include<iostream>
#include <sstream>
#include <stdio.h>
#include <string>
#include <EGL/egl.h>
#include <GLES2/gl2.h>
//#include <GLES3/gl3.h>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>
#include<ModeDrawable.h>
#include <android/log.h>
using namespace std;
using glm::vec3;
std::string glOperation;

ModeDrawable::ModeDrawable(int rows, int cols,const int FrameSize)
{
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::ModeDrawable()");
    /*MyLOGF("[GL_VERSION] %s", glGetString(GL_VERSION ));
    MyLOGF("[GL_SHADING_LANGUAGE_VERSION] %s", glGetString(GL_SHADING_LANGUAGE_VERSION));
    MyLOGF("[GL_RENDERER] %s", glGetString(GL_RENDERER));
    MyLOGF("[GL_EXTENSIONS] %s", glGetString(GL_EXTENSIONS));*/

	mRows = rows;
	mCols = cols;
	mFrameSize = FrameSize;
    load();
}


void ModeDrawable::checkGlError(std::string glOperation)
{
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "checkGlError : NoError()");

    GLenum err = glGetError();
    if (err == GL_NO_ERROR) {
        return;
    } else {
        MyLOGF("[FAIL GL] %s", glOperation.c_str());
    }

    switch(err) {

        case GL_INVALID_ENUM:
            MyLOGE("GL_INVALID_ENUM: GLenum argument out of range");
            break;

        case GL_INVALID_VALUE:
            MyLOGE("GL_INVALID_VALUE: numeric argument out of range");
            break;

        case GL_INVALID_OPERATION:
            MyLOGE("GL_INVALID_OPERATION: operation illegal in current state");
            break;

        case GL_INVALID_FRAMEBUFFER_OPERATION:
            MyLOGE("GL_INVALID_FRAMEBUFFER_OPERATION: framebuffer object is not complete");
            break;

        case GL_OUT_OF_MEMORY:
            MyLOGE( "GL_OUT_OF_MEMORY: not enough memory left to execute command");
            break;

        default:
            MyLOGE("unlisted error");
            break;
    }
}


void ModeDrawable::checkShaderCompileError(int shader,GLenum shaderType)
{
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, " In checkShaderCompileError");
     GLint compiled = GL_FALSE;
    glGetShaderiv(shader, GL_COMPILE_STATUS, &compiled);
    if (!compiled)
    {
        GLint infoLogLen = 0;
        glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &infoLogLen);
        if (infoLogLen > 0)
        {
            GLchar* infoLog = (GLchar*)malloc(infoLogLen);
            if (infoLog)
            {
                glGetShaderInfoLog(shader, infoLogLen, NULL, infoLog);
                ALOGE("Could not compile %s shader:\n%s\n",shaderType == GL_VERTEX_SHADER ? "vertex" : "fragment",infoLog);
                free(infoLog);
            }
        }
    }

}

ModeDrawable::~ModeDrawable()
{
    //glDeleteProgram(mProgram);
    //glDeleteTextures(1, &mTextureDataHandle);
 }
//#if 0
void ModeDrawable::load()
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "In ModeDrawable::load()");
    const char vertexShaderCode[] =
    "attribute vec3 vPosition;\n"
    "attribute vec3 vColorValues;\n"
    "attribute vec2 vTextCoards;\n"
    "varying vec3 v_Color;\n"
    "varying vec3 v_Position;\n"
    "varying vec2 v_TextCoords;\n"
    "uniform mat4 uMVPMatrix;\n"
    "void main() {\n"
        "gl_Position = uMVPMatrix * vec4(vPosition,1.0);\n"
        "v_Color = vColorValues;\n"
        "v_TextCoords = vTextCoards;\n"
    "}\n";

    const char fragmentShaderCode[] =
    "precision mediump float;\n"
    "varying vec3 v_Color;\n"
    "varying vec2 v_TextCoords;\n"
    "uniform sampler2D u_Texture;"

    "void main() {\n"
        "vec3 result = texture2D(u_Texture, v_TextCoords).rgb;\n"
        "gl_FragColor = vec4(result,1.0);\n"
    "}\n";


    glEnable(GL_CULL_FACE);
    glEnable(GL_DEPTH_TEST);

    glGenBuffers(1,&mVertexBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() mVertexBuffer glGenBuffers");
    checkGlError("glGenBuffers");
    glGenBuffers(1,&mColorBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() mColorBuffer glGenBuffers");
    checkGlError("glGenBuffers");
    glGenBuffers(1,&mTextureBuffer);
        __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() mColorBuffer glGenBuffers");
        checkGlError("glGenBuffers");

    mVertexShaderID = glCreateShader(GL_VERTEX_SHADER);
    mFragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
    glClearColor(0.5f, 0.8f, 0.8f, 0.0f);
    const char *adapter[1];
     adapter[0] = vertexShaderCode;
    glShaderSource(mVertexShaderID, 1,adapter,0);
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() vertexshader");
    checkGlError("glShaderSource");

    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() fragmentshader");
    adapter[0] = fragmentShaderCode;
    glShaderSource(mFragmentShaderID, 1,adapter,0);
    checkGlError("glShaderSource");


    glCompileShader(mVertexShaderID);
    checkGlError("glCompileShader");
    checkShaderCompileError(mVertexShaderID,GL_VERTEX_SHADER);
      __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() compile Vertexshader");

    glCompileShader(mFragmentShaderID);
    checkGlError("glCompileShader");
    checkShaderCompileError(mFragmentShaderID,GL_FRAGMENT_SHADER);
      __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() compile fragmentshader");

    mProgram = glCreateProgram();
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() compile fragmentshader");
    checkGlError("glCreateProgram");
    glAttachShader(mProgram, mVertexShaderID);
    checkGlError("glAttachShader");
    glAttachShader(mProgram, mFragmentShaderID);
    checkGlError("glAttachShader");
    glLinkProgram(mProgram);
    checkGlError("glLinkProgram");

    glUseProgram(mProgram);
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() glUseprogram");
    checkGlError("glUseProgram");

    mMVPMatrixHandle = glGetUniformLocation(mProgram, "uMVPMatrix");
    glGenTextures(1, &mTextureDataHandle);
        __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() glUseprogram");
        checkGlError("glGenTextures");
    mFramebuffer = new unsigned char[mFrameSize];

    glUseProgram(0);

}

/*int loadShader(int type, const char* shaderCode)
{
        int mShader = glCreateShader(type);
        glShaderSource(mShader, 1,&shaderCode,0);
        __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::loadshader");
        checkGlError("glShaderSource");

        glCompileShader(mShader);
        if(type == GL_VERTEX_SHADER)
        {
            __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() vertexshader");
            CheckGlError("glCompileShader");
            checkShaderCompileError(mShader,GL_VERTEX_SHADER);
        }else
        {
            __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() fragmentshader");
            CheckGlError("glCompileShader");
            checkShaderCompileError(mShader,GL_FRAGMENT_SHADER);

        }
        return mShader;
}*/


void ModeDrawable::draw(const glm::mat4& projMat)
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw()");

    glUseProgram(mProgram);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() glUseProgram");
    checkGlError("glUseProgram");

    mVertexPosHandle = glGetAttribLocation(mProgram,"vPosition");
    MyLOGI("[mVertexPosHandle] %d", mVertexPosHandle);
    glBindBuffer(GL_ARRAY_BUFFER,mVertexBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() VerPos glBindBuffer");
    checkGlError("glBindBuffer");
    glBufferData(GL_ARRAY_BUFFER,sizeof(squareCoords),squareCoords,GL_STATIC_DRAW);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() verPos glBufferData");
    checkGlError("glBufferData");
    glEnableVertexAttribArray(mVertexPosHandle);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() verPos glEnableVertexAttribArray");
    checkGlError("glEnableVertexAttribArray");
    glVertexAttribPointer(mVertexPosHandle,3,GL_FLOAT,GL_FALSE,0,0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() verPos glVertexAttribPointer");
    checkGlError("glVertexAttribPointer");

    MyLOGI("[mColorValHandle] %d", mColorValHandle);
     mColorValHandle = glGetAttribLocation(mProgram,"vColorValues");
    glBindBuffer(GL_ARRAY_BUFFER,mColorBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() col glBindBuffer");
    checkGlError("glBindBuffer");
    glBufferData(GL_ARRAY_BUFFER,sizeof(colorCoords),colorCoords,GL_STATIC_DRAW);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw()  color glBufferData");
    checkGlError("glBufferData");
    glEnableVertexAttribArray(mColorValHandle);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() color glEnableVertexAttribArray");
    checkGlError("glEnableVertexAttribArray");
    glVertexAttribPointer(mColorValHandle,3,GL_FLOAT,GL_FALSE,0,0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() col glVertexAttribPointer");
    checkGlError("glVertexAttribPointer");

    MyLOGI("[mColorValHandle] %d", mTexturePosHandle);
    mTexturePosHandle = glGetAttribLocation(mProgram,"vTextCoards");
    glBindBuffer(GL_ARRAY_BUFFER,mTextureBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() tex glBindBuffer");
    checkGlError("glBindBuffer");
    glBufferData(GL_ARRAY_BUFFER,sizeof(textureCoords),textureCoords,GL_STATIC_DRAW);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw()  tex glBufferData");
    checkGlError("glBufferData");
    glEnableVertexAttribArray(mTexturePosHandle);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() tex glEnableVertexAttribArray");
    checkGlError("glEnableVertexAttribArray");
    glVertexAttribPointer(mTexturePosHandle,2,GL_FLOAT,GL_FALSE,0,0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() tex glVertexAttribPointer");
    checkGlError("glVertexAttribPointer");


   mViewMatrix = glm::lookAt(vec3(0, 0, 1), vec3(0, 0, 0), vec3(0, 1.0, 0.0));
    glm::mat4 trans= glm::translate(glm::mat4(1.0f), glm::vec3(0.0, 0.0, 0.0));
       //glm::mat4 scale1= glm::scale(glm::mat4(1.0f), glm::vec3(1.5, 1.5, 0.5));
    glm::mat4 modelViewProjMat = projMat * mViewMatrix *trans /**scale1*/ ;
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() glUseprogram");

    mTextureUniformHandle= glGetUniformLocation(mProgram,"u_Texture");
    glActiveTexture(GL_TEXTURE0);
    checkGlError("glActiveTexture");
    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle);
    checkGlError("glBindTexture");

    glUniformMatrix4fv(mMVPMatrixHandle, 1, false, glm::value_ptr(modelViewProjMat));
    checkGlError("glUniformMatrix4fv");
    glUniform1i(mTextureUniformHandle,0);
    checkGlError("glUniformli");

    glDrawArrays(GL_TRIANGLES, 0, mNumberofVertices);
    checkGlError("glDrawArrays");
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() glDrawArrays");
    glDisableVertexAttribArray(mVertexPosHandle);
    glDisableVertexAttribArray(mColorValHandle);

    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glUseProgram(0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() glUseprogram(0)");
    checkGlError("glUseProgram");

}
//#endif
void ModeDrawable::updateViewMatrix()
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::updateViewMatrix()");

    glm::mat4 trans = glm::translate(glm::mat4(1.0f),vec3(0.0f,0.0f,-3.0f));
    mViewMatrix = trans;
}

void ModeDrawable::update(unsigned char* data/*, size_t dataLen*/)
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::update()");
    glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::glPixelStorei()");
    checkGlError("glPixelStorei");

    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle);
    checkGlError("glBindTexture");
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);


//    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, mCols, mRows, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, mCols, mRows, 0, GL_RGB, GL_UNSIGNED_BYTE, data);
    checkGlError("glTexImage2D");

    glBindTexture(GL_TEXTURE_2D, 0);
    checkGlError("glBindTexture");

  //  glPixelStorei(GL_UNPACK_ALIGNMENT, 0);
   // checkGlError("glPixelStorei");

}




/*#if 0
void ModeDrawable::load()
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "In ModeDrawable::load()");
    const char vertexShaderCode[] =
    "#version 300 es;\n"
    "in vec3 vPosition;\n"
    "in vec3 vColorValues;\n"
    "in vec2 vTextCoards;\n"
    "out vec3 v_Color;\n"
    "out vec2 v_TextCoords;\n"
    "uniform mat4 uMVPMatrix;\n"
    "void main() {\n"
        "gl_Position = uMVPMatrix * vec4(vPosition,1.0);\n"
        "v_Color = vColorValues;\n"
        "v_TextCoords = vTextCoards;\n"
    "}\n";

    const char fragmentShaderCode[] =
    "#version 300 es;\n"
    "precision mediump float;\n"
    "in vec3 v_Color;\n"
    "in vec2 v_TextCoords;\n"
    "uniform sampler2D u_Texture;"

    "void main() {\n"
        "vec3 result = texture2D(u_Texture, v_TextCoords).rgb;\n"
        "gl_FragColor = vec4(result,1.0);\n"
    "}\n";


    glEnable(GL_CULL_FACE);
    glEnable(GL_DEPTH_TEST);
    glGenVertexArrays(1, &vao);
    glBindVertexArray(vao);

    glGenBuffers(1,&mVertexBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() mVertexBuffer glGenBuffers");
    checkGlError("glGenBuffers");
    glBindBuffer(GL_ARRAY_BUFFER,mVertexBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() VerPos glBindBuffer");
    checkGlError("glBindBuffer");
    glBufferData(GL_ARRAY_BUFFER,sizeof(squareCoords),squareCoords,GL_STATIC_DRAW);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() verPos glBufferData");
    checkGlError("glBufferData");
    glEnableVertexAttribArray(mVertexPosHandle);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() verPos glEnableVertexAttribArray");
    checkGlError("glEnableVertexAttribArray");
    glVertexAttribPointer(mVertexPosHandle,3,GL_FLOAT,GL_FALSE,0,0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() verPos glVertexAttribPointer");
    checkGlError("glVertexAttribPointer");


    glGenBuffers(1,&mColorBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() mColorBuffer glGenBuffers");
    checkGlError("glGenBuffers");
    glBindBuffer(GL_ARRAY_BUFFER,mColorBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() col glBindBuffer");
    checkGlError("glBindBuffer");
    glBufferData(GL_ARRAY_BUFFER,sizeof(colorCoords),colorCoords,GL_STATIC_DRAW);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw()  color glBufferData");
    checkGlError("glBufferData");
    glEnableVertexAttribArray(mColorValHandle);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() color glEnableVertexAttribArray");
    checkGlError("glEnableVertexAttribArray");
    glVertexAttribPointer(mColorValHandle,3,GL_FLOAT,GL_FALSE,0,0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() col glVertexAttribPointer");
    checkGlError("glVertexAttribPointer");

    glGenBuffers(1,&mTextureBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() mColorBuffer glGenBuffers");
    checkGlError("glGenBuffers");
    glBindBuffer(GL_ARRAY_BUFFER,mTextureBuffer);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() tex glBindBuffer");
    checkGlError("glBindBuffer");
    glBufferData(GL_ARRAY_BUFFER,sizeof(textureCoords),textureCoords,GL_STATIC_DRAW);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw()  tex glBufferData");
    checkGlError("glBufferData");
    glEnableVertexAttribArray(mTexturePosHandle);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() tex glEnableVertexAttribArray");
    checkGlError("glEnableVertexAttribArray");
    glVertexAttribPointer(mTexturePosHandle,2,GL_FLOAT,GL_FALSE,0,0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() tex glVertexAttribPointer");
    checkGlError("glVertexAttribPointer");

    mVertexShaderID = glCreateShader(GL_VERTEX_SHADER);
    mFragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
    glClearColor(0.5f, 0.8f, 0.8f, 0.0f);
    const char *adapter[1];
     adapter[0] = vertexShaderCode;
    glShaderSource(mVertexShaderID, 1,adapter,0);
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() vertexshader");
    checkGlError("glShaderSource");

    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() fragmentshader");
    adapter[0] = fragmentShaderCode;
    glShaderSource(mFragmentShaderID, 1,adapter,0);
    checkGlError("glShaderSource");


    glCompileShader(mVertexShaderID);
    checkGlError("glCompileShader");
    checkShaderCompileError(mVertexShaderID,GL_VERTEX_SHADER);
      __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() compile Vertexshader");

    glCompileShader(mFragmentShaderID);
    checkGlError("glCompileShader");
    checkShaderCompileError(mFragmentShaderID,GL_FRAGMENT_SHADER);
      __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() compile fragmentshader");

    mProgram = glCreateProgram();
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() compile fragmentshader");
    checkGlError("glCreateProgram");
    glAttachShader(mProgram, mVertexShaderID);
    checkGlError("glAttachShader");
    glAttachShader(mProgram, mFragmentShaderID);
    checkGlError("glAttachShader");
    glLinkProgram(mProgram);
    checkGlError("glLinkProgram");

    glUseProgram(mProgram);
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() glUseprogram");
    checkGlError("glUseProgram");

    mMVPMatrixHandle = glGetUniformLocation(mProgram, "uMVPMatrix");
    glGenTextures(1, &mTextureDataHandle);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() glUseprogram");
    checkGlError("glGenTextures");

    mVertexPosHandle = glGetAttribLocation(mProgram,"vPosition");
    MyLOGI("[mVertexPosHandle] %d", mVertexPosHandle);

    MyLOGI("[mColorValHandle] %d", mColorValHandle);
    mColorValHandle = glGetAttribLocation(mProgram,"vColorValues");

    MyLOGI("[mColorValHandle] %d", mTexturePosHandle);
    mTexturePosHandle = glGetAttribLocation(mProgram,"vTextCoards");
    mFramebuffer = new unsigned char[mFrameSize];

    glUseProgram(0);

}

int loadShader(int type, const char* shaderCode)
{
        int mShader = glCreateShader(type);
        glShaderSource(mShader, 1,&shaderCode,0);
        __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::loadshader");
        checkGlError("glShaderSource");

        glCompileShader(mShader);
        if(type == GL_VERTEX_SHADER)
        {
            __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() vertexshader");
            CheckGlError("glCompileShader");
            checkShaderCompileError(mShader,GL_VERTEX_SHADER);
        }else
        {
            __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::load() fragmentshader");
            CheckGlError("glCompileShader");
            checkShaderCompileError(mShader,GL_FRAGMENT_SHADER);

        }
        return mShader;
}

void ModeDrawable::draw(const glm::mat4& projMat)
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw()");

    glUseProgram(mProgram);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() glUseProgram");
    checkGlError("glUseProgram");


    mViewMatrix = glm::lookAt(vec3(0, 0, 1), vec3(0, 0, 0), vec3(0, 1.0, 0.0));
    glm::mat4 trans= glm::translate(glm::mat4(1.0f), glm::vec3(0.0, 0.0, 0.0));
       //glm::mat4 scale1= glm::scale(glm::mat4(1.0f), glm::vec3(1.5, 1.5, 0.5));
    glm::mat4 modelViewProjMat = projMat * mViewMatrix *trans ;

    mTextureUniformHandle= glGetUniformLocation(mProgram,"u_Texture");
    glActiveTexture(GL_TEXTURE0);
    checkGlError("glActiveTexture");
    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle);
    checkGlError("glBindTexture");

    glUniformMatrix4fv(mMVPMatrixHandle, 1, false, glm::value_ptr(modelViewProjMat));
    checkGlError("glUniformMatrix4fv");
    glUniform1i(mTextureUniformHandle,0);
    checkGlError("glUniformli");

    glBindVertexArray(vao);
    glDrawArrays(GL_TRIANGLES, 0, mNumberofVertices);
    checkGlError("glDrawArrays");
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() glDrawArrays");

  //  glUseProgram(0);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "ModeDrawable::draw() glUseprogram(0)");
    checkGlError("glUseProgram");

}
#endif*/
