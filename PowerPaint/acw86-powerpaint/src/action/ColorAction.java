/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;

import view.DrawingArea;

/**
 * This class represents a color action that opens a color chooser and returns the
 * chosen value to the drawing area.
 * @author Adam Waldron
 * @version 1.0
 */
public class ColorAction extends AbstractAction {

    /**
     * Default serialization for AbstractAction.
     */
    private static final long serialVersionUID = -2962831126702768136L;
    
    /**
     * The drawing area.
     */
    private final DrawingArea myDrawingArea;
    
    /**
     * Constructor for the ColorAction class sets mnemonic, icon, and attaches a
     * JColorChooser dialog to the action.
     * @param theName passes the name of the action to AbstractAction.
     * @param theArea passes the drawing area to myDrawingArea.
     */
    public ColorAction(final String theName, final DrawingArea theArea) {
        super(theName);
        myDrawingArea = theArea;
        putValue(Action.SMALL_ICON, 
                 new ColorIcon(myDrawingArea.getColor()));
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        
    }

    /**
     * Opens the color chooser dialog and sets the current color in the drawing area
     * to the chosen color.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingArea.setColor(JColorChooser.showDialog
                                (myDrawingArea, "Choose a Color", myDrawingArea.getColor()));
        putValue(Action.SMALL_ICON, 
                 new ColorIcon(myDrawingArea.getColor()));

    }

}
