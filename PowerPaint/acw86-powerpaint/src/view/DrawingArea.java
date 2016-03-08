/*
* TCSS 305 – Winter 2016
* Assignment 5 – PowerPaint 
*/
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import model.AbstractPaintTool;
import model.Pencil;

/**
 * This class provides the functionality for the drawing area, it also
 * stores the current and a collection of drawings which represent color, 
 * shape(myDrawingTool), and brush width.
 * @author Adam Waldron
 * @version 2.0
 */
public class DrawingArea extends JPanel {

    /**
     * Serialization for extension of JPanel.
     */
    private static final long serialVersionUID = 2066849679620201724L;
    
    /**
     * Default Color for the drawing area.
     */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    
    /**
     * Default drawing tool for the drawing area.
     */
    private static final AbstractPaintTool DEFAULT_TOOL = new Pencil();
    
    /**
     * Default brush size for the drawing area.
     */
    private static final Integer DEFAULT_BRUSH = 5;
    /**
     * Default drawing area size.
     */
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 400);
    
    /**
     * Collection of drawings to be drawn to the panel.
     */
    private final List<Drawing> myDrawings;
    
    /**
     * The drawing currently being drawn in the drawing area.
     */
    private Drawing myCurrentDrawing;
    
    /**
     * The current color selected by the user.
     */
    private Color myCurrentColor;
    
    /**
     * The current brush/stroke size selected by the user.
     */
    private int myCurrentBrushSize;
    
    /**
     * The current tool selected by the user.
     */
    private AbstractPaintTool myCurrentTool;
    
    /**
     * Stores the current selection value of the square/circle check box. 
     */
    private boolean mySquareCircle;
    
    /**
     * Default constructor that calls JPanel constructor, calls addComponents(),
     * and sets default values for the drawing area.
     */
    public DrawingArea() {
        super();
        addComponents();
        myDrawings = new ArrayList<>();
        mySquareCircle = false;
        myCurrentColor = UW_PURPLE;
        myCurrentBrushSize = DEFAULT_BRUSH;
        myCurrentTool = DEFAULT_TOOL;
        
    }
    
    /**
     * Sets the cursor, size, and color of the drawing area, then adds the
     * mouse listener.
     */
    private void addComponents() {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setPreferredSize(PREFERRED_SIZE);
        setBackground(Color.WHITE);
        final MouseInputAdapter mia = new MyMouseInputAdapter();
        addMouseMotionListener(mia);
        addMouseListener(mia);
    }
    
    /**
     * Sets the current color.
     * @param theBrushSize passes the current brush size to the drawing area.
     */
    public void setBrushSize(final int theBrushSize) {
        myCurrentBrushSize = theBrushSize;
    }
    
    /**
     * Sets the current color.
     * @param theColor passes the current color to the drawing area.
     */
    public void setColor(final Color theColor) {
        myCurrentColor = theColor;
    }
    
    /**
     * Sets the current drawing tool.
     * @param theTool passes the current drawing tool to the drawing area.
     */
    public void setTool(final AbstractPaintTool theTool) {
        myCurrentTool = theTool;
    }
    
    /**
     * Allows the frame access to the drawing area current color.
     * @return passes the current color to the frame.
     */
    public Color getColor() {
        return myCurrentColor;
    }
    
    /**
     * Clears all the drawings in the drawing area and sets the current
     * drawing to null.
     */
    public void undoAll() {
        myDrawings.clear();
        myCurrentDrawing = null; //This is to prevent the current drawing from being painted.
    }
    
    /**
     * Checks to see if the collection of drawings contains any drawings.
     * @return the truth value of whether or not the collection is empty.
     */
    public boolean hasDrawings() {
        return !myDrawings.isEmpty();
    }
    
    /**
     * Toggles the truth value of the square/circle flag.
     */
    public void toggleSquareCircle() {
        if (mySquareCircle) {
            mySquareCircle = false;
            myCurrentTool.setSquareCircle(mySquareCircle);
        } else {
            mySquareCircle = true;
            myCurrentTool.setSquareCircle(mySquareCircle);
        }
    }
    
    /**
     * Overridden paint component that first attempts to draw each shape in
     * the myDrawings collection, then attempts to draw the shape currently
     * being drawn.
     */
    @Override
    protected void paintComponent(final Graphics theGraphic) {
        super.paintComponent(theGraphic);
        final Graphics2D aGraphic = (Graphics2D) theGraphic;
        
        // for better graphics display
        aGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        

        for (final Drawing d: myDrawings) {
            aGraphic.setPaint(d.getColor());
            aGraphic.setStroke(new BasicStroke(d.getBrushSize()));
            aGraphic.draw(d.getShape());
        }
        if (Objects.nonNull(myCurrentDrawing)) {
            aGraphic.setPaint(myCurrentDrawing.getColor());
            aGraphic.setStroke(new BasicStroke(myCurrentDrawing.getBrushSize()));
            aGraphic.draw(myCurrentDrawing.getShape());


        }
       
    }
    
    /**
     * Input adapter for mouse events in the drawing panel.
     * @author Adam Waldron
     *
     */
    class MyMouseInputAdapter extends MouseInputAdapter {
        
        /**
         * Starts to draw the current tool in the drawing area.
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (myCurrentBrushSize > 0) {
                myCurrentTool.setSquareCircle(mySquareCircle);
                myCurrentTool.setStartingPoint(theEvent.getPoint());
                myCurrentTool.addNewPoint(theEvent.getPoint());
                myCurrentDrawing = new Drawing(myCurrentColor, myCurrentTool.getShape(), 
                                               myCurrentBrushSize);
            }
            repaint();        
        } 
        
        /**
         * Modifies the current drawing while the mouse is being dragged.
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myCurrentBrushSize > 0) {               
                myCurrentTool.addNewPoint(theEvent.getPoint());
                myCurrentDrawing.setShape(myCurrentTool.getShape());   
            }
            repaint();
        } 
        
        /**
         * Finishes the current drawing, stores it in the collection, then sets
         * myCurrentDrawing to null to prevent unintended drawing in the drawing area.
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (myCurrentBrushSize > 0) {
                myCurrentTool.addNewPoint(theEvent.getPoint());
                myDrawings.add(myCurrentDrawing);           
            }
            
            /**
             * To prevent the current drawing from being displayed after its 
             * added to the collection
             * */
            myCurrentDrawing = null;       
            repaint();
        }
    }
    
    
}
