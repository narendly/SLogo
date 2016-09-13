package commandnode;

import java.util.ArrayList;
import java.util.List;

import exception.SLogoException;
import model.SLogoCharacter;
import model.SLogoCharacterState;
import model.SLogoDisplayData;
import model.SLogoTurtleFactory;
import model.SLogoWorkspace;

public abstract class TurtleCommand extends UnaryNode {

    /**
     * Super class that is extended by all turtle command classes. A concise and abstract way of extending functionality for
     * turtle commands who need access to the list of active and all turtles contained within the workspace
     */

    private SLogoWorkspace myWorkspace;
    private SLogoTurtleFactory turtleFactory;
    
    /**
     * Constant that differentiates a normal turtle from a stamped image of a turtle
     */
    private static final int STAMP_ID = -1;

    /**
     * set the workspace
     * 
     * @param SLogoWorkspace sw - current workspace 
     */
    public void setWorkspace(SLogoWorkspace ws){
        myWorkspace = ws;
        turtleFactory = ws.getCurrentTurtleFactory();
    }

    /**
     * get the SLogoTurtleFactory
     * 
     * @param SLogoTurtleFactory turtleFactory - current turtleFactory 
     */
    public SLogoTurtleFactory getTurtleFactory() {
        return turtleFactory;
    }

    /**
     * get the workspace
     * 
     * @return SLogoWorkspace sw - current workspace 
     */
    public SLogoWorkspace getWorkspace(){
        return myWorkspace;
    }

    /**
     * closed way of allowing the super class to handle interacting with the specific turtle factory instance in order to 
     * create and return a turtle with a specific index
     * 
     * @param int turtleIndexToGrab - index of turtle that we want
     * @return SLogoCharacter 
     */
    protected SLogoCharacter grabTurtle(int turtleIndexToGrab) throws SLogoException {
        return turtleFactory.createTurtle(turtleFactory.getDefaultX(), 
               turtleFactory.getDefaultX(), turtleIndexToGrab, 
               turtleFactory.getDefaultDirection(), turtleFactory.getDefaultHidden(), 
               turtleFactory.getDefaultShape());
    }
    
    protected SLogoCharacter createStampTurtle(SLogoCharacterState state) throws SLogoException {
    	return turtleFactory.createTurtle((int)state.getXCoor(), (int)state.getYCoor(), 
    			STAMP_ID, state.getDirection(), state.getHidden(), state.getShapeIndex());
    }
    
    protected void clearStampTurtles(){
    	List<SLogoCharacter> stampsTurtleStateToRemove = new ArrayList<>();
    	List<SLogoDisplayData> stampsDisplayDataToRemove = new ArrayList<>();
    	for (SLogoCharacter turtle: myWorkspace.getCharacterList()) {
    		if (turtle.getState().getID() == STAMP_ID) {
    			stampsTurtleStateToRemove.add(turtle);
    		}
    	}
    	for (SLogoDisplayData stateData : myWorkspace.getObservableDataList()) {
			if (stateData.getID() == STAMP_ID) {
				stampsDisplayDataToRemove.add(stateData);
			}
		}
    	// avoid concurrent modification exceptions by creating new lists we iterate through to remove the turtle's elements
    	if (!stampsTurtleStateToRemove.isEmpty()) {
    		for (SLogoCharacter turtle: stampsTurtleStateToRemove) {
    			turtle.getState().setHidden(true);
    			myWorkspace.getCharacterList()
    							.remove(myWorkspace.getCharacterList().indexOf(turtle));
    			
    		}
    	}
    	if (!stampsDisplayDataToRemove.isEmpty()) {
    		for (SLogoDisplayData turtle: stampsDisplayDataToRemove) {
    			myWorkspace.getObservableDataList().remove(myWorkspace.getObservableDataList()
    					.indexOf(turtle));
    		}
    	}
    }

}
