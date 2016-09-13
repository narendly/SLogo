package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * @author Aaron
 * Node representation of Forward command that extends StraightCommand
 */
public class ForwardNode extends StraightCommand {

    /**
     * @return pixel distance moved forward based on current direction
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double[] newCoor = calculateLoc(state.getDirection(), state);
        state.setXCoor(state.getXCoor() + newCoor[0]);
        state.setYCoor(state.getYCoor() + newCoor[1]);
        return evaluateChild(0, state);
    }

}