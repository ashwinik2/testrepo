#ifndef FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H
#define FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H

#include <vector>
#include <GlErrorLogger.h>
#include<SimpleOpenCV.h>
#include <CL/cl.h>
class FrameGenerator
{
public:
    FrameGenerator ();
    ~FrameGenerator ();
    void GetFrame( unsigned char *mFrameBuffer,int &cl_device_ready);
    void checkClError(/*std::string clOperation,*/int err);

private:
    int cols = 10;
    int rows = 10;
    int i=0,j =0;
    unsigned char mRed = 0;
    unsigned char mGreen = 0;
    unsigned char mBlue = 0;
    unsigned char mGrayValue = 0;
   // unsigned char *mFrameBuffer;
    const int mFrameSize = cols*rows*4;
SimpleOpenCV*  mSimpleOpenCV = nullptr;

};

#endif //FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H
