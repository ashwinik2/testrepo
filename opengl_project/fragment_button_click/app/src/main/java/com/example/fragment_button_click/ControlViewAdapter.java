package com.example.fragment_button_click;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ControlViewAdapter extends RecyclerView.Adapter {

    private List< ControlViewModel > models = new ArrayList<>();
    private LayoutInflater mInflater;

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
        Log.i("onCreateViewHolder()","ControlViewAdapter");
         final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_itemview, parent, false);
        //View view = mInflater.inflate(R.layout.item_simple_itemview, parent, false);
           return new  ControlViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.i("onBindViewHolder()","ControlViewAdapter");
        ((ControlViewHolder)holder).bindData(models.get(position));
    }
    @Override
    public int getItemCount() {
        Log.i("getItemCount()","ControlViewAdapter");
        return models.size();
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_simple_itemview;
    }


    public int getItem(final int id) {
        return models.indexOf(id);
    }
}