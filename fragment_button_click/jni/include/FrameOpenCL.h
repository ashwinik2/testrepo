#ifndef FRAGMENT_BUTTON_CLICK_FRAMEOPENCL_H
#define FRAGMENT_BUTTON_CLICK_FRAMEOPENCL_H

#include <vector>
#include <GlErrorLogger.h>
#include<SimpleOpenCV.h>
#include <CL/cl.h>
class FrameOpenCL
{
public:
    FrameOpenCL ();
    ~FrameOpenCL ();
    void ProcessFrame( unsigned char *mFrameBuffer,int &cl_device_ready,const int &mFrameSize);

private:
	SimpleOpenCV*  mSimpleOpenCV = nullptr;

};

#endif //FRAGMENT_BUTTON_CLICK_FRAMEOPENCL_H

