LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)

LOCAL_MODULE := Datarenderer

LOCAL_C_INCLUDES += $(LOCAL_PATH)/glm \
                    $(LOCAL_PATH)/include \
                    $(LOCAL_PATH)/../external
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
                        libOpenCL

LOCAL_SRC_FILES := src/jniRenderer.cpp \
                    src/DataRenderer.cpp \
                    src/ModeDrawable.cpp \
                   src/FrameGenerator.cpp
include $(BUILD_SHARED_LIBRARY)



