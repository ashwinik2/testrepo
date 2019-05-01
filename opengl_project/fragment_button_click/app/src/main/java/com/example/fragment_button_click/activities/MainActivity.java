package com.example.fragment_button_click.activities;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewOverlay;
import android.widget.FrameLayout;

import com.example.fragment_button_click.R;
import com.example.fragment_button_click.common.CONTROL_MODE;
import com.example.fragment_button_click.controls.ControlViewButton;
import com.example.fragment_button_click.controls.ControlViewContainer;
import com.example.fragment_button_click.controls.ControlViewMode;
import com.example.fragment_button_click.drawable.CustomDrawable;
import com.example.fragment_button_click.drawable.CustomView;
import com.example.fragment_button_click.opengl.GLSurfaceViewContainer;

public class MainActivity extends AppCompatActivity
        implements
        ControlViewButton.controlButtonListener,
        ControlViewMode.controlModeListener/*,
        View.OnClickListener*/
{
    ControlViewContainer mFragmentControlButton;
    GLSurfaceViewContainer mFragmentGLSurfaceView;
    FrameLayout frameLayout;
    CustomDrawable mCustomDraw;
    ViewOverlay viewOverlayText;
    CustomView mCustomView;
    ControlViewMode mControlModeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        mFragmentGLSurfaceView = new GLSurfaceViewContainer();
        fragmentTransaction.add(R.id.glsurfaceview_container,mFragmentGLSurfaceView);

        mControlModeFragment = new ControlViewMode();
        fragmentTransaction.add(R.id.control_mode_fragment, mControlModeFragment);

        mFragmentControlButton = new ControlViewContainer();
        fragmentTransaction.add(R.id.control_view_container,mFragmentControlButton);
        fragmentTransaction.commit();

        Log.i("MainActivity","After ControlButton and GLSurfaceView Container created");
        frameLayout = findViewById(R.id.glsurfaceview_container);
        viewOverlayText = frameLayout.getOverlay();
        mCustomDraw = new CustomDrawable(this);
        viewOverlayText.add(mCustomDraw);

        /*ConstraintLayout mConstraint = findViewById(R.id.glsurfaceview_container);
        CustomView mView = new CustomView(this);
        mConstraint.addView(mView);*/

        FrameLayout mframelayout = findViewById(R.id.glsurfaceview_container);
        CustomView mView = new CustomView(this);
        mframelayout.addView(mView);

    }

    @Override
    public void messageFromControlButton(String message)
    {
        Log.e("main","messageFromControlButton!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        mFragmentGLSurfaceView.gotMessage(message);

    }
    @Override
    public void messageFromControlMode(CONTROL_MODE controlmode)
    {
        Log.e("main","messageFromControlmode!!!");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentControlButton = new ControlViewContainer();
        mFragmentControlButton.setPosition(controlmode);
        fragmentTransaction.replace(R.id.control_view_container,mFragmentControlButton);
        fragmentTransaction.commit();

    }

}
