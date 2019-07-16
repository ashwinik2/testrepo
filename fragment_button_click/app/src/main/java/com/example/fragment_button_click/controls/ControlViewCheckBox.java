package com.example.fragment_button_click.controls;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.example.fragment_button_click.R;

public class ControlViewCheckBox extends ControlViewModel {

    private View mCheckBoxView;
    private String mCheckBoxYes,mCheckBoxNo;
    private Context mContext;
    private View mView;
    private CheckBox mCheckBox;
    private int CHECKBOX_ITEMVIEW_LIST;
	public int type;

    public ControlViewCheckBox(final Context context,int type)
    {
        super();
        Log.i("ControlViewCheckBox()","ControlViewCheckBox");
        setCheckBoxName();
        mContext = context;
	this.type = type;
    }

    public void setView(View view,final Context mContext){
        Log.i("setview()","ControlViewCheckBox");
        mView = view;
    }

    @NonNull
    public String getCheckBoxYes()
    {
        return mCheckBoxYes;
    }

    public String getcheckBoxNo()
    {
        return mCheckBoxNo;
    }

    public void setCheckBoxName() {
        this.mCheckBoxYes = "YES";
        this.mCheckBoxNo = "NO";
    }

    public int  getButtonLayout()
    {
        return CHECKBOX_ITEMVIEW_LIST;
    }
    
    @Override
    public int getListItemType() {
        return this.type;
    }

    public View createView(Context context) {
        Log.e("createView()","ControlViewCheckBox");
        mCheckBoxView = (View) LayoutInflater.from(context)
                .inflate(R.layout.checkbox_itemview, null);
        setView(mCheckBoxView, context);
        return mCheckBoxView;
    }

    public void bindData(View itemView)
    { 
	mCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox1);
        if(mCheckBox != null)
        {
            mCheckBox.setText(getCheckBoxYes());
        }
        mCheckBox= (CheckBox) itemView.findViewById(R.id.checkBox2);
        if(mCheckBox != null)
        {
            mCheckBox.setText(getcheckBoxNo());
        }
    }
}
