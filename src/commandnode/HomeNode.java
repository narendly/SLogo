package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * Node representation of Home commands
 */
public class HomeNode extends NullaryNode{

    /**
     * @param state
     * Sends turtle to home (0,0) with direction 0
     * @return distance from old position to home
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        double distance = calculateDistance(state.getXCoor(), state.getYCoor());
        state.setXCoor(0);
        state.setYCoor(0);
        state.setDirection(0);
        return distance;
    }

    /**
     * @return distance traveled to home
     */
    private double calculateDistance(double x, double y){
        return Math.sqrt(x*x + y*y);
    }

}