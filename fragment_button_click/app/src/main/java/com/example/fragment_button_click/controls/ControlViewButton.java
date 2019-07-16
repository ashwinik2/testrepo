package com.example.fragment_button_click.controls;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import com.example.fragment_button_click.R;

public class ControlViewButton extends ControlViewModel {

    private View mButtonView;
    private String mStartButton,mStopButton;
    private int BUTTON_ITEMVIEW_LIST;
    Button mStart,mStop;
    private Context mContext;
    private View mView;
    controlButtonListener mCallback;
    private Button mButton;
    public int type;
    public TextView mTextView;
    public ControlViewButton(final Context context,int type) {
        super();
        Log.i("ButtonControlView()","ButtonControlView");
        setButtonNameStart();
        mContext = context;
	this.type = type;

    }

    public void setView(View view,final Context mContext){
        Log.i("setview()","ButtonControlView");
        mView = view;
        try {
            mCallback = (controlButtonListener)mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException("Control_Button_Container crashed"
                    + " must implement buttonClicked");
        }
        Button mButtonStart = (Button)mView.findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("R.id.button_show","onclick button");
                Toast.makeText(mContext,"inside button click START",Toast.LENGTH_SHORT).show();
                mCallback.messageFromControlButton("Start");
            }
        });

        Button mButtonStop = (Button)mView.findViewById(R.id.button_stop);
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("R.id.button_show","onclick button");
                Toast.makeText(mContext,"inside button click STOP",Toast.LENGTH_SHORT).show();
                mCallback.messageFromControlButton("Stop");
            }
        });
    }

    @NonNull
    public String getButtonNameStart()
    {
        return mStartButton;
    }

    public String getButtonNameStop() {
        return mStopButton;
    }
    public void setButtonNameStart() {
        this.mStartButton = "START";
        this.mStopButton = "STOP";
    }
    public interface controlButtonListener
    {
        void messageFromControlButton(String message);
    }
    public int  getButtonLayout()
    {
        return BUTTON_ITEMVIEW_LIST;
    }

	@Override
    public int getListItemType() {
        return this.type;
    }
    public View createView(Context context) {
        Log.e("createView()","ButtonControlView");
        mButtonView = (View) LayoutInflater.from(context)
                .inflate(R.layout.button_itemview, null);
       setView(mButtonView, context);
        return mButtonView;
    }

    public void bindData(View itemView)
    {
	    Log.e("bindData","ButtonControlView");
        mTextView = itemView.findViewById(R.id.textview1);
        mButton = (Button) itemView.findViewById(R.id.button_start);
        if( mButton!= null){
            Log.i("mButton  != null","bindData() ButtonControlView");
            System.out.println("getButtonNameStart is    = " + getButtonNameStart());
            mButton.setText(getButtonNameStart());
        }
        mButton = (Button) itemView.findViewById(R.id.button_stop);
        if( mButton != null){
            Log.i("mButton  != null","bindData() ButtonControlView");
            System.out.println("getButtonNameStart is    = " + getButtonNameStop());
            mButton.setText(getButtonNameStop());
        }
	   }
}
