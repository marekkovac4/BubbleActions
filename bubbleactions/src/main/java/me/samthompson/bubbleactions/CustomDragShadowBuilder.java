package me.samthompson.bubbleactions;

import android.graphics.Point;
import android.view.View;

public class CustomDragShadowBuilder extends View.DragShadowBuilder {

    @Override
    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
        outShadowSize.set(1,1);
        outShadowTouchPoint.set(0,0);
    }
}
