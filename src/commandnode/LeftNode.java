package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Left, Lt commands that extends TurnNode
 */
public class LeftNode extends TurnNode {

    /**
     * @param state
     * @return newDirection, a difference in turtle's current direction and child 0's evaluation (degrees to go left)
     */
    public double calculateDir(SLogoCharacterState state) throws SLogoException {
        return state.getDirection() - evaluateChild(0, state);
    }

}