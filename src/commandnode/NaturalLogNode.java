package commandnode;

import exception.SLogoException;
import model.ResourceLoader;
import model.SLogoCharacterState;

/**
 * Node representation of Log command, a Math command
 */
public class NaturalLogNode extends UnaryNode{
	
	/**
	 * @return Logarithm evaluation
	 * throws SLogoException if evaluating value out of logarithm domain (x>0)
	 */
	public double evaluate(SLogoCharacterState state) throws SLogoException {
		double toEvaluate = evaluateChild(0, state);
		if(toEvaluate < 0){
			throw new SLogoException(new ResourceLoader().getString("Logarithm"));
		}
		return Math.log(evaluateChild(0, state));	
	}

}