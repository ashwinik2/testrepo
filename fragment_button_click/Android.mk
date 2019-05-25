#
# File: Android.mk
#

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := constraint-layout-solver:../../../../../prebuilts/tools/linux-x86/offline-sdk/solver/constraint-layout-solver-1.0.2.jar
include $(BUILD_MULTI_PREBUILT)

include $(CLEAR_VARS)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := constraint-layout:../../../../../prebuilts/tools/linux-x86/offline-sdk/constraintlayout/constraint-layout-1.0.2.aar
include $(BUILD_MULTI_PREBUILT)

# app (.apk)
include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := Fragment_Button_Click
#LOCAL_MODULE_TAGS := optional eng user user-debug

#LOCAL_SDK_VERSION := current
LOCAL_PRIVATE_PLATFORM_APIS := true
LOCAL_SRC_FILES := \
    $(call all-java-files-under, app/src/main/java)

LOCAL_CERTIFICATE := platform
LOCAL_AAPT_FLAGS := \
                  --auto-add-overlay \
                  --extra-packages android.support.v7.appcompat \
                  --extra-packages android.support.v7.recyclerview \
                  --extra-packages android.support.coordinatorlayout \
                  --extra-packages android.support.constraint \
                  --extra-packages android.support.transition

LOCAL_PROGUARD_FLAG_FILES := app/proguard-rules.pro
LOCAL_PROGUARD_FLAG_FILES += ../../../../../frameworks/support/coordinatorlayout/proguard-rules.pro
LOCAL_PROGUARD_FLAG_FILES += ../../../../../frameworks/support/v7/recyclerview/proguard-rules.pro

LOCAL_JAVACFLAGS :=-Xlint:all -Xlint:-path

# Adding Assets directory
LOCAL_ASSET_FILES += $(call find-subdir-assets)

# Resource Directories
LOCAL_RESOURCE_DIR :=   $(LOCAL_PATH)/app/src/main/res

LOCAL_RESOURCE_DIR += \
                        prebuilts/sdk/current/support/v7/appcompat/res \
                        prebuilts/sdk/current/support/v7/recyclerview/res \
                        prebuilts/sdk/current/support/transition/res \
                        prebuilts/sdk/current/support/coordinatorlayout/res

LOCAL_STATIC_JAVA_LIBRARIES := \
                               android-support-v4 \
                               android-support-v7-appcompat \
                               android-support-transition \
                               android-support-v7-recyclerview \
                               constraint-layout-solver \
                               

LOCAL_STATIC_JAVA_AAR_LIBRARIES := constraint-layout
#                                   support-design

#LOCAL_JAVA_LIBRARIES := lib
#Android Manifest
LOCAL_FULL_MANIFEST_FILE := $(LOCAL_PATH)/app/src/main/AndroidManifest.xml
include $(BUILD_PACKAGE)

include $(call all-makefiles-under,$(LOCAL_PATH))

