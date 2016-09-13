package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of TowardsXY, a Turtle Command
 */
public class SetTowardsNode extends TurnNode {

    private static final int NUM_CHILDREN = 2;
    private static final double MAX_DEGREES = 180.0;
    private static final double MIN_DEGREES = 0.0;
    private static final double MIN_FACTOR = 0.000001;

    public SetTowardsNode() throws SLogoException{
        setNumChildren(NUM_CHILDREN);
    }

    /**
     * @param state
     * Points turtle in the direction of point (x, y)
     * @return new direction (degrees)
     */

    public double calculateDir(SLogoCharacterState state) throws SLogoException {
        double diffX = evaluateChild(0, state) - state.getXCoor();
        double diffY = evaluateChild(1, state) - state.getYCoor();
        if (diffY < MIN_DEGREES) {
            return Math.toDegrees(Math.atan(diffX/diffY)) - MAX_DEGREES;
        }
        if (Math.abs(diffX - MIN_DEGREES) < MIN_FACTOR && 
                            Math.abs(diffY - MIN_DEGREES) < MIN_FACTOR) {
            return state.getDirection();
        }
        return Math.toDegrees(Math.atan(diffX/diffY));
    }

}