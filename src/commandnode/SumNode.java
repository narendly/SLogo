package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Add, Sum, or + commands
 */
public class SumNode extends MathNode{

	private final int OPCODE = 0;
	
	public double evaluate(SLogoCharacterState state) throws SLogoException {
    	return super.evaluate(state, OPCODE);
    }

}