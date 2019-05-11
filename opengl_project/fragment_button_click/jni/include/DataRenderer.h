//
// Created by prasanna on 5/5/2019.
//

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
#include <include\ModeDrawable.h>
#include <include\FrameGenerator.h>
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
    int cols = 10;
    int rows = 10;
    const int mFrameSize = cols*rows*3;
    glm::mat4 mProjectionMatrix ;
    unsigned char mFrameBuffer[300];



};


#endif //FRAGMENT_BUTTON_CLICK_DATARENDERER_H
