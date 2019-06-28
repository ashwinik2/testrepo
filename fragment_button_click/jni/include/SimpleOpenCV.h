

#ifndef FRAGMENT_BUTTON_CLICK_SIMPLEOPENCV_H

#define FRAGMENT_BUTTON_CLICK_SIMPLEOPENCV_H
#include <GlErrorLogger.h>
#include<opencv2/core.hpp>
#include <opencv2/imgproc.hpp>
#include<opencv2/imgcodecs.hpp>
#include<opencv2/highgui.hpp>

class SimpleOpenCV
{
public:
    SimpleOpenCV();
    ~SimpleOpenCV();

    void CreateMat(unsigned char*mFrameBuffer,int rows, int cols,const int FrameSize);
private:
    int mCols = 0;
    int mRows = 0;
    int mFrameSize = 0;
    enum FilterType {BLUR = 1,
	    	    SHARP = 2,
		    EDGE =3};

};


#endif //FRAGMENT_BUTTON_CLICK_SIMPLEOPENCV_H
