package com.example.fragment_button_click;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class CustomDrawable extends Drawable {


    private Paint mpaint;
    private Context mcontext;

    public CustomDrawable(Context context) {
        Log.i("CustomDrawable()"," In customDrawable");
        this.mcontext = context;
        initPaint();
    }

    private void initPaint()
    {
        Log.i("initPaint()"," In customDrawable");
        mpaint = new Paint();
        mpaint.setColor(Color.parseColor("#4D000000"));
        //paint.setColor(Color.CYAN);
        mpaint.setAntiAlias(true);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        Log.i("draw()"," In customDrawable");
        //transparent screen
        canvas.drawColor(mpaint.getColor());

        mpaint.setTextSize(30);
        mpaint.setColor(Color.WHITE);
        mpaint.setFakeBoldText(true);
        canvas.drawText("Left Top",16,50,mpaint);
        canvas.drawText("Left Bottom",16,canvas.getHeight() - 30,mpaint);
        canvas.drawText("Right Top",canvas.getWidth()  - 200,50,mpaint);
        canvas.drawText("Right Botton",canvas.getWidth()  - 200,canvas.getHeight() - 30,mpaint);

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
