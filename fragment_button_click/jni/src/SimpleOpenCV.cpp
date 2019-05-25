
#include<opencv2/core.hpp>
#include<SimpleOpenCV.h>	
#include<iostream>
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
void SimpleOpenCV ::CreateMat()
{

	__android_log_print(ANDROID_LOG_INFO, __FUNCTION__,"simpleOpenCV()::CreateMat()");
    	Mat img(1,0, CV_8UC1, Scalar(126,0,255));
	
}
