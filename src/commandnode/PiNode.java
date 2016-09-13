package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Pi command, a Math command
 */
public class PiNode extends NullaryNode {

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return Math.PI;
    }

}