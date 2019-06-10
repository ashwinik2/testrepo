
#include<opencv2/imgcodecs.hpp>
#include<opencv2/highgui.hpp>
#include<opencv2/core.hpp>
#include<SimpleOpenCV.h>
#include<iostream>
#include<unistd.h>
#include <android/log.h>
using namespace cv;
SimpleOpenCV::SimpleOpenCV()
{
	__android_log_print(ANDROID_LOG_INFO, __FUNCTION__,"simpleOpenCV()");
}

SimpleOpenCV::~SimpleOpenCV()
{

	__android_log_print(ANDROID_LOG_INFO, __FUNCTION__,"~simpleOpenCV()");
}
void SimpleOpenCV ::CreateMat(unsigned char*mFrameBuffer,int rows, int cols,const int FrameSize)
{
	mRows = rows;
	mCols = cols;
	mFrameSize = FrameSize;
	DRLOGF("[mFrameSize in simpleOpenCV %d", mFrameSize);
	

	sleep(5);
	__android_log_print(ANDROID_LOG_INFO, __FUNCTION__,"simpleOpenCV()::CreateMat()");
//	Mat image = imread("/home/megan/work/8096_Open-Q_820_Android_BSP-P_v5.0/Source_Package/APQ8096_LA.UM.7.5.r1-03100-8x96.0_P_v5.0/device/qcom/msm8996/apps/fragment_button_click/jni/src/cat.jpeg",IMREAD_COLOR);
	Mat srcimg = imread("/system/lib64/cat.jpeg",IMREAD_COLOR);
	if( srcimg.empty() )                      // Check for invalid input

	{
		__android_log_print(ANDROID_LOG_INFO, __FUNCTION__,"SimpleOpenCV()::CreateMat() image empty");
        	
   	}	
#if 0
//	cvtColor(image,imageRGBA,COLOR_BGR2RGB);
	unsigned char *mLocalBuffer = new unsigned char[mFrameSize];
	memcpy( (void*)mLocalBuffer , (void*)mFrameBuffer, mFrameSize );
    	Mat dstimg(mRows,mCols, CV_8UC4, Scalar(0,0,0,0));
	Mat srcimg(mRows,mCols, CV_8UC4, mLocalBuffer);
#endif	

	/*Gaussian Filter */
#if 0
	int size = dstimg.total() * dstimg.elemSize();
	Mat dstimage(srcimg.rows,srcimg.cols,CV_8UC3,Scalar(0,0,0));
	GaussianBlur(srcimg, dstimage, Size(3, 3), 0);
	memcpy(mFrameBuffer,dstimage.data,size* sizeof(unsigned char));
#endif
	/*Laplacian Edge Detection */
#if 0
	Mat dstimg(srcimg.rows,srcimg.cols,CV_8UC3,Scalar(0,0,0));
	int size = dstimg.total() * dstimg.elemSize();
	float kernel[9] = {-1,-1,-1, -1,8,-1, -1,-1,-1};
	Mat Kernel(3,3,CV_32F,kernel);
	filter2D(srcimg, dstimg, -1, Kernel, Point(-1,-1), 0.0, BORDER_REPLICATE);
	memcpy(mFrameBuffer,dstimg.data,size* sizeof(unsigned char));
#endif
	/*Homogeneous Blur */
#if 0
	Mat dstimg(srcimg.rows,srcimg.cols,CV_8UC3,Scalar(0,0,0));
	int size = dstimg.total() * dstimg.elemSize();
	float kernel[9] = {1/9,1/9,1/9, 1/9,1/9,1/9,1/9, 1/9,1/9};
	Mat Kernel(3,3,CV_32F,kernel);
	filter2D(srcimg, dstimg, -1, Kernel, Point(-1,-1), 0.0, BORDER_REPLICATE);
	memcpy(mFrameBuffer,dstimg.data,size* sizeof(unsigned char));
#endif
	/*sharpen the image */
#if 0
	Mat dstimg(srcimg.rows,srcimg.cols,CV_8UC3,Scalar(0,0,0));
	int size = dstimg.total() * dstimg.elemSize();
	float kernel[9] = {0,-1,0,-1,5,-1,0,-1,0};
	Mat Kernel(3,3,CV_32F,kernel);
	filter2D(srcimg, dstimg, -1, Kernel, Point(-1,-1), 0.0, BORDER_REPLICATE);
	memcpy(mFrameBuffer,dstimg.data,size* sizeof(unsigned char));
#endif
	/*Sobel Edge Detection */
//#if 0
        Mat dstimg(srcimg.rows,srcimg.cols,CV_8UC3,Scalar(0,0,0));
        int size = dstimg.total() * dstimg.elemSize();
        float kernel[9] = {-1,-2,0, 0,0,-1, -1,-2,-1};
        Mat Kernel(3,3,CV_32F,kernel);
        filter2D(srcimg, dstimg, -1, Kernel, Point(-1,-1), 0.0, BORDER_REPLICATE);
        memcpy(mFrameBuffer,dstimg.data,size* sizeof(unsigned char));
//#endif

//delete[]mLocalBuffer;
}
