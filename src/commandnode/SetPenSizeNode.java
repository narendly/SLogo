package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of SetPenSize, a Display command
 */
public class SetPenSizeNode extends UnaryNode{

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double pixels = evaluateChild(0, state);
        state.getPen().setSize(pixels);
        return pixels;
    }

}