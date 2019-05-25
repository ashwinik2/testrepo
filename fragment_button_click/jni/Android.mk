LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)

LOCAL_MODULE := libDatarenderer

LOCAL_C_INCLUDES += $(LOCAL_PATH)/glm \
			$(LOCAL_PATH)/include \
			$(LOCAL_PATH)/../external \
			external/opencv3/include/opencv \
			external/opencv3/modules/core/include \
			external/opencv3/modules/hal/include \
			external/opencv3/modules/imgproc/include \
			external/opencv3/modules/photo/include \
			external/opencv3/modules/video/include \
			external/opencv3/modules/objdetect/include
LOCAL_CPPFLAGS += \
                -std=c++14 \
                -fPIC \
                -frtti \
                -fexceptions \
                -Werror \
                -Wno-unused-parameter \
                -DOGL_VERBOSE

LOCAL_SHARED_LIBRARIES := \
                        liblog \
                        libGLESv2 \
                        libandroid \
                        libEGL \
                        libOpenCL \
                        libopencv_core

LOCAL_SRC_FILES := src/jniRenderer.cpp \
                    src/DataRenderer.cpp \
                    src/ModeDrawable.cpp \
                    src/FrameGenerator.cpp \
#                    src/SimpleOpenCV.cpp
include $(BUILD_SHARED_LIBRARY)



