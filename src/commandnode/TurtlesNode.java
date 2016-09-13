package commandnode;
import exception.SLogoException;
import model.SLogoCharacterState;

public class TurtlesNode extends TurtleCommand {

    /**
     * returns the total number of turtles created so far by the turtle factory for the given workspace
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        return super.getWorkspace().getCharacterList().size();
    }

}