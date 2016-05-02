package com.mytechia.robobo.framework.hri.touch;

/**
 * Created by luis on 5/4/16.
 */

enum TouchGestureDirection{LEFT, UP, DOWN, RIGHT};

public interface ITouchListener {
    void tap(Integer x, Integer y);
    void touch(Integer x, Integer y);
    void fling(TouchGestureDirection dir);
    void caress(TouchGestureDirection dir);

}
