package com.example.fragment_button_click.controls;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.graphics.Canvas;

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
@Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            itemViewHolder.onItemSelected();
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
        itemViewHolder.onItemClear();
    }
    
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float width = (float) viewHolder.itemView.getWidth();
            float alpha = 1.0f - Math.abs(dX) / width;
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY,
                    actionState, isCurrentlyActive);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            View itemView = viewHolder.itemView;
            c.save();
            c.clipRect(itemView.getLeft() + dX, itemView.getTop() + dY, itemView.getRight() + dX, itemView.getBottom() + dY);
            c.translate(itemView.getLeft() + dX, itemView.getTop() + dY);

            // draw the frame
            c.drawColor(0x33000000);

            c.restore();
        }
    }
}
