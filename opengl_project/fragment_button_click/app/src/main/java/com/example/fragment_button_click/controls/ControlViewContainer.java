package com.example.fragment_button_click.controls;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragment_button_click.R;
import com.example.fragment_button_click.common.CONTROL_MODE;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ControlViewContainer extends Fragment implements OnStartDragListener
{

    View mControlView;
    ControlViewAdapter mAdapter;
    private Context mContext;
    ControlViewMode mControlModeFragment;
    CONTROL_MODE mControlMode = CONTROL_MODE.CONTROL_MODE1;
    ItemTouchHelper mTouchHelper;
    List<ControlViewModel> ControlViewModelList = new ArrayList<>();


    public void setPosition(CONTROL_MODE controlmode)
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

        mAdapter = new ControlViewAdapter(generateSimpleList(mControlMode),this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mTouchHelper = new ItemTouchHelper(callback);

        mControlView = inflater.inflate(R.layout.control_view_container, container,false);
        RecyclerView recyclerView =  mControlView.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        mTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
      /*  DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));*/
        //recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        return mControlView;

     }

    private List<ControlViewModel> generateSimpleList(CONTROL_MODE controlmode)
    {

        switch (controlmode)
        {
            case CONTROL_MODE0:
                ControlViewModelList.add(new ControlViewButton(mContext));
                ControlViewModelList.add(new ControlViewText(String.format(Locale.US, "Text Item View", 1)));
                ControlViewModelList.add(new ControlViewRadioButton());
                break;

            case CONTROL_MODE1:
                ControlViewModelList.add(new ControlViewImageButton( mContext));
                ControlViewModelList.add(new ControlViewCheckBox( mContext));
                break;
        }
            
        return ControlViewModelList;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
      //  mTouchHelper.startDrag(viewHolder);
    }

 }


























