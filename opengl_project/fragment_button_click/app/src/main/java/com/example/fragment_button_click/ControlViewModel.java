package com.example.fragment_button_click;

import android.content.Context;
import android.util.Log;
import android.view.View;

public abstract class ControlViewModel {

        public ControlViewModel()
        {
            Log.i("ControlViewModel()","ControlViewModel");
        }

        protected abstract View createView(Context context);

        public void bindData(View itemView)
        {

        }
}


