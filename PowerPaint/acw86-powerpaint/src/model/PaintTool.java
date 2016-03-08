/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package model;

import java.awt.Point;
import java.awt.Shape;

/**
 * An interface for  PaintTools which dynamically draws Shapes based on the tool selected.
 * @author Adam Waldron
 * @version 1.0
 */
public interface PaintTool {
    
    /**
     * Returns the shape of the PaintTool which was drawn by the user.
     * @return the Shape object.
     */
    Shape getShape();
    
    /**
     * Adds a new or final point to the Shape being drawn.
     * @param thePoint passes the new or final point to draw the shape.
     */
    void addNewPoint(final Point thePoint);
    
    /**
     * Adds the initial or starting point to the Shape being drawn.
     * @param thePoint passes the starting point to draw the shape.
     */
    void setStartingPoint(final Point thePoint);
    
    /**
     * Returns the name of the PaintTool.
     * @return the String representing the PaintTool's name.
     */
    String getName();
    
    /**
     * Flag for a paint tool to adapt square or circle only logic.
     * @param theSqOrCir passes the value to the stored flag.
     */
    void setSquareCircle(final boolean theSqOrCir);
    
}
