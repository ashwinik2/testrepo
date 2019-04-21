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

public class ControlViewMode extends Fragment  implements AdapterView.OnItemSelectedListener
{
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i("onCreateView","ControlViewMode");
        String[] mModes_Item  = new String[]{"MODES", "MODE A", "MODE B", "MODE C"};

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
        Log.i("onItemSelected","ControlViewMode");
        Toast.makeText(getActivity(), "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
