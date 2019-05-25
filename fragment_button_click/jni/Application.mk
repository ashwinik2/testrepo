#APP_ABI := all
APP_OPTIM := debug    # Build the target in debug mode.
#APP_ABI := armeabi-v7a x86 arm64-v8a x86_64# Define the target architecture to be ARM.
APP_ABI := armeabi-v7a # Define the target architecture to be ARM.
APP_STL :=  c++_static
APP_CPPFLAGS := -frtti -fexceptions    # This is the place you enable exception.
APP_PLATFORM := android-28 # Define the target Android version of the native application.
#APP_BUILD_SCRIPT := Android.mk
