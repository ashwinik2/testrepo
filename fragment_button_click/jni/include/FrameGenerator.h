#ifndef FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H
#define FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H

#include <vector>
#include <GlErrorLogger.h>
#include<FrameOpenCL.h>
#include<SimpleOpenCV.h>
#include <CL/cl.h>
class FrameGenerator
{
public:
    FrameGenerator ();
    ~FrameGenerator ();
    void GetFrame( unsigned char *mFrameBuffer,int &cl_device_ready,const int FrameSize,int rows,int cols);
    void checkClError(/*std::string clOperation,*/int err);

private:
    int mCols ;
    int mRows ;
    int i=0,j =0;
    unsigned char mRed = 0;
    unsigned char mGreen = 0;
    unsigned char mBlue = 0;
    unsigned char mGrayValue = 0;
   // unsigned char *mFrameBuffer;
    int mFrameSize = 0;
    FrameOpenCL* mFrameOpenCL = nullptr;
   // SimpleOpenCV*  mSimpleOpenCV = nullptr;

};

#endif //FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H
