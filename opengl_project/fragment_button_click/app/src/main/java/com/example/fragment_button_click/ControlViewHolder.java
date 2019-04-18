package com.example.fragment_button_click;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ControlViewHolder extends RecyclerView.ViewHolder {

    private TextView mSimpleTextView;
    private Context mContext;

     public ControlViewHolder(View itemView) {
         super(itemView);
         if (itemView == null) {

             Log.i("ControlViewHolder()","ControlViewHolder");
             mSimpleTextView = (TextView) itemView.findViewById(R.id.simple_text);
         }

    }

     public void bindData(ControlViewModel viewModel) {
         mSimpleTextView = (TextView) itemView.findViewById(R.id.simple_text);
        mSimpleTextView.setText(viewModel.getSimpleText());
         Log.i("bindData()","ControlViewHolder");
    }
}