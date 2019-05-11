#
# File: Android.mk
#

LOCAL_PATH := $(call my-dir)

# Neo Scanui Package Build
include $(CLEAR_VARS)


#LOCAL_SDK_VERSION := current
LOCAL_MODULE_TAGS := optional eng user user-debug

LOCAL_SRC_FILES := \
    $(call all-java-files-under, app\src\main\java) \

LOCAL_CERTIFICATE := platform
LOCAL_AAPT_FLAGS := \
                  --auto-add-overlay \
                  --extra-packages android.support.v7.appcompat \
                  --extra-packages android.support.v7.recyclerview \
                  --extra-packages android.support.coordinatorlayout \
                  --extra-packages android.support.constraint \
                  --extra-packages android.support.transition \


LOCAL_JAVACFLAGS :=-Xlint:all -Xlint:-path

# Adding Assets directory
LOCAL_ASSET_FILES += $(call find-subdir-assets)

# Resource Directories
LOCAL_RESOURCE_DIR :=   $(LOCAL_PATH)\app\src\main\res \

LOCAL_RESOURCE_DIR += \
                        prebuilts/sdk/current/support/v7/appcompat/res \
                        prebuilts/sdk/current/support/v7/recyclerview/res \
                        prebuilts/sdk/current/support/transition/res \
                        prebuilts/sdk/current/support/coordinatorlayout/res

LOCAL_STATIC_JAVA_LIBRARIES := \
                               android-support-v4 \
                               android-support-constraint-layout \
                               android-support-v7-appcompat \
                               android-support-transition \
                               android-support-v7-recyclerview \
                               constraint-layout \
                               constraint-layout-solver \
                               android-support-constraint-layout \
                               support-design-sources \

LOCAL_STATIC_JAVA_AAR_LIBRARIES := constraint-layout \
#                                   support-design \

#Android Manifest
LOCAL_FULL_MANIFEST_FILE := $(LOCAL_PATH)\app\src\main\AndroidManifest.xml
LOCAL_PACKAGE_NAME := OverlayApp
#LOCAL_MODULE := OverlayApp
include $(BUILD_PACKAGE)
#include $(BUILD_SHARED_LIBRARY)

# The below line needs to be uncommented while performing Instrumentation test
# to generate SettingsUITestRunner.apk
#include $(call all-makefiles-under,$(LOCAL_PATH))

#######################################

LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
# Re-map native code path
LOCAL_SRC_FILES := $(call all-c-files-under, jni)

LOCAL_MODULE := mypackage_jni

include $(BUILD_SHARED_LIBRARY)
#ndk-build NDK_PROJECT_PATH=. APP_PLATFORM=android-18  APP_BUILD_SCRIPT=c:\Users\prasanna\testrepo\testrepo\opengl_project\fragment_button_click\Android.mk

