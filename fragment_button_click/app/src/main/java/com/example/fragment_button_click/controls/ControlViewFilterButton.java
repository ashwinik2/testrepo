package com.example.fragment_button_click.controls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragment_button_click.R;

public class ControlViewFilterButton extends ControlViewModel{

    private View mButtonView;
    private String mButtonBlur,mButtonSharp,mButtonEdgeDet;
    private int BUTTON_ITEMVIEW_LIST;
    Button mStart,mStop;
    private Context mContext;
    private View mView;
    controlButtonFilterListener mCallback;
    private Button mButton;
	public int type;

    public ControlViewFilterButton(final Context context,int type) {
        super();
        Log.i("ControlViewFilterButton()","ButtonFilterControlView");
        setButtonNameStart();
        mContext = context;
	this.type = type;

    }

    public void setView(View view,final Context mContext){
        Log.i("setview()","ButtonFilterControlView");
        mView = view;
        try {
            mCallback = (controlButtonFilterListener)mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException("Control_FilterButton_Container crashed"
                    + " must implement buttonClicked");
        }
        Button mButtonBlur = (Button)mView.findViewById(R.id.button_blur);
        mButtonBlur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("R.id.button_blur","onclick button");
                Toast.makeText(mContext,"inside button click BLUR",Toast.LENGTH_SHORT).show();
                mCallback.messageFromControlFilterButton("Blur");
            }
        });

        Button mButtonSharp = (Button)mView.findViewById(R.id.button_sharp);
        mButtonSharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("R.id.button_sharp","onclick button");
                Toast.makeText(mContext,"inside button click SHARP",Toast.LENGTH_SHORT).show();
                mCallback.messageFromControlFilterButton("Sharp");
            }
        });

       Button mButtonEdgeDet = (Button)mView.findViewById(R.id.button_edge_det);
        mButtonEdgeDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("R.id.button_edge_det","onclick button");
                Toast.makeText(mContext,"inside button click EDGEDET",Toast.LENGTH_SHORT).show();
                mCallback.messageFromControlFilterButton("EdgeDet");
            }
        });
    }

    @NonNull
    public String getButtonNameBlur()
    {
        return mButtonBlur;
    }

    public String getButtonNameSharp()
    {
        return mButtonSharp;
    }
    public String getButtonNameEdgeDet()
    {
        return mButtonEdgeDet;
    }
    public void setButtonNameStart() {
        this.mButtonBlur = "BLUR";
        this.mButtonSharp = "SHARP";
        this.mButtonEdgeDet = "EDGEDET";
    }
    public interface controlButtonFilterListener
    {
        void messageFromControlFilterButton(String message);
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
        Log.e("createView()","FilterButtonControlView");
        mButtonView = (View) LayoutInflater.from(context)
                .inflate(R.layout.filterbutton_itemview, null);
        setView(mButtonView, context);
        return mButtonView;
    }

    public void bindData(View itemView)
    {  Log.e("bindData", "FilterButtonControlView");
        mButton = (Button) itemView.findViewById(R.id.button_blur);
        if( mButton  == null){
            Log.i("mButton == null","bindData() FilterButtonControlView\"");
            System.out.println("getButtonNameBlur is    = " + getButtonNameBlur());
            mButton.setText("BLUR");
            mButton.setText(getButtonNameBlur());
        }
        mButton = (Button) itemView.findViewById(R.id.button_sharp);
        if (mButton != null) {
            Log.i("mButton ! != null","bindData() FilterButtonControlView");
            System.out.println("getButtonNameSharp is    = " + getButtonNameSharp());
            mButton.setText("SHARP");
            mButton.setText(getButtonNameSharp());
        }
        mButton = (Button) itemView.findViewById(R.id.button_edge_det);
        if (mButton != null) {
            Log.i("mButton != null","bindData() FilterButtonControlView");
            System.out.println("getButtonNameEdgeDet is    = " + getButtonNameEdgeDet());
            mButton.setText("EDGEDET");
            mButton.setText(getButtonNameEdgeDet());
        }
    }
}
