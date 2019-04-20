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
import android.widget.Button;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ControlViewContainer extends Fragment
{

    String message = "From button Fragment";
    View mControlButtonView;
    View mControlView;
    ControlViewAdapter mAdapter;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        Log.i("onCreateView","Control_Button_Container");
        mAdapter = new ControlViewAdapter(generateSimpleList());
        mControlView = inflater.inflate(R.layout.control_button_container, container,false);
        RecyclerView recyclerView =  mControlView.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        return mControlView;
     }

    private List<ControlViewModel> generateSimpleList()
    {
        List<ControlViewModel> ControlViewModelList = new ArrayList<>();

        ControlViewModelList.add(new ControlViewButton(0,mContext));
        ControlViewModelList.add(new ControlViewText(String.format(Locale.US, "Text Item View", 1),1));
        ControlViewModelList.add(new ControlViewRadioButton(2));
            
        return ControlViewModelList;
    }

 }


























