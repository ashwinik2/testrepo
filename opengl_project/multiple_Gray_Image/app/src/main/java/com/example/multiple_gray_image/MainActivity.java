package com.example.multiple_gray_image;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.view.Gravity;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;
import 	android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener{

  public GLSurfaceView mGLView;
    public Button mButton;

    public void start_app(View v)
    {
       /*Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();*/
        setContentView(mGLView);
       mGLView.requestRender();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mGLView = new mGLSurfaceView(this);
        setContentView(R.layout.activity_main);
        setContentView(mGLView);
        LinearLayout ll = new LinearLayout(this);
        mButton = (Button)findViewById(R.id.button);
       // mButton = new Button(this);
        mButton.setText("START");
        ll.addView(mButton);
        ll.setGravity( Gravity.CENTER_VERTICAL | Gravity.BOTTOM);
        this.addContentView(ll,
                new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
      // mButton.setOnClickListener(this);
        /*mButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                mGLView.requestRender();
            }
        });
       /* mButton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view)
                                       {
                                           mGLView.requestRender();
                                       }
                                   });
       // Button button = (Button) findViewById(R.id.button);
       // Button button = (Button) findViewById(R.id.button);
       /* button.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
       start_app(mGLView);

    }
    public void onClick(View v)
    {
        if(v == mButton)
        {
            //finish();

            mGLView.requestRender();
        }
    }
    /*@Override
    public void onClick(View mGLView){
        mGLView.requestRender();*/



    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

}