package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Minus command, a Math command that flips a number's value
 */
public class MinusNode extends UnaryNode {

    private static final int SIGN_FLIP = -1;
    
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return SIGN_FLIP * evaluateChild(0, state);
    }

}