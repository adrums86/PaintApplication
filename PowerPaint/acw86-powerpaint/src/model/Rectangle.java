/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * This class represents a tool that draws a rectangle shape.
 * @author Adam Waldron
 * @version 2.0
 */
public class Rectangle extends AbstractPaintTool {
    
    /**
     * This overridden method provides the all the logic for the rectangle/ellipse and 
     * circle/square only if the flag is set to true.
     */
    @Override
    public Shape getShape() {
        final Rectangle2D rectangle = new Rectangle2D.Double();
        rectangle.setFrameFromDiagonal(myStartingPoint, myNewPoint);
        if (mySqOrCir) {
            final double xDistance = rectangle.getMaxX() - rectangle.getMinX();
            final double yDistance = rectangle.getMaxY() - rectangle.getMinY();
            if (xDistance > yDistance) {
                rectangle.setRect(myStartingPoint.getX(), myStartingPoint.getY(), 
                                  yDistance, yDistance);    
            } else {
                rectangle.setRect(myStartingPoint.getX(), myStartingPoint.getY(), 
                                  xDistance, xDistance);
            }
        }
        return rectangle;
    }    
}
