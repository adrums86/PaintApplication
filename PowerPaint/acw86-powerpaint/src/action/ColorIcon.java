/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package action;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * This class creates a color Icon for display in the menu through the color action.
 * @author Adam Waldron
 * @version 1.0
 */
public class ColorIcon implements Icon {
    /**
     * Size of the color icon.
     */
    private static final int SIZE = 15;
    
    /**
     * Color of the color icon.
     */
    private final Color myColor;
    
    /**
     * Constructor for the color icon.
     * @param theColor passes the color to myColor.
     */
    public ColorIcon(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Paints the Icon based on the size set here and the position of it's parent component.
     */
    @Override
    public void paintIcon(final Component theComponent, final Graphics theGraphic, 
                          final int theX, final int theY) {
        theGraphic.setColor(myColor);
        theGraphic.fillRect(theX, theY, SIZE, SIZE);
    }

    /**
     * Returns the width of the icon.
     */
    @Override
    public int getIconWidth() {      
        return SIZE;
    }

    /**
     * Returns the height of the icon.
     */
    @Override
    public int getIconHeight() {       
        return SIZE;
    }

}
