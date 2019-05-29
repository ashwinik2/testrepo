#include <FrameGenerator.h>
#include <stdlib.h>
#include <vector>
#include <android/log.h>
#include <CL/cl.h>
#include<string.h>
#include<iostream>
#include <sstream>
using namespace std;
std::string clOperation;
FrameGenerator::FrameGenerator()
{
    //mFrameBuffer = new unsigned char[mFrameSize];

    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator()");
}

FrameGenerator::~FrameGenerator()
{

    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "~FrameGenerator()");
}
void FrameGenerator::checkClError(/*std::string clOperation,*/int err)
{
     __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "checkClError : NoError()");


    switch(err) {

        case -32:
            MyLOGE("CL_INVALID_PLATFORM: CLenum no platform selected");
            break;

        case -30:
            MyLOGE("CL_INVALID_VALUE: numeric argument out of range");
            break;


        case -1:
	    MyLOGE( "CL_DEVICE_NOT_FOUND: not enough memory left to execute command");
            break;

        case -2:
	    MyLOGE( "CL_DEVICE_NOT_AVAILABLE: not enough memory left to execute command");
            break;


        case -6:
            MyLOGE( "CL_OUT_OFHOST__MEMORY: not enough memory left to execute command");
            break;

        case -31:
            MyLOGE( "CL_INVALID_DEVICE_TYPE_: not enough memory left to execute command");
            break;

        default:
            MyLOGE("unlisted error");
            break;
    }
}

void FrameGenerator::GetFrame(unsigned char* mFrameBuffer,int &cl_device_ready)
{
    __android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::GetFrame()");
     for (i = 0; i < (cols *rows); i++)
     {
        mRed = rand() % 255;
        mGreen = rand() % 255;
        mBlue = rand() % 255;
        mGrayValue = ((mRed * 0.30) + (mGreen * 0.59) + (mBlue * 0.11))/3;
        mRed = mGrayValue;
        mGreen = mGrayValue;
        mBlue = mGrayValue;

        mFrameBuffer[(i*4)+0] = mRed;
        mFrameBuffer[(i*4)+1] = mGreen;
        mFrameBuffer[(i*4)+2] = mBlue;
        mFrameBuffer[(i*4)+3] = 255;
    }
    //return mFrameBuffer;
//#if 0
    const char *KernelSource = "\n" \
    "__kernel void square(                                                       \n" \
    "   __global unsigned char* input,                                              \n" \
    "   __global unsigned char* output,                                             \n" \
    "   const int count)                                           \n" \
    "{                                                                      \n" \
    "   int i = get_global_id(0);                                           \n" \
    "   if(i < count)                                                       \n" \
    "       output[i] = input[i] * input[i];                                \n" \
    "}                                                                      \n" \
    "\n";
       int err;                            // error code returned from api calls
        int DATA_SIZE =100;
        float data[DATA_SIZE];              // original data set given to device
        unsigned char results[DATA_SIZE];           // results returned from device
        unsigned int correct;               // number of correct results returned

        size_t global;                      // global domain size for our calculation
        size_t local;                       // local domain size for our calculation
	cl_platform_id platform;
        cl_device_id device_id;             // compute device id
        cl_context context;                 // compute context
        cl_command_queue commands;          // compute command queue
        cl_program program;                 // compute program
        cl_kernel kernel;                   // compute kernel

        cl_mem input;                       // device memory used for the input array
        cl_mem output;                      // device memory used for the output array

        // Fill our data set with random float values
        //
        int i = 0;
       /* unsigned int count = DATA_SIZE;
        for(i = 0; i < count; i++)
            data[i] = rand() / (float)RAND_MAX;*/

        // Connect to a compute device
        //
	err = clGetPlatformIDs(1, &platform, NULL);
   	if(err < 0) 
	{
      		perror("Couldn't identify a platform");
		DRLOGF("clGetPlatformIDs err is %d", err);
      		exit(1);
   	}

        err = clGetDeviceIDs(platform, CL_DEVICE_TYPE_GPU, 1, &device_id, NULL);
        if (err != CL_SUCCESS)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clGetDeviceIDs()");
            printf("Error: Failed to create a device group!\n");
           // return EXIT_FAILURE;

	DRLOGF("clDeviceID err is %d", err);
        checkClError(/*clGetDeviceID,*/err);
     	exit(1);
        }


        // Create a compute context
        //
        context = clCreateContext(0, 1, &device_id, NULL, NULL, &err);
        if (!context)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clCreateContext()");
            printf("Error: Failed to create a compute context!\n");
           // return EXIT_FAILURE;

            exit(1);
        }

/*        context = clCreateContextFromType(0, CL_DEVICE_TYPE_GPU,  NULL, NULL, NULL);
        if (!context)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clCreateContext()");
            printf("Error: Failed to create a compute context!\n");
           // return EXIT_FAILURE;
        checkClError("clcreateContextFromType");	
            exit(1);
        }
	size_t szParamDataBytes;
	clGetContextInfo(context,CL_CONTEXT_DEVICES, 0, NULL ,&szParamDataBytes);
	device_id =(cl_device_id*)malloc(szParamDataBytes);
	clGetContextInfo(context,CL_CONTEXT_DEVICES,szParamDataBytes,device_id,NULL);*/


        // Create a command commands
        //
        commands = clCreateCommandQueue(context, device_id, 0, NULL);
        if (!commands)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clCreateCommandQueue()");
            printf("Error: Failed to create a command commands!\n");
//            return EXIT_FAILURE;

            exit(1);
        }

        // Create the compute program from the source buffer
        //
        program = clCreateProgramWithSource(context, 1, (const char **) & KernelSource, NULL, &err);
        if (!program)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clCreateProgramWithSource()");
            printf("Error: Failed to create compute program!\n");
//            return EXIT_FAILURE;

            exit(1);
        }

        // Build the program executable
        //
        err = clBuildProgram(program, 0, NULL, NULL, NULL, NULL);
        if (err != CL_SUCCESS)
        {
            size_t len;
            char buffer[2048];

            printf("Error: Failed to build program executable!\n");
         //   clGetProgramBuildInfo(program, device_id, CL_PROGRAM_BUILD_LOG, sizeof(buffer), buffer, &len);
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clGetProgramBuildInfo()");
            printf("%s\n", buffer);
            exit(1);
        }

        // Create the compute kernel in the program we wish to run
        //
        kernel = clCreateKernel(program, "square", &err);
        if (!kernel || err != CL_SUCCESS)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clCreateKernel()");
            printf("Error: Failed to create compute kernel!\n");
            exit(1);
        }

        // Create the input and output arrays in device memory for our calculation
        //
        input = clCreateBuffer(context,  CL_MEM_READ_ONLY,  sizeof(unsigned char) * (mFrameSize), NULL, NULL);
        output = clCreateBuffer(context, CL_MEM_WRITE_ONLY, sizeof(unsigned char) * (mFrameSize), NULL, NULL);
        if (!input || !output)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clCreateBuffer()");
            printf("Error: Failed to allocate device memory!\n");
            exit(1);
        }

        // Write our data set into the input array in device memory
        //
        err = clEnqueueWriteBuffer(commands, input, CL_TRUE, 0, sizeof(unsigned char) * (mFrameSize), mFrameBuffer, 0, NULL, NULL);
        if (err != CL_SUCCESS)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clEnqueueWriteBuffer()");
            printf("Error: Failed to write to source array!\n");
            exit(1);
        }

        // Set the arguments to our compute kernel
        //
        err = 0;
        err  = clSetKernelArg(kernel, 0, sizeof(cl_mem), &input);
        err |= clSetKernelArg(kernel, 1, sizeof(cl_mem), &output);
        err |= clSetKernelArg(kernel, 2, sizeof(cl_int), &mFrameSize);
        if (err != CL_SUCCESS)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clSetKernelArg()");
            printf("Error: Failed to set kernel arguments! %d\n", err);
            exit(1);
        }

        // Get the maximum work group size for executing the kernel on the device
        //
       /* err = clGetKernelWorkGroupInfo(kernel, device_id, CL_KERNEL_WORK_GROUP_SIZE, sizeof(local), &local, NULL);
        if (err != CL_SUCCESS)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clGetKernelWorkGroupInfo()");
            printf("Error: Failed to retrieve kernel work group info! %d\n", err);
            exit(1);
        }*/

        // Execute the kernel over the entire range of our 1d input data set
        // using the maximum number of work group items for this device
        //
        global = mFrameSize;
	local = 64;
        err = clEnqueueNDRangeKernel(commands, kernel, 1, NULL, &global, &local, 0, NULL, NULL);
        if (err)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clEnqueueNDRangeKernel()");
            printf("Error: Failed to execute kernel!\n");
           // return EXIT_FAILURE;
        }

        // Wait for the command commands to get serviced before reading back results
        //
        clFinish(commands);

        // Read back the results from the device to verify the output
        //
        err = clEnqueueReadBuffer( commands, output, CL_TRUE, 0, sizeof(unsigned char) * mFrameSize, mFrameBuffer, 0, NULL, NULL );
        if (err != CL_SUCCESS)
        {
		__android_log_print(ANDROID_LOG_INFO,  __FUNCTION__, "FrameGenerator::clEnqueueReadBuffer()");
            printf("Error: Failed to read output array! %d\n", err);
            exit(1);
        }

 
        clReleaseMemObject(input);
        clReleaseMemObject(output);
        clReleaseProgram(program);
        clReleaseKernel(kernel);
        clReleaseCommandQueue(commands);
        clReleaseContext(context);
	cl_device_ready =1;
//	mSimpleOpenCV = new SimpleOpenCV();
//mSimpleOpenCV->CreateMat();
//#endif
		
	cl_device_ready =1;
}

