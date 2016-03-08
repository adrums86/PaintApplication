/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package model;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * This class represents a line drawing tool.
 * @author Adam Waldron
 * @version 2.0
 */
public class Line extends AbstractPaintTool {
    
    /**
     * This method creates a new line from to points and returns the shape.
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(myStartingPoint, myNewPoint);
    }

}
