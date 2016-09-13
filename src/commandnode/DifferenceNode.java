package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Difference, Subtract, or - commands
 */
public class DifferenceNode extends MathNode {

private final int OPCODE = 1;
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
    	return super.evaluate(state, OPCODE);
    }
    
}