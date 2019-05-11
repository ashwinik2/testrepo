#ifndef GLESNATIVEANDROID_My_LOGGER_H
#define GLESNATIVEANDROID_My_LOGGER_H

#include <android/log.h>

#define LOG_TAG "ModeDrawable"
#define  MyLOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define  MyLOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define  MyLOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG,__VA_ARGS__)
#define  MyLOGI(...)  __android_log_print(ANDROID_LOG_INFO   , LOG_TAG,__VA_ARGS__)
#define  MyLOGW(...)  __android_log_print(ANDROID_LOG_WARN   , LOG_TAG,__VA_ARGS__)
#define  MyLOGF(...)  __android_log_print(ANDROID_LOG_FATAL   , LOG_TAG,__VA_ARGS__)
#define  MyLOGSIMPLE(...)
#define LOG_TAG1 "GLES C++"
#define ALOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG1, __VA_ARGS__)
#define ALOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG1, __VA_ARGS__)
#define LOG_TAG2 "Jni"
#define  JniLOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG2, __VA_ARGS__)
#define  JniLOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG2, __VA_ARGS__)
#define  JniLOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG2,__VA_ARGS__)
#define  JniLOGI(...)  __android_log_print(ANDROID_LOG_INFO   , LOG_TAG2,__VA_ARGS__)
#define  JniLOGW(...)  __android_log_print(ANDROID_LOG_WARN   , LOG_TAG2,__VA_ARGS__)
#define  JniLOGF(...)  __android_log_print(ANDROID_LOG_FATAL   , LOG_TAG2,__VA_ARGS__)
#define LOG_TAG3 "DataRenderer"
#define  DRLOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG3, __VA_ARGS__)
#define  DRLOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG3, __VA_ARGS__)
#define  DRLOGV(...)  __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG3,__VA_ARGS__)
#define  DRLOGI(...)  __android_log_print(ANDROID_LOG_INFO   , LOG_TAG3,__VA_ARGS__)
#define  DRLOGW(...)  __android_log_print(ANDROID_LOG_WARN   , LOG_TAG3,__VA_ARGS__)
#define  DRLOGF(...)  __android_log_print(ANDROID_LOG_FATAL   , LOG_TAG3,__VA_ARGS__)

#endif //GLESNATIVEANDROID_My_LOGGER_H

