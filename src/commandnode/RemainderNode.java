package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Remainder, a Math command, for Remainder and % commands
 */
public class RemainderNode extends BinaryNode{

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return evaluateChild(0, state) % evaluateChild(1, state);
    }

}