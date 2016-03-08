/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import model.AbstractPaintTool;
import view.DrawingArea;

/**
 * This class represents the drawing tool action, so buttons and menus can be created
 * dynamically.
 * @author Adam Waldron
 * @version 1.0
 */
public class ToolAction extends AbstractAction {

    /**
     * Default serialization for AbstractAction.
     */
    private static final long serialVersionUID = 908082071881820134L;

    /**
     * Holds the value of the tool used in the various components.
     */
    private final AbstractPaintTool myTool;
    
    /**
     * Holds the value of the drawing area.
     */
    private final DrawingArea myDrawingArea;
    
    /**
     * Constructor for ToolAction sets up the icons, mnemonics, description, and coordinates
     * button selection between components.
     * @param theArea passes the drawing area to myDrawingArea.
     * @param theName passes the name to set the mnemonic and description.
     * @param theIcon passes the icon associated with the action.
     * @param theTool passes the tool to myTool.
     */
    public ToolAction(final DrawingArea theArea, final String theName, final Icon theIcon, 
                      final AbstractPaintTool theTool) {
        super(theName);
        putValue(Action.LARGE_ICON_KEY, theIcon);
        
        // set a mnemonic on the first character of the name
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
        
        // tool tips
        putValue(Action.SHORT_DESCRIPTION, theName + " background");
        
        // coordinate button selection
        putValue(Action.SELECTED_KEY, true);
        
        myTool = theTool;        
        myDrawingArea = theArea;

    }

    /**
     * Sets the current tool being used in the drawing area to the tool
     * associated with the given action.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingArea.setTool(myTool);
        
    }
    
}


