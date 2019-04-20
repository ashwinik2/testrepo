package com.example.fragment_button_click;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ControlViewHolder extends RecyclerView.ViewHolder {

     public ControlViewHolder(View itemView,final Context context) {

         super(itemView);
         Log.i("ControlViewHolder()","ControlViewHolder");
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(context, "Item View "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
             }
         });

    }

     public void bindData(ControlViewModel viewModel,int position) {

         Log.i("bindData()","ControlViewHolder");
         viewModel.bindData(itemView);
    }
}