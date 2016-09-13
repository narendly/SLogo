package model;

/**
 * SLogo's Position class for accessing previous and current X and Y coordinates
 * 
 * @author Hunter
 *
 */
public class SLogoPosition {

    private static final int PANE_SIZE = 440;
    private static final int TURTLE_SIZE = 40;
    private static final int PADDING = TURTLE_SIZE / 2;
    private static final int HALF_FACTOR = 2;

    private double myPrevX;
    private double myPrevY;
    private double myX;
    private double myY;

    /**
     * Default constructor that sets coordinates to 0
     * 
     */
    public SLogoPosition () {
        myPrevX = 0;
        myPrevY = 0;
        myX = 0;
        myY = 0;
    }

    public SLogoPosition (double x, double y) {
        myPrevX = 0;
        myPrevY = 0;
        myX = x;
        myY = y;
    }

    /**
     * Sets new XY coordinates and automatically updates previous X,Y values
     * Coordinates are bounded
     * 
     * @param x
     * @param y
     */
    public void setXY (double x, double y) {		
        myPrevX = myX;
        myPrevY = myY;
        myX = boundCoordinate(x);
        myY = boundCoordinate(y);
    }

    /**
     * Sets new X coordinate after bounding and updates previous value
     * 
     * @param x
     */
    public void setX (double x) {
        myPrevX = myX;
        myX = boundCoordinate(x);
    }

    /**
     * Sets new Y coordinate after bounding and updates previous value
     * 
     * @param x
     */
    public void setY (double y) {
        myPrevY = myY;
        myY = boundCoordinate(y);
    }

    public double getPrevX() {
        return myPrevX;
    }

    public double getPrevY() {
        return myPrevY;
    }
    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }

    /**
     * Bounds coordinates when they go out of the visible range of coordinates
     * 
     * @param displaydata
     */
    private double boundCoordinate (double coor) {
        if (coor > PANE_SIZE / HALF_FACTOR - PADDING) {
            return (PANE_SIZE / HALF_FACTOR - PADDING);
        }
        if (coor < -PANE_SIZE / HALF_FACTOR + PADDING) {
            return (-PANE_SIZE / HALF_FACTOR + PADDING); 
        }
        return coor;
    }
}