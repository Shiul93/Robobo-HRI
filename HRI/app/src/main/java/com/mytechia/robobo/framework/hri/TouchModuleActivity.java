package com.mytechia.robobo.framework.hri;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
//TODO: Preguntar como arreglar esto, sobra un.com
import com.mytechia.robobo.com.hri.R;
import com.mytechia.robobo.framework.hri.touch.ITouchListener;
import com.mytechia.robobo.framework.hri.touch.ITouchModule;
import com.mytechia.robobo.framework.hri.touch.TouchGestureDirection;
import com.mytechia.robobo.framework.hri.touch.android.AndroidTouchModule;

import static android.widget.Toast.makeText;

public class TouchModuleActivity extends AppCompatActivity implements ITouchListener {
    ITouchModule itm;
    RadioButton rb;
    boolean toggle;
    int posx, posy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_module);
        rb = (RadioButton) findViewById(R.id.radioButton);
        itm = new AndroidTouchModule();
        itm.startupTest(this.getApplicationContext());
        itm.suscribe(this);
        posx = (int) rb.getX();
        posy = (int) rb.getY();


    }

    public boolean onTouchEvent(MotionEvent event){
        itm.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public void tap(Integer x, Integer y) {
        Log.d("AT_module", "tap: x="+x+" y="+y);
        makeText(getApplicationContext(),"Tap",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void touch(Integer x, Integer y) {
        Log.d("AT_module","touch");
       // makeText(getApplicationContext(),"Touch", Toast.LENGTH_SHORT).show();
        rb.toggle();
        toggle = !toggle;
        rb.setChecked(toggle);
    }

    @Override
    public void fling(TouchGestureDirection dir) {
        Log.d("AT_module","fling");
        makeText(getApplicationContext(),"fling", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void caress(TouchGestureDirection dir) {
        Log.d("AT_module","caress");
        makeText(getApplicationContext(),"caress", Toast.LENGTH_SHORT).show();
    }
}
