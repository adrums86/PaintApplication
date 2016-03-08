/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package model;


import java.awt.Point;
import java.util.Objects;

/**
 * This class represents a generic paint tool used to draw on the DrawingArea panel.
 * @author Adam Waldron
 * @version 2.0
 */
public abstract class AbstractPaintTool implements PaintTool {
    
    /**
     * The starting point of the drawing tool.
     */
    protected Point myStartingPoint;
    
    /**
     * The newest point passed to the paint tool.
     */
    protected Point myNewPoint;
    
    /**
     * The status of the square/circle only flag. This is stored here for the sole
     * purpose of being able to add new tools that share that logic.
     */
    protected boolean mySqOrCir;
    
    /**
     * This method returns a string representation of the drawing tool.
     * @return the simple name of the drawing tool class.
     */
    public String getName() {
        return getClass().getSimpleName();
    }
    
    /**
     * This method adds a new point to the paint tool, cloning for encapsulation.
     * @param thePoint passes the new point to myNewPoint.
     */
    public void addNewPoint(final Point thePoint) {
        myNewPoint = (Point) Objects.requireNonNull(thePoint.clone());
        
    }

    /**
     * This method sets the starting point for the paint tool.
     * @param thePoint passes the starting point to myStartingPoint.
     */
    public void setStartingPoint(final Point thePoint) {
        myStartingPoint = (Point) Objects.requireNonNull(thePoint.clone());
        
    }
    
    /**
     * This method sets the value of the square/circle only flag.
     * @param theSqOrCir passes the flag value to mySqOrCir.
     */
    public void setSquareCircle(final boolean theSqOrCir) {
        mySqOrCir = theSqOrCir;
    }
    
    /**
     * Returns the starting point for the paint tool, cloned for encapsulation.
     * @return the starting point.
     */
    public Point getStartingPoint() {
        return (Point) Objects.requireNonNull(myStartingPoint.clone());
    }
}
