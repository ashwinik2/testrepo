package com.example.fragment_button_click.controls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import com.example.fragment_button_click.R;

public class ControlViewHolder extends RecyclerView.ViewHolder implements
        ItemTouchHelperViewHolder {

    OnStartDragListener mDragStartListener;

     public ControlViewHolder(final View itemView, final Context context) {

         super(itemView);
         Log.i("ControlViewHolder()","ControlViewHolder");
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context, "Item View "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
             }
         });

         }

     public void bindData(ControlViewModel viewModel) {

         Log.i("bindData()","ControlViewHolder");
         viewModel.bindData(itemView);
    }
@Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}
