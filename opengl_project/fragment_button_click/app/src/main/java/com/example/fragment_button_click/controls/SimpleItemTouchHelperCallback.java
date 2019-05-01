package com.example.fragment_button_click.controls;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;


public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

   private final ItemTouchHelperAdapter mAdapter;
   // ControlViewAdapter mAdapter;

   /* public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        Log.i("SimpleItemCallback","ControlViewAdapter");
        mAdapter = adapter;
    }*/

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter  adapter) {
        Log.i("SimpleItemCallback","SimpleItemTouchHelperCallback");
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled()
    {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled()
    {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i("getMovementFlags","SimpleItemTouchHelperCallback");
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        //int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        Log.e("onMove","SimpleItemTouchHelperCallback");
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i("onSwiped","SimpleItemTouchHelperCallback");
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}