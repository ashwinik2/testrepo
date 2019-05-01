package com.example.fragment_button_click.controls;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageButton;

import com.example.fragment_button_click.R;

public class ControlViewImageButton extends ControlViewModel {

    private View mImageButtonView;
    private Context mContext;
    private View mView;
    private ImageButton mImageButton;
    private int IMAGEBUTTON_ITEMVIEW_LIST;

    public ControlViewImageButton(final Context context)
    {
        super();
        Log.i("ControlViewImageButon()","ControlViewImageButton");
        mContext = context;

    }

    public void setView(View view,final Context mContext)
    {
        Log.i("setview()","ControlViewImageButton");
        mView = view;
    }

    public int  getButtonLayout()
    {
        return IMAGEBUTTON_ITEMVIEW_LIST;
    }

    public View createView(Context context) {
        Log.e("createView","ControlViewImageButton");
        mImageButtonView = (View) LayoutInflater.from(context)
                .inflate(R.layout.imagebutton_itemview, null);
        setView(mImageButtonView, context);
        return mImageButtonView;
    }

    public void bindData(View itemView)
    {
        mImageButton = (ImageButton) itemView.findViewById(R.id.imageButton1);
    }
}
