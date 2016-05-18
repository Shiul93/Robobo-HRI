package com.mytechia.robobo.framework.hri.touch.android;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.mytechia.robobo.framework.FrameworkManager;

import com.mytechia.robobo.framework.hri.touch.ATouchModule;
import com.mytechia.robobo.framework.hri.touch.ITouchListener;
import com.mytechia.robobo.framework.hri.touch.TouchGestureDirection;

/**
 * Created by luis on 5/4/16.
 */
public class AndroidTouchModule extends ATouchModule implements GestureDetector.OnGestureListener {
    public  AndroidTouchModule(){
        super();
    }
    public void startup(FrameworkManager manager){

    }
    public void shutdown(){

    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
        motionEvent.getPointerCoords(0,coords);
        notifyTap(Math.round(coords.x),Math.round(coords.y));
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //TODO Dar soporte a varios dedos,¿Pointer count y media de posiciones?
        MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
        motionEvent.getPointerCoords(0,coords);
        MotionEvent.PointerCoords coords1 = new MotionEvent.PointerCoords();
        motionEvent1.getPointerCoords(0,coords1);
        int motionx = Math.round(coords.x)-Math.round(coords.y);
        int motiony = Math.round(coords.x)-Math.round(coords.y);
        if (Math.abs(motionx)>Math.abs(motiony)){
            if (motionx>=0){
                notifyCaress(TouchGestureDirection.DOWN);
            }else {
                notifyCaress(TouchGestureDirection.UP);
            }
        }else{
            if (motiony>=0){
                notifyCaress(TouchGestureDirection.LEFT);
            }else {
                notifyCaress(TouchGestureDirection.RIGHT);
            }
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
        motionEvent.getPointerCoords(0,coords);
        notifyTouch(Math.round(coords.x), Math.round(coords.y));

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //TODO Dar soporte a varios dedos,¿Pointer count y media de posiciones?
        MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
        motionEvent.getPointerCoords(0,coords);
        MotionEvent.PointerCoords coords1 = new MotionEvent.PointerCoords();
        motionEvent1.getPointerCoords(0,coords1);
        int motionx = Math.round(coords.x)-Math.round(coords.y);
        int motiony = Math.round(coords.x)-Math.round(coords.y);
        if (Math.abs(motionx)>Math.abs(motiony)){
            if (motionx>=0){
                notifyFling(TouchGestureDirection.DOWN);
            }else {
                notifyFling(TouchGestureDirection.UP);
            }
        }else{
            if (motiony>=0){
                notifyFling(TouchGestureDirection.LEFT);
            }else {
                notifyFling(TouchGestureDirection.RIGHT);
            }
        }
        return true;
    }
}
