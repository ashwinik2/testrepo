package com.example.fragment_button_click;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


public class ControlButtonContainer extends Fragment implements View.OnClickListener
{
    controlButtonListener mCallback;
    Button mButtonStart, mButtonStop;
    String message = "From button Fragment";
    View mControlButtonView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        Log.i("onCreateView","Control_Button_Container");
        try {
            mCallback = (controlButtonListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Control_Button_Container crashed"
                    + " must implement buttonClicked");
        }

        //Inflate the layout for this fragment
       mControlButtonView = inflater.inflate(R.layout.control_button_container, container,false);

        mButtonStart = mControlButtonView.findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(this);

        mButtonStop = mControlButtonView.findViewById(R.id.button_stop);
        mButtonStop.setOnClickListener(this);

        return mControlButtonView;
     }

  @Override
    public void onClick(View view)
    {
        Log.e("R.id.button_show","onclick");
        switch (view.getId()){
            case R.id.button_start:
                Log.e("R.id.button_show","onclick button");
                Toast.makeText(getActivity(),"inside button click START",Toast.LENGTH_SHORT).show();
                mCallback.messageFromControlButton("Start");

                break;
            case R.id.button_stop:
                Log.e("R.id.button_show","onclick button");
                Toast.makeText(getActivity(),"inside button click STOP",Toast.LENGTH_SHORT).show();
                mCallback.messageFromControlButton("Stop");
                break;
        }
    }

    /*@Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button btn = view.findViewById(R.id.button_start);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("R.id.button_show","onclick button");
                Toast.makeText(getActivity(),"insise button click",Toast.LENGTH_SHORT).show();

            }
        });
    }*/

    public interface controlButtonListener
    {
         void messageFromControlButton(String message);
    }
 }


























