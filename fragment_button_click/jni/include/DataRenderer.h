
#ifndef FRAGMENT_BUTTON_CLICK_DATARENDERER_H
#define FRAGMENT_BUTTON_CLICK_DATARENDERER_H
#include <vector>
#include<string>
#include<iostream>
#include <EGL/egl.h>
#include <GLES2/gl2.h>
#include <glm/vec2.hpp>
#include <glm/vec3.hpp>
#include <glm/mat4x4.hpp>
#include <ModeDrawable.h>
#include <FrameGenerator.h>
class DataRenderer
{
public:
    static DataRenderer* Instance();

    DataRenderer();
    ~DataRenderer();
    void onSurfaceChanged(int width, int height);
    void onDrawFrame();
    void onSurfaceCreated(EGLConfig config);

private:
    static DataRenderer*  s_instance;
    ModeDrawable* mModeDrawable = nullptr;
    FrameGenerator* mFrameGenerator = nullptr;
    int mSurfaceWidth=0;
    int mSurfaceHeight = 0;
    glm::mat4 mProjectionMatrix ;

    int cols = 10;
    int rows = 10;
    int Start_Generating_Frame =0;
    const int mFrameSize = cols*rows*4;
    int i=0,j =0;
    int mRed = 0;
    int mGreen = 0;
    int mBlue = 0;
    int mGrayValue = 0;
    unsigned char *mFrameBuffer;
    int cl_device_ready = 0;	


};


#endif //FRAGMENT_BUTTON_CLICK_DATARENDERER_H
