package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

public class SetPenStyle extends UnaryNode {

	private static final int DEFAULT_STROKE = 2;
	
 /**
 * Evaluates SetPenStyle command.
 * 
 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		 double myIndex = evaluateChild(0, state);
	     state.getPen().setStrokeStyle(DEFAULT_STROKE);
	     return myIndex;
	}

}
