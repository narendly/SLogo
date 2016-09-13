package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Cosine, Cos commands
 */
public class CosineNode extends UnaryNode{

    /**
     * @return Cosine evaluation of radians converted from degrees
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return Math.cos(Math.toRadians(evaluateChild(0, state)));
    }

}