/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package view;

import action.ToolAction;
import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import model.Ellipse;
import model.Line;
import model.Pencil;
import model.Rectangle;


/**
 * PowerPaintGUI
 * This class provides the GUI components for the PowerPaint program. It creates all 
 * the components and stores a JFrame which contains the nested JComponents which compose 
 * the drawing area and tool bar.
 * @author Adam Waldron
 * @version 2.0
 */
public class PowerPaintGUI {
       
    /**
     * Path for the shared icon image.
     */
    public static final String GRUMPY_ICON = "./images/grumpy.png";
    
    /**
     * Stores the visual components of the PowerPaint program.
     */
    private final JFrame myWindow;
    
    /**
     * Stores the drawing area and the tool bar.
     */
    private final JPanel myMainPanel;
    
    /**
     * Creates stores drawings created from mouse input.
     */
    private final DrawingArea myDrawingPanel;
    
    /**
     * The collection of drawing tool actions.
     */
    private final List<ToolAction> myTools;
    
    /**
     * Constructs a new PowerPaintGUI and all the subcomponents stored within.
     */
    public PowerPaintGUI() {
        myWindow = new JFrame();
        myMainPanel = new JPanel(new BorderLayout());
        myDrawingPanel = new DrawingArea();
        myTools = new ArrayList<ToolAction>();
        myWindow.setIconImage(new ImageIcon(GRUMPY_ICON).getImage());
        addTools();
        constructFrame();
    }
    
    /**
     * Adds all the tools into the ToolAction ArrayList.
     */
    private void addTools() {
        myTools.add(new ToolAction(myDrawingPanel, new Pencil().getName(), 
                                   new ImageIcon("./images/pencil.gif"), new Pencil()));
        myTools.add(new ToolAction(myDrawingPanel, new Line().getName(), 
                                   new ImageIcon("./images/line_bw.gif"), new Line()));
        myTools.add(new ToolAction(myDrawingPanel, new Rectangle().getName(), 
                                   new ImageIcon("./images/rectangle_bw.gif"), 
                                   new Rectangle()));
        myTools.add(new ToolAction(myDrawingPanel, new Ellipse().getName(), 
                                   new ImageIcon("./images/ellipse_bw.gif"), new Ellipse()));
              
    }
    
    /**
     * Adds all the menu items to a new JMenuBar.
     */
    private void constructFrame() {
        
        final MenuBar menu = new MenuBar(myWindow, myDrawingPanel, myTools);
        
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setJMenuBar(menu);
        myWindow.setContentPane(myMainPanel);
        myWindow.setVisible(true);
        myMainPanel.add(buildToolBar(), BorderLayout.SOUTH);
        myMainPanel.add(myDrawingPanel, BorderLayout.CENTER);
        myWindow.pack();
    }
    
    /**
     * Creates the tool bar and adds the tool actions.
     * @return the tool bar JToolBar.
     */
    private JToolBar buildToolBar() {
        final JToolBar toolBar = new JToolBar();
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (final ToolAction t: myTools) {  
            final JToggleButton jt = new JToggleButton(t);
            toolBar.add(jt);
            buttonGroup.add(jt);
        }
        
        return toolBar;
    }
    

}
