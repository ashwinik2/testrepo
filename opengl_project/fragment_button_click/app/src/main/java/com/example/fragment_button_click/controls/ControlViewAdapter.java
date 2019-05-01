package com.example.fragment_button_click.controls;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlViewAdapter extends RecyclerView.Adapter  implements ItemTouchHelperAdapter
{
    private List<ControlViewModel> models = new ArrayList<>();
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    ControlViewHolder mViewHolder = null;

    public ControlViewAdapter(final List<ControlViewModel> viewModels,OnStartDragListener dragStartListener)
    {
        if (viewModels != null)
        {
            Log.i("ControlViewAdapter()","ControlViewAdapter");
            mDragStartListener = dragStartListener;
            this.models.addAll(viewModels);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType)
    {
        View mView =null;
        mContext = parent.getContext();

        System.out.println("The total array size is  " + getItemCount());

        Log.i("onCreateViewHolder()","ControlViewAdapter");
        mView = ((ControlViewModel) models.get(viewType)).createView(mContext);;
        mViewHolder= new ControlViewHolder(mView,mContext,mDragStartListener);

        return mViewHolder;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
        Log.i("onBindViewHolder()","ControlViewAdapter");
        ((ControlViewHolder)holder).bindData(models.get(position),getItemViewType(position));
        /*holder.msetOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag((RecyclerView.ViewHolder)itemView);
                }
                return false;
            }
        });*/
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

   @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
       Log.i("onItemMove","ControlViewAdapter");
        /*if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(models, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(models, i, i - 1);
            }
        }*/
       Collections.swap(models, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        Log.i("onItemDismiss","ControlViewAdapter");
        models.remove(position);
        notifyItemRemoved(position);
    }


}