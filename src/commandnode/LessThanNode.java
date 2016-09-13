package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Less? and LessP commands, Boolean commands
 */
public class LessThanNode extends BinaryNode{

    /**
     * @param state
     * @return 1 if 0th child evaluation strictly less than 1st
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return evaluateChild(0, state) < evaluateChild(1, state) ? 1 : 0;
    }
}