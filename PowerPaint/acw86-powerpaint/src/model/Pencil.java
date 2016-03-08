/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package model;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.Objects;

/**
 * This class represents a pencil tool that creates a path from mouse movement
 * and returns the final shape of the drawn path.
 * @author Adam Waldron
 * @version 2.0
 */
public class Pencil extends AbstractPaintTool {
    
    /**
     * Stores the path while being drawn.
     */
    private Path2D myPath;
    
    /**
     * Starts the path, draws a single dot at the first click in the drawing area.
     */
    @Override
    public Shape getShape() {
        myPath.moveTo(myNewPoint.getX(), myNewPoint.getY());
        return myPath;
    }
    
    /**
     * This method is overridden to keep the path from drawing to the previous path drawn.
     */
    @Override
    public void setStartingPoint(final Point thePoint) {
        myStartingPoint = (Point) Objects.requireNonNull(thePoint.clone());
        myPath = new Path2D.Double();
        myPath.reset();
        myPath.moveTo(myStartingPoint.getX(), myStartingPoint.getY());
        
    }
    
    /**
     * Overriding this method and adding the lineTo method for myPath 
     * created the smoothest line.
     */
    @Override
    public void addNewPoint(final Point thePoint) {
        myNewPoint = (Point) Objects.requireNonNull(thePoint.clone());
        myPath.lineTo(myNewPoint.getX(), myNewPoint.getY());
     
    }
    
    
    
    
}
