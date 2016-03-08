package view;

import action.ColorAction;
import action.ToolAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * This class constructs the menu bar for the PowerPaintGUI class.
 * @author Adam Waldron
 * @version 2.0
 */
public class MenuBar extends JMenuBar {

    /**
     * 
     */
    private static final long serialVersionUID = -111659266189879540L;
    
    /**
     * Minimum value for thickness slider.
     */
    private static final int SLIDER_MIN = 0;
    
    /**
     * Maximum value for the thickness slider.
     */
    private static final int SLIDER_MAX = 20;
    
    /**
     * Initial value for the thickness slider.
     */
    private static final int SIZE_INIT = 5;
    
    /**
     * The major tick mark interval for the thickness slider.
     */
    private static final int MAJOR_TICK = 5;
    
    /**
     * The minor tick mark interval for the thickness slider.
     */
    private static final int MINOR_TICK = 1;
    
    /**
     * The collection of drawing tool actions.
     */
    private final List<ToolAction> myTools;
    
    /**
     * The menu item that triggers the clearing of the drawing area.
     */
    private JMenuItem myUndo;
    
    /**
     * The drawing area.
     */
    private final DrawingArea myDrawingPanel;
    
    /**
     * The JFrame that holds all the swing components.
     */
    private final JFrame myWindow;
    
    /**
     * Constructor for the menu bar that sits in the main frame.
     * @param theFrame the frame from PowerPaintGUI.
     * @param thePanel the drawing area from PowerPaintGUI.
     * @param theTools the collection of tool actions created in PowerPaintGUI.
     */
    public MenuBar(final JFrame theFrame, final DrawingArea thePanel, 
                   final List<ToolAction> theTools) {
        super();
        myWindow = theFrame;
        myDrawingPanel = thePanel;
        myTools = theTools;
        add(buildFileMenu());
        add(buildOptionsMenu());
        add(buildToolsMenu());
        add(buildHelpMenu());
    }
    
    /**
     * Creates the file menu and listeners.
     * @return the file menu JMenu.
     */
    private JMenu buildFileMenu() {
        myUndo = new JMenuItem("Undo all changes");
        final JMenu file = new JMenu("File");
        final JMenuItem exit = new JMenuItem("Exit");
        
        myUndo.setEnabled(false);
        
        /**
         * This was a far more intuitive solution to me, instead of crow-barring in 
         * the property change listener.
         * */
        file.addMenuListener(new MenuListener() {           
            @Override
            public void menuSelected(final MenuEvent theEvent) {
                if (myDrawingPanel.hasDrawings()) {
                    myUndo.setEnabled(true);
                }             
            }
            @Override
            public void menuDeselected(final MenuEvent theEvent) {
                if (myDrawingPanel.hasDrawings()) {
                    myUndo.setEnabled(true);
                }                
            }
            @Override
            public void menuCanceled(final MenuEvent theEvent) {
                if (myDrawingPanel.hasDrawings()) {
                    myUndo.setEnabled(true);
                }                
            }
        });
        myUndo.addActionListener(new ActionListener() {       
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawingPanel.undoAll();
                myUndo.setEnabled(false);
                myDrawingPanel.repaint();
            }
        });
        exit.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myWindow.dispose();
            }
        });
        myUndo.setMnemonic(KeyEvent.VK_U);
        exit.setMnemonic(KeyEvent.VK_E);
        file.add(myUndo);
        file.add(new JSeparator(SwingConstants.HORIZONTAL));
        file.add(exit);
        file.setMnemonic(KeyEvent.VK_F);
        
        return file;
    }
    
    /**
     * Creates the options menu and the listener for square/circle.
     * @return the options menu JMenu.
     */
    private JMenu buildOptionsMenu() {
        final JMenu options = new JMenu("Options");
        final JCheckBoxMenuItem sqOrCir = new JCheckBoxMenuItem("Square/Circle only");
        final JMenu thicknessSub = new JMenu("Thickness");
   
        options.setMnemonic(KeyEvent.VK_O);
        sqOrCir.setMnemonic(KeyEvent.VK_S);
        thicknessSub.setMnemonic(KeyEvent.VK_T);
        thicknessSub.add(buildSlider());
        sqOrCir.addActionListener(new ActionListener() {           
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawingPanel.toggleSquareCircle();
            }
        });
        options.add(sqOrCir);
        options.add(new JSeparator(SwingConstants.HORIZONTAL));
        options.add(thicknessSub);
        options.add(new JSeparator(SwingConstants.HORIZONTAL));
        options.add(new ColorAction("Color...", myDrawingPanel));
        
        return options;
    }
    
    /**
     * Creates the thickness slider and adds the change listener.
     * @return the thickness/stroke JSlider.
     */
    private JSlider buildSlider() {
        final JSlider thicknessSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, 
                                                    SLIDER_MAX, SIZE_INIT);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);
        final Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
        //@PMD, I considered it...It seems this works just fine...
        
        Integer count = 0;
        while (count <= SLIDER_MAX) {
            table.put(count, new JLabel(count.toString()));
            count += SIZE_INIT;
        }
        thicknessSlider.setLabelTable(table);
        thicknessSlider.setMinorTickSpacing(MAJOR_TICK);
        thicknessSlider.setMajorTickSpacing(MINOR_TICK);
        thicknessSlider.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = thicknessSlider.getValue();
                    myDrawingPanel.setBrushSize(value);
            }
        });
        return thicknessSlider;
    }
    
    /**
     * Creates the tools menu and adds the tool action to each tool.
     * @return the tools JMenu.
     */
    private JMenu buildToolsMenu() {
        final JMenu tools = new JMenu("Tools");
        final ButtonGroup buttonGroup = new ButtonGroup();     
        for (final ToolAction t: myTools) { 
            final JRadioButton jr = new JRadioButton(t);
            tools.add(jr);
            buttonGroup.add(jr);
        }
        tools.setMnemonic(KeyEvent.VK_T);
        return tools;
    }
    
    /**
     * Creates the help menu and adds the about listener.
     * @return the help menu JMenu.
     */
    private JMenu buildHelpMenu() {
        final JMenu help = new JMenu("Help");
        final JMenuItem about = new JMenuItem("About...");
        
        help.setMnemonic(KeyEvent.VK_H);
        about.setMnemonic(KeyEvent.VK_A);
        about.addActionListener(new ActionListener() {     
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                JOptionPane.showMessageDialog(myDrawingPanel, 
                                              "TCSS 305 PowerPaint\nWinter 2016\nAdm Waldron", 
                                              "About", JOptionPane.PLAIN_MESSAGE, 
                                              new ImageIcon(PowerPaintGUI.GRUMPY_ICON));
            }
        });
        help.add(about);
        
        return help;
    }

}
