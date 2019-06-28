#include <unistd.h>
#include <cstring>
#include <jni.h>
#include <cstdio>
#include <string>
#include <glm/gtc/matrix_transform.hpp>
#include <EGL/egl.h>
#include <DataRenderer.h>
#include <GlErrorLogger.h>
#include <android/log.h>
#include <Common.h>
int mCVFilterType; 
//DataRenderer1 *mDataRenderer;
extern "C"
JNIEXPORT void JNICALL
Java_com_example_fragment_1button_1click_opengl_mGLRenderer_initGL(JNIEnv* env, jclass type)
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "initGL");
    /*EGLConfig config = nullptr;
    if(mDataRenderer == nullptr)
    {
        __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "initGL....");
        mDataRenderer = new DataRenderer();
        mDataRenderer->onSurfaceCreated(config);
    }*/

    EGLConfig config = nullptr;
    DataRenderer::Instance()->onSurfaceCreated(config);

}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_fragment_1button_1click_opengl_mGLRenderer_resizeGL(JNIEnv* env, jclass type, jint width, jint height)
{
    DataRenderer::Instance()->onSurfaceChanged((int)width, (int)height);
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "resizeGL");
    JniLOGI("[width] %d", width);
    JniLOGI("[height] %d", height);
    /*if(mDataRenderer != nullptr)
    {
        __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "mDataRenderer != nullptr in resizeGL");
        mDataRenderer->onSurfaceChanged((int)width, (int)height);
    }*/

}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_fragment_1button_1click_opengl_mGLRenderer_drawGL(JNIEnv* env, jclass type,int CVFilterType)
{
    //LOG_PRINT("Native DrawGL");
    mCVFilterType = CVFilterType;
    JniLOGI("[ mCVFilterType is ] %d", mCVFilterType);
    DataRenderer::Instance()->onDrawFrame();
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "drawGL");
    /*if(mDataRenderer != nullptr)
    {
        __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "mDataRenderer != nullptr in drawGL");
        mDataRenderer ->onDrawFrame();
    }*/
}
