LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)

LOCAL_MODULE := libDatarenderer

LOCAL_C_INCLUDES += $(LOCAL_PATH)/glm \
			$(LOCAL_PATH)/include \
			$(LOCAL_PATH)/../external \
			$(LOCAL_PATH)/../external/opencv3/include/opencv \
			$(LOCAL_PATH)/../external/opencv3/modules/core/include \
			$(LOCAL_PATH)/../external/opencv3/modules/hal/include \
			$(LOCAL_PATH)/../external/opencv3/modules/imgproc/include \
			$(LOCAL_PATH)/../external/opencv3/modules/photo/include \
			$(LOCAL_PATH)/../external/opencv3/modules/video/include \
			$(LOCAL_PATH)/../external/opencv3/modules/objdetect/include \
			$(LOCAL_PATH)/../external/opencv3/modules/imgcodecs/include \
			$(LOCAL_PATH)/../external/opencv3/3rdparty/libjpeg \
			$(LOCAL_PATH)/../external/opencv3/modules/highgui/include \
			$(LOCAL_PATH)/../external/opencv3/modules/videoio/include 
LOCAL_CPPFLAGS += \
                -std=c++14 \
                -fPIC \
                -frtti \
                -fexceptions \
                -Werror \
                -Wno-unused-parameter \
                -DOGL_VERBOSE

LOCAL_STATIC_LIBRARIES := \
		opencv_libjpeg

LOCAL_SHARED_LIBRARIES := \
                        liblog \
                        libGLESv2 \
                        libandroid \
                        libEGL \
                        libOpenCL \
                        libopencv_core \
			libopencv_imgproc \
			libopencv_imgcodecs \
			libopencv_highgui

LOCAL_SRC_FILES := src/jniRenderer.cpp \
                    src/DataRenderer.cpp \
                    src/ModeDrawable.cpp \
                    src/FrameGenerator.cpp \
		    src/FrameOpenCL.cpp \
                    src/SimpleOpenCV.cpp
include $(BUILD_SHARED_LIBRARY)



