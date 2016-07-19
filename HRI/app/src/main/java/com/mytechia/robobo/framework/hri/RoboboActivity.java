package com.mytechia.robobo.framework.hri;

import android.os.Bundle;

import com.mytechia.robobo.framework;
import com.mytechia.robobo.framework.activity.DefaultRoboboActivity;
import com.mytechia.robobo.framework.example.dummy.DummyTestModule1;
import com.mytechia.robobo.framework.exception.ModuleNotFoundException;
/**
 * Created by luis on 24/5/16.
 */
public class RoboboActivity extends DefaultRoboboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setDisplayActivityClass(MainActivityTTS.class);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void startRoboboApplication() {

        //start the application code in a new thread
        Thread t = new Thread(new RoboboApp(getRoboboFramework()));
        t.start();


    }

}
