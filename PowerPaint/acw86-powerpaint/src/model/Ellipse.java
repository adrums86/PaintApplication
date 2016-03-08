/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * This class represents a tool that draws an ellipse or circle depending
 * on the square/circle only flag.
 * @author Adam Waldron
 * @version 2.0
 */
public class Ellipse extends Rectangle {

    /**
     * This method creates a new ellipse and calls the getShape() method from the
     * rectangle superclass and casts the returned shape then sets the bounding
     * rectangle to the value.
     */
    @Override
    public Shape getShape() {      
        final RectangularShape ellipse = new Ellipse2D.Double();
        ellipse.setFrame((Rectangle2D) super.getShape());
        return ellipse;
        
    }
}
