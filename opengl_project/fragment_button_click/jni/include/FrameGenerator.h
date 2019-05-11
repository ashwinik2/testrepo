#ifndef FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H
#define FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H

#include <vector>
class FrameGenerator
{
public:
    int cols = 10;
    int rows = 10;
    int Start_Generating_Frame =0;
    const int mFrameSize = cols*rows*3;
    int i=0,j =0;
    int mRed = 0;
    int mGreen = 0;
    int mBlue = 0;
    int mGrayValue = 0;
    //std::vector<uint8_t> mFrameVectArray;
    unsigned char mFrameBuffer[300];

    FrameGenerator ();
    ~FrameGenerator ();

    unsigned char* SendFrame() ;


private:

};

#endif //FRAGMENT_BUTTON_CLICK_FRAMEGENERATOR_H
