package com.mytechia.robobo.framework.hri.touch;


public interface ITouchListener {
    //TODO Cambiar integers por floats?
    void tap(Integer x, Integer y);
    void touch(Integer x, Integer y);
    void fling(TouchGestureDirection dir);
    void caress(TouchGestureDirection dir);

}
