/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * The main application driver class that creates a new instance of PowerPaintGUI.
 * @author Adam Waldron
 * @version 2.0
 */
public final class PowerPaint {
    
    /**
     * Private default constructor that does nothing since this is a utility class.
     */
    private PowerPaint() {
        
    }

    /**
     * Creates a new instance of PowerPaintGUI.
     * @param theArgs command line arguments.
     */
    public static void main(final String[] theArgs) {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PowerPaintGUI();
            }
        });

    }

}
