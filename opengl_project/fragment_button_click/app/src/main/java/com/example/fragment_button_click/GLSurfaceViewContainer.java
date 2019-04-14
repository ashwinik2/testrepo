package com.example.fragment_button_click;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.opengl.GLSurfaceView;
import android.widget.TextView;



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

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView","GLSurfaceView_Container");
        //Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.GLSurfaceViewContainer, container,false);
        //mTextView = view.findViewById(R.id.textview);

        mGLSurfaceView = new com.example.fragment_button_click.mGLSurfaceView(getActivity());
        return mGLSurfaceView;

    }
}

