

#ifndef FRAGMENT_BUTTON_CLICK_MODEDRAWABLE_H
#define FRAGMENT_BUTTON_CLICK_MODEDRAWABLE_H

#include <vector>
#include<string>
#include <EGL/egl.h>
#include <GLES2/gl2.h>
#include <glm/vec2.hpp>
#include <glm/vec3.hpp>
#include <glm/mat4x4.hpp>
#include <GlErrorLogger.h>
class ModeDrawable
{
public:
    ModeDrawable(int rows, int cols,const int FrameSize);
    ~ModeDrawable();

    void checkGlError(std::string string);
    void load();
    void updateViewMatrix();
    void update(unsigned char* data/*, size_t dataLen*/);
    void draw(const glm::mat4& projMat);
    void checkShaderCompileError(int shader,GLenum shaderType);
    int loadShader(int type, const char* src);
private:
    std::string glesVersionInfo;
   int mShader=0;
    int mCols = 0;
    int mRows = 0;
    int mFrameSize = 0 ;
    GLuint mProgram = 0;
    GLint mMVPMatrixHandle = -1;
    GLint mTextureUniformHandle = -1;
    GLuint mTextureDataHandle = -1;
    GLint mVertexPosHandle = -1;
    GLint mTexturePosHandle = -1;
    GLint mColorValHandle = -1;

    int mNumberofVertices = 6;
    int mPositionHandle = 0;
    int COORDS_PER_VERTEX = 3;
    int TextCOORDS_PER_VERTEX = 2;
    int vertexStride = COORDS_PER_VERTEX * 4;
    int textureStride = TextCOORDS_PER_VERTEX * 4;
    unsigned char mFrameBuffer[300];
    unsigned char *mFramebuffer;
    GLuint mVertexBuffer;
    GLuint mColorBuffer;
    GLuint mTextureBuffer;
    GLuint mdraworderBuffer;
    GLuint mVertexShaderID;
    GLuint mFragmentShaderID;

    glm::mat4 mMVPMatrix, mViewMatrix;
    GLuint vao;
    int type =0;
    int i=0,j =0;
    unsigned char mRed = 0;
    unsigned char mGreen = 0;
    unsigned char mBlue = 0;
    unsigned char mGrayValue = 0;
    static const int nNumVertexCoords = 12;
    static const int nColorCoords = 12;
    static const int nTexCoords = 8;
    static const int ndraworderCoords = 6;

  /*  GLfloat squareCoords[nNumVertexCoords] =
    {
        -1.0f, 1.0f, 1.0f,
        -1.0f, -1.0f, 1.0f,
        1.0f, -1.0f, 1.0f,
        -1.0f, 1.0f, 1.0f,
        1.0f, -1.0f, 1.0f,
        1.0f, 1.0f, 1.0f,
    };
    GLfloat colorCoords[nColorCoords] =
    {
        1.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 1.0f,
        1.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 1.0f,
    };
    GLfloat textureCoords[nTexCoords] =
    {
        0.0f,0.0f,
        0.0f,1.0f,
        1.0f,0.0f,
        0.0f,1.0f,
        1.0f,1.0f,
        1.0f,0.0f,
    };*/
	GLfloat squareCoords[nNumVertexCoords] = {
            -1.0f,  1.0f, 0.0f,   // top left
            -1.0f, -1.0f, 0.0f,   // bottom left
             1.0f, -1.0f, 0.0f,   // bottom right
             1.0f,  1.0f, 0.0f }; // top right

    GLuint drawOrder[ndraworderCoords] = { 0, 1, 2, 0, 2, 3 };
 GLfloat colorCoords[nColorCoords] =
    {
        1.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 1.0f,
        1.0f, 0.0f, 0.0f,
    };
    GLfloat textureCoords[nTexCoords] =
    {
        0.0f,1.0f,
        0.0f,0.0f,
        1.0f,0.0f,
        1.0f,1.0f,
    };
};

#endif //FRAGMENT_BUTTON_CLICK_MODEDRAWABLE_H
