package commandnode;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;

public class AskWithNode extends TurtleCommand {

	public AskWithNode() {
		
	}

	@Override
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		for (SLogoCharacter turtle : super.getWorkspace().getCharacterList()) {
			return evaluateChild(0, state) != 0 ? evaluateChild(1, state) : 0;
		}
		return 0;
	}

	
	
}
