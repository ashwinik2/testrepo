package com.example.fragment_button_click.controls;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.example.fragment_button_click.R;
import com.example.fragment_button_click.controls.ControlViewModel;

public class ControlViewRadioButton extends ControlViewModel {

    private View mRadioButtonView;
    private RadioButton mRadioButton;
    private String mOneRadioButton,mTwoRadioButton;
    private int RADIO_BUTTON_ITEMVIEW_LIST;
    private View mView;

    public ControlViewRadioButton() {
        super();
        Log.i("RadioButtonControlView","RadioButtonControlView");
        setRadioButtonNames();

    }
    public void setView(View view, final Context mContext)
    {
        Log.i("setview()","RadioButtonControlView");
        mView = view;

    }

    @NonNull
    public String getRadioButtonNameOne()
    {
        return mOneRadioButton;
    }

    @NonNull
    public String getRadioButtonNameTwo()
    {
        return mTwoRadioButton;
    }

    public void setRadioButtonNames()
    {
        this.mOneRadioButton = "ONE";
        this.mTwoRadioButton = "TWO";
    }


    public int  getRadioButtonLayout()
    {
        return RADIO_BUTTON_ITEMVIEW_LIST;
    }

    public View createView(Context context) {
        mRadioButtonView = (View) LayoutInflater.from(context)
                .inflate(R.layout.radionbutton_itemview, null);
        setView(mRadioButtonView,context);
        return mRadioButtonView;
    }

    public void bindData(View itemView)
    {
        mRadioButton = (RadioButton) itemView.findViewById(R.id.radioOne);
        mRadioButton.setText(getRadioButtonNameOne());
        mRadioButton = (RadioButton) itemView.findViewById(R.id.radioTwo);
        mRadioButton.setText(getRadioButtonNameTwo());
    }
}
