package com.example.multiple_gray_image;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        mGLView = new mGLSurfaceView(this);
        setContentView(mGLView);
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
    }


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
    public void start_app(View v)
    {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}