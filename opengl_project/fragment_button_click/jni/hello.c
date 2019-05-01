#include<jni.h>
#include<string.h>

jstring Java_com_example_fragment_button_click_activities_MainActivity_helloworld(JNIEnv* env, jobject obj)
{
    return (*env)->NewStringUTF(env,"Hello");
}