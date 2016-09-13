package commandnode;

import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Super class for forward and backward commands
 */

public abstract class StraightCommand extends UnaryNode{

	private static final double PRECISION_DIGITS = 100000d;
	private static final int NUM_DOUBLES = 2;
	
	/**
	 * @param direction: turtle's current direction
	 * Calculates new turtle position
	 * @return result: 1x2 Array of new (x, y)
	 */
	public double[] calculateLoc(double direction, SLogoCharacterState state) 
			throws SLogoException {
		double[] result = new double[NUM_DOUBLES];
		
		if(getChild(0) instanceof ParenthesisNode){
			List<Node> innerRoots = ((ListNode) getChild(0)).getInnerRoots();
			for(int x=0; x<innerRoots.size(); x++){
				double innerChildEvaluation = ((ListNode) getChild(0)).evaluateInnerChild(x, state);
				result[0] += Math.sin(Math.toRadians(direction)) * innerChildEvaluation;
				result[1] += Math.cos(Math.toRadians(direction)) * innerChildEvaluation;
			}
		}
		else{
			result[0] = Math.sin(Math.toRadians(direction)) * evaluateChild(0, state);
			result[1] = Math.cos(Math.toRadians(direction)) * evaluateChild(0, state);
		}
		
		result[0] = precisionFix(result[0]);
		result[1] = precisionFix(result[1]);
		return result;
	}

	/**
	 * Rounds coordinate if small error
	 */

	private double precisionFix(double value){
		return (double)Math.round(value * PRECISION_DIGITS) / PRECISION_DIGITS;
	}

}