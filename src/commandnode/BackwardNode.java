package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * @author Aaron
 * Node representation of Backward, Back, Bk commands that extends StraightCommand
 * Flips x and y coordinates of calculated location to move backwards not forwards
 */
public class BackwardNode extends StraightCommand {

    /**
     * @return pixel distance moved backward based on current direction
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double[] newCoor = calculateLoc(state.getDirection(), state);
        state.setXCoor(state.getXCoor() + -1 * newCoor[0]);
        state.setYCoor(state.getYCoor() + -1 * newCoor[1]);
        return evaluateChild(0, state);
    }

}