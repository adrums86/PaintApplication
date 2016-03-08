/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package view;

import java.awt.Color;
import java.awt.Shape;

/**
 * This class is designed to hold store the color, shape, and brush size
 * of a drawing in the drawing area.
 * @author Adam Waldron
 * @version 2.0
 */
public class Drawing {

    /**
     * The color of the drawing.
     */
    private final Color myColor;
    
    /**
     * The shape of the drawing.
     */
    private Shape myShape;
    /**
     * the brush size or stroke of the drawing.
     */
    private final int myBrushSize;
    
    /**
     * The constructor for each Drawing object.
     * @param theColor passes the color to myColor.
     * @param theShape passes the shape to myShape.
     * @param theBrushSize passes the brush size to myBrushSize.
     */
    public Drawing(final Color theColor, final Shape theShape, final int theBrushSize) {
        myColor = theColor;
        myShape = theShape;
        myBrushSize = theBrushSize;
    }
       
    /**
     * Sets the current shape.
     * @param theShape passes the current shape to myShape.
     */
    public void setShape(final Shape theShape) {
        myShape = theShape;
    }
    
    /**
     * Provides access to myShape.
     * @return the shape of the drawing.
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Provides access to myColor.
     * @return the color of the drawing.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Provides access to myBrushSize.
     * @return the brush size of the drawing.
     */
    public int getBrushSize() {
        return myBrushSize;
    }
    
}
