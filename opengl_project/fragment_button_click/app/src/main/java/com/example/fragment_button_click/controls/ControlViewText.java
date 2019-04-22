package com.example.fragment_button_click.controls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.fragment_button_click.R;
import com.example.fragment_button_click.controls.ControlViewModel;

public class ControlViewText extends ControlViewModel {
    private String mSimpleText;
    private View mTextView;
    private int TEXT_ITEMVIEW_LIST;
    private View mView;
    private TextView mSimpleTextView;

    public ControlViewText(@NonNull final String simpleText,int itemViewListNumber) {
        super();
        setSimpleText(simpleText);
        TEXT_ITEMVIEW_LIST = itemViewListNumber;
    }
    public void setView(View view, final Context mContext){
        Log.i("setview()","TextControlView");
        mView = view;

    }
    @NonNull
    public String getSimpleText()
    {
        return mSimpleText;
    }
    public int  getTextLayout()
    {
        return TEXT_ITEMVIEW_LIST;
    }

    public void setSimpleText(@NonNull final String simpleText)
    {
        this.mSimpleText = simpleText;
    }

    public View  createView(Context context) {
        mTextView = (View) LayoutInflater.from(context)
                .inflate(R.layout.text_itemview, null);
        setView(mTextView,context);
        return mTextView;
    }
    public void bindData(View itemView) {
        mSimpleTextView = (TextView) itemView.findViewById(R.id.text1);
        mSimpleTextView.setText(getSimpleText());
    }
}