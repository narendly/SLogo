package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of SetHeading, Seth commands, Turtle commands
 */

public class SetHeadingNode extends TurnNode{

    /**
     * @return new direction
     */

    public double calculateDir(SLogoCharacterState state) throws SLogoException {
        return evaluateChild(0, state);
    }
}