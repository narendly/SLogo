package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of SetBackground, Setbg commands, Display commands
 */
public class SetBackgroundNode extends UnaryNode{

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double index = evaluateChild(0, state);
        state.setBackground((int) index); 
        return index;
    }

}