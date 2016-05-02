package com.mytechia.robobo.framework.hri.touch;

/**
 * Created by luis on 5/4/16.
 */


public abstract class ATouchModule implements ITouchModule {
    private  ITouchListener listeners;

    protected void notifyTap(Integer x, Integer y){

    }
    protected void notifyFling(Integer x, Integer y){

    }
    protected void notifyCaress(TouchGestureDirection dir){

    }
    protected void notifyTouch(Integer x, Integer y){

    }
    public void suscribe(ITouchListener listener){

    }
    public void unsuscribe(ITouchListener listener){

    }

}
