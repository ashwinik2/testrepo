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
    void ProcessFrame( unsigned char *mFrameBuffer,int &cl_device_ready,const int &mFrameSize,int rows,int cols);

private:
	SimpleOpenCV*  mSimpleOpenCV = nullptr;
	int mRows =0;
	int mCols =0;

};

#endif //FRAGMENT_BUTTON_CLICK_FRAMEOPENCL_H

