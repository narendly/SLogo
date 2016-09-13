package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Right, Rt commands that extends TurnNode
 */
public class RightNode extends TurnNode {

    /**
     * @param state
     * @return newDirection, a difference in turtle's current direction and child 0's evaluation (degrees to go right)
     */
    public double calculateDir(SLogoCharacterState state) throws SLogoException {
        return state.getDirection() + evaluateChild(0, state);
    }

}