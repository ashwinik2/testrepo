package com.example.fragment_button_click.opengl;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.opengl.GLSurfaceView;

import com.example.fragment_button_click.opengl.mGLSurfaceView;
import com.example.fragment_button_click.common.INTENT;

public class GLSurfaceViewContainer extends Fragment {

    public GLSurfaceView mGLSurfaceView;

    public void gotMessage(String message) {
        Log.i("GLSurfaceView_Container","gotmessage called!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if(message.matches("Start")) {
            Log.i("GLSurfaceView_Container","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            mGLSurfaceView.onResume();

        } else if (message.matches("Stop")){
            Log.i("GLSurfaceView_Container","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Stop!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            mGLSurfaceView.onPause();
        }
    }
    public void gotMessagefromFilter(String message) {
            Log.i("GLSurfaceView_Container","gotmessage called!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if(message.matches("Blur")) {
                Log.i("GLSurfaceView_Container","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Blur!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                INTENT.i = 1;

            } else if (message.matches("Sharp")){
                Log.i("GLSurfaceView_Container","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Sharp!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                INTENT.i = 2;
            }
            else if (message.matches("EdgeDet")){
                Log.i("GLSurfaceView_Container","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!EdgeDet!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                INTENT.i = 3;
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView","GLSurfaceView_Container");
        //Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.GLSurfaceViewContainer, container,false);
        //mTextView = view.findViewById(R.id.textview);

        mGLSurfaceView = new mGLSurfaceView(getActivity());
        return mGLSurfaceView;

    }
}

