#make the device read/write
adb root
sleep 1;

adb remount

#turn on the screen
#adb shell input keyevent 26

#unlock the screen
#adb shell input keyevent 82

adb shell settings put system screen_off_timeout 1600000

#push all the dependent libraries
adb push ~/work/8096_Open-Q_820_Android_BSP-P_v5.0/Source_Package/APQ8096_LA.UM.7.5.r1-03100-8x96.0_P_v5.0/out/target/product/msm8996/system/app/Fragment_Button_Click/Fragment_Button_Click.apk /system/app/Fragment_Button_Click/Fragment_Button_Click.apk

adb push ~/work/8096_Open-Q_820_Android_BSP-P_v5.0/Source_Package/APQ8096_LA.UM.7.5.r1-03100-8x96.0_P_v5.0/out/target/product/msm8996/obj/SHARED_LIBRARIES/libDatarenderer_intermediates/libDatarenderer.so /system/lib64/

#start he activity/ launch the app
#adb shell am force-stop com.example.fragment_button_click/.activities.MainActivity
adb shell ps | grep fragment_button_click | awk '{print $2}' | xargs adb shell kill

adb shell am start -n com.example.fragment_button_click/.activities.MainActivity
