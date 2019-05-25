#include <GLES2/gl2.h>
#include <cstring>
#include<iostream>
#include <sys/mman.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <glm/gtc/matrix_transform.hpp>
#include <android/log.h>
#include <ModeDrawable.h>
#include <DataRenderer.h>

using namespace std;
DataRenderer* DataRenderer::s_instance = nullptr;

DataRenderer* DataRenderer::Instance()
{
    if (s_instance == nullptr)
    {
        s_instance = new DataRenderer();

    }
    return s_instance;
}

DataRenderer::DataRenderer()
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "DataRenderer");

}

DataRenderer::~DataRenderer()
{

}

void DataRenderer::onDrawFrame()
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "DataRenderer ::onDrawFrame");

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

   if(mModeDrawable != nullptr)
    {
         __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "DataRenderer::onDrawFrame when mModeDrawable != nullptr call draw");
         mFrameGenerator -> GetFrame(mFrameBuffer);
         mModeDrawable->update(mFrameBuffer);
         mModeDrawable->draw(mProjectionMatrix);
    }

}

void DataRenderer::onSurfaceChanged(int width, int height)
{

    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, " DataRenderer:: onSurfaceChanged");
    mSurfaceWidth = width;
    mSurfaceHeight = height;
    DRLOGF("[width] %d", width);
    DRLOGF("[height] %d", height);

    //mProjectionMatrix = glm::ortho(0.0f, (float) mSurfaceWidth, (float) mSurfaceHeight, 0.0f, 0.1f, 10.0f);

    glViewport(0, 0, width, height);
    float ratio = (float) (width / height);
    mProjectionMatrix = glm::ortho(-1.5f, 1.5f, -1.5f, 1.5f, 0.0f, 10.0f);

    //mProjectionMatrix =glm::frustum(-1.0f, 1.0f, -1.0f, 1.0f, 3.0f, 10.0f);
   // glViewport(0, 0, width, height);
     //mProjectionMatrix = glm::ortho(-ratio, ratio, -1.0f, 1.0f, 1.0f, 100.0f);

}

void DataRenderer::onSurfaceCreated(EGLConfig config)
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "DataRenderer::onSurfaceCreated");
    glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    glDisable(GL_CULL_FACE);
    glDisable(GL_DEPTH_TEST);
    mModeDrawable = new ModeDrawable();
    mFrameGenerator = new FrameGenerator();
    mFrameBuffer = new unsigned char[mFrameSize];
}






