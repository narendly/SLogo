package view;

import java.util.Observable;

import javafx.scene.paint.Color;

/**
 * A container class that is referenced by view 
 * Keeps track of screen information that could be dynamically
 * altered mid-simulation by the user through GUI interaction
 * 
 * Users Observable interface
 *
 */
public class SLogoPropertiesData extends Observable {

    private Color myPaneColor; 

    /**
     * @return the myPaneColor
     */
    public Color getPaneColor() {
        return myPaneColor;
    }

    /**
     * @param myPaneColor the myPaneColor to set
     */
    public void setPaneColor(Color myPaneColor) {
        this.myPaneColor = myPaneColor;
        setChanged();
    }

}