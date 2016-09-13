package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Product, a Math command
 */
public class ProductNode extends MathNode{

	private final int OPCODE = 3;

	public double evaluate(SLogoCharacterState state) throws SLogoException {
		return super.evaluate(state, OPCODE);
	}

}