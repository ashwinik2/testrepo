package com.example.fragment_button_click;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewOverlay;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity
        implements
        ControlViewButton.controlButtonListener,
        ControlViewMode.controlModeListener/*,
        View.OnClickListener*/
{
    ControlViewContainer mFragmentControlButton;
    GLSurfaceViewContainer mFragmentGLSurfaceView;
    FrameLayout frameLayout;
    CustomDrawable obj;
    ViewOverlay viewOverlay;
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
        //fragmentTransaction.commit();


        mFragmentControlButton = new ControlViewContainer();
        fragmentTransaction.add(R.id.control_view_container,mFragmentControlButton);
        fragmentTransaction.commit();

        Log.i("MainActivity","After ControlButton and GLSurfaceView Container created");
        frameLayout = findViewById(R.id.glsurfaceview_container);
        viewOverlay = frameLayout.getOverlay();
        obj = new CustomDrawable(this);
        viewOverlay.add(obj);
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
