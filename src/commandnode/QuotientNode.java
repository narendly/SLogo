package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Quotient, a Math command
 */
public class QuotientNode extends MathNode{

private final int OPCODE = 2;
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
    	return super.evaluate(state, OPCODE);
    }

}