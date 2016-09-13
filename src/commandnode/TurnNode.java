package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Abstract class for commands that turn the turtle
 */

public abstract class TurnNode extends UnaryNode {
    private static final double MAX_DEGREE = 360;
    private static final double MIN_DEGREE = 0;

    /**
     * @param state
     * Updates direction of turtle
     * @return change in direction (degrees)
     */

    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double newDirection = convertDir(calculateDir(state));
        double diff = Math.abs(newDirection - state.getDirection());
        state.setDirection(newDirection);
        return diff;
    }

    /**
     * @param non-bounded direction (degrees)
     * Converts direction to value between 0 and 360 
     * @return bounded direction (degrees)
     */

    public double convertDir(double direction) {
        double result;
        if (direction > MAX_DEGREE) {
            result = direction % MAX_DEGREE;
        }
        else if(direction < MIN_DEGREE) {
            result = MAX_DEGREE - (Math.abs(direction) % MAX_DEGREE);
        }
        else {
            result = direction;
        }
        return result;
    }

    public abstract double calculateDir(SLogoCharacterState state) throws SLogoException;

}