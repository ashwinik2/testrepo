package com.example.fragment_button_click;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

enum CONTROL_MODE{
    CONTROL_MODE0,
    CONTROL_MODE1,
    CONTROL_MODE2
};

public class ControlViewContainer extends Fragment
{

    View mControlView;
    ControlViewAdapter mAdapter;
    private Context mContext;
    ControlViewMode mControlModeFragment;
    CONTROL_MODE mControlMode = CONTROL_MODE.CONTROL_MODE1;


    void setPosition(CONTROL_MODE controlmode)
    {
        mControlMode = controlmode;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        Log.i("onCreateView","Control_view_Container");

        mAdapter = new ControlViewAdapter(generateSimpleList(mControlMode));
        mControlView = inflater.inflate(R.layout.control_view_container, container,false);
        RecyclerView recyclerView =  mControlView.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        return mControlView;

     }

    private List<ControlViewModel> generateSimpleList(CONTROL_MODE controlmode)
    {
        List<ControlViewModel> ControlViewModelList = new ArrayList<>();

        switch (controlmode)
        {
            case CONTROL_MODE0:
                ControlViewModelList.add(new ControlViewButton(0,mContext));
                ControlViewModelList.add(new ControlViewText(String.format(Locale.US, "Text Item View", 1),1));
                ControlViewModelList.add(new ControlViewRadioButton(2));
                break;

            case CONTROL_MODE1:
                ControlViewModelList.add(new ControlViewImageButton(0, mContext));
                ControlViewModelList.add(new ControlViewCheckBox(1, mContext));
                break;
        }
            
        return ControlViewModelList;
    }

 }


























