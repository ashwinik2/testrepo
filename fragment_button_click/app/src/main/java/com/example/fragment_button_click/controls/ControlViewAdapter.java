package com.example.fragment_button_click.controls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlViewAdapter extends RecyclerView.Adapter  implements ItemTouchHelperAdapter
{
    private List<ControlViewModel> models = new ArrayList<>();
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    ControlViewHolder mViewHolder = null;
	private int mFromPosition = 0;
    private int mToPosition = 0;
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
        mViewHolder= new ControlViewHolder(mView,mContext);

        return mViewHolder;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
	Log.i("onBindViewHolder()","ControlViewAdapter");
        System.out.println("Position in onBindViewHolder is   = " + position);
        ((ControlViewHolder)holder).bindData(models.get(position));
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("onBindViewHolder1()","ControlViewAdapter");
                if (event.getAction() ==
                        MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemViewType(int position)
    {
	return models.get(position).getListItemType();
    }

    @Override
    public int getItemCount()
    {
        Log.i("getItemCount()","ControlViewAdapter");
        return models.size();
    }

	@Override
    public long getItemId(final int position)
    {
        Log.i("getItem()","ControlViewAdapter");
        return position;
    }	

   @Override
    public boolean onItemMove(int fromPosition, int toPosition)
    {
	Log.i("onItemMove","ControlViewAdapter");
       mFromPosition = fromPosition;
       mToPosition = toPosition;
       System.out.println("mFromPosition is    = " + mFromPosition);
       System.out.println("mToPosition is    = " + mToPosition);

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
