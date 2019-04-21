package com.example.fragment_button_click;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.fragment_button_click.CONTROL_MODE.CONTROL_MODE0;
import static com.example.fragment_button_click.CONTROL_MODE.CONTROL_MODE1;
import static com.example.fragment_button_click.CONTROL_MODE.CONTROL_MODE2;

public class ControlViewMode extends Fragment  implements AdapterView.OnItemSelectedListener
{
    View mView;
    controlModeListener mCallback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i("onCreateView","ControlViewMode");
        String[] mModes_Item  = new String[]{"MODES", "MODE A", "MODE B", "MODE C"};

        try {
            mCallback = (controlModeListener)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Control_Button_Container crashed"
                    + " must implement buttonClicked");
        }

        mView = inflater.inflate(R.layout.control_mode_fragment, container, false);
        Spinner spinner = (Spinner) mView.findViewById(R.id.spinner);

        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, mModes_Item);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mArrayAdapter);
        spinner.setOnItemSelectedListener(this);
        return mView;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
    {

        Log.i("onItemSelected", "ControlViewMode");
        Toast.makeText(getActivity(), "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

        if (position == 0)
        {
            mCallback.messageFromControlMode(CONTROL_MODE.CONTROL_MODE0);
        }
        else if (position == 1)
        {
            mCallback.messageFromControlMode(CONTROL_MODE.CONTROL_MODE1);
        }
        else
        {
            mCallback.messageFromControlMode(CONTROL_MODE.CONTROL_MODE2);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface controlModeListener
    {
        void messageFromControlMode(CONTROL_MODE controlmode);
    }
}
