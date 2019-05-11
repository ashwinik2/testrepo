LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := Datarenderer
LOCAL_C_INCLUDES +=  $(LOCAL_PATH)\glm \
                $(LOCAL_PATH)\include

LOCAL_LDLIBS := -llog -lGLESv2 -lEGL -landroid
#LOCAL_EXPORT_C_INCLUDE_DIRS := $(LOCAL_PATH)\include
LOCAL_SRC_FILES := src\jniRenderer.cpp \
                    src\DataRenderer.cpp \
                    src\ModeDrawable.cpp
   #                 src\FrameGenerator.cpp
include $(BUILD_SHARED_LIBRARY)

#C:\Users\prasanna\testrepo\testrepo\opengl_project\fragment_button_click\jni\src
 #C:\Users\prasanna\testrepo\testrepo\opengl_project\fragment_button_click\jni\Android.mk
