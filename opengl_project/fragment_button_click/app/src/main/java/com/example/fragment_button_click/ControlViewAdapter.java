package com.example.fragment_button_click;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class ControlViewAdapter extends RecyclerView.Adapter
{
    private List< ControlViewModel > models = new ArrayList<>();
    private Context mContext;

    public ControlViewAdapter(final List<ControlViewModel> viewModels)
    {
        if (viewModels != null)
        {
            Log.i("ControlViewAdapter()","ControlViewAdapter");
            this.models.addAll(viewModels);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType)
    {
        View mView =null;
        ControlViewHolder mViewHolder = null;
        mContext = parent.getContext();

        System.out.println("The total array size is  " + getItemCount());

        Log.i("onCreateViewHolder()","ControlViewAdapter");
        mView = ((ControlViewModel) models.get(viewType)).createView(mContext);;
        mViewHolder= new ControlViewHolder(mView,mContext);

        return mViewHolder;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
        Log.i("onBindViewHolder()","ControlViewAdapter");
        ((ControlViewHolder)holder).bindData(models.get(position),getItemViewType(position));
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public int getItemCount()
    {
        Log.i("getItemCount()","ControlViewAdapter");
        return models.size();
    }

    public int getItem(final int id)
    {
        Log.i("getItem()","ControlViewAdapter");
        return models.indexOf(id);
    }
}