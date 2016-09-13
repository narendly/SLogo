package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of SetXY, a Turtle Command
 */
public class SetPositionNode extends BinaryNode {

    private static final int DIST_FACTOR = 2;
    
    /**
     * @param state
     * Updates position of turtle to (x, y)
     * @return distance moved
     */

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double distance = calculateDistance(state.getXCoor(), state.getYCoor(), state);
        state.setXCoor(evaluateChild(0, state));
        state.setYCoor(evaluateChild(1, state));
        return distance;
    }

    /**
     * @param x: previous x
     * @param y: previous y
     * @param state
     * Calculates distance moved
     * @return distance moved
     */

    private double calculateDistance(double x, double y, SLogoCharacterState state) 
                                                                throws SLogoException {
        return Math.sqrt(Math.pow(evaluateChild(0, state) - x, DIST_FACTOR)
                         + Math.pow(evaluateChild(1, state) - y, DIST_FACTOR));
    }

}