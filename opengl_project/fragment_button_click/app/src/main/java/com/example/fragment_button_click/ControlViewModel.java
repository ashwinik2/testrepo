package com.example.fragment_button_click;

import android.support.annotation.NonNull;
import android.util.Log;

public class ControlViewModel {
    private String simpleText;

    public ControlViewModel(@NonNull final String simpleText) {
        Log.i("ControlViewModel()","ControlViewModel");
        setSimpleText(simpleText);
    }

    @NonNull
    public String getSimpleText() {
        Log.i("getSimpleText()","ControlViewModel");
        return simpleText;
    }

    public void setSimpleText( @NonNull final String simpleText) {
        Log.i("setSimpleText()","ControlViewModel");
        this.simpleText = simpleText;
    }
}
