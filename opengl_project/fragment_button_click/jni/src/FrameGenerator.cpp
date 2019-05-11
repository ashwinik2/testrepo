#include "C:\Users\prasanna\testrepo\testrepo\opengl_project\fragment_button_click\jni\include\FrameGenerator.h"
#include <stdlib.h>
#include <vector>

FrameGenerator::FrameGenerator()
{
    Start_Generating_Frame = 1;
}

FrameGenerator::~FrameGenerator()
{

}

unsigned char* FrameGenerator::SendFrame()
{
     for (i = 0; i < (cols *rows); i++)
     {
        mRed = rand() % 255;
        mGreen = rand() % 255;
        mBlue = rand() % 255;
        mGrayValue = (mRed + mGreen + mBlue)/3;
        mRed = mGrayValue;
        mGreen = mGrayValue;
        mBlue = mGrayValue;

        mFrameBuffer[i] = mRed;
        mFrameBuffer[i] = mGreen;
        mFrameBuffer[i] = mBlue;
    }
    return mFrameBuffer;

}

