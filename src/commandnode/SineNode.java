package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SineNode extends UnaryNode{

    /**
     * @return sine evaluation of radians converted from degrees
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return Math.sin(Math.toRadians(evaluateChild(0, state)));
    }

}