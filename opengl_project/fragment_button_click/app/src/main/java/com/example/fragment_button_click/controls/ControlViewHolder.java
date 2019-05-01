package com.example.fragment_button_click.controls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ControlViewHolder extends RecyclerView.ViewHolder {

    OnStartDragListener mDragStartListener;

     public ControlViewHolder(final View itemView, final Context context, OnStartDragListener dragListener) {

         super(itemView);
         Log.i("ControlViewHolder()","ControlViewHolder");
         mDragStartListener = dragListener;
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context, "Item View "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
             }
         });



         }

     public void bindData(ControlViewModel viewModel, int position) {

         Log.i("bindData()","ControlViewHolder");
         viewModel.bindData(itemView);
    }
}