package model;

import exception.SLogoException;

/**
 * SLogo's Turtle Factory class to create turtles
 * 
 */
public class SLogoTurtleFactory {

	private static final double DEFAULT_DIRECTION = 0; 
	private static final boolean DEFAULT_HIDDEN = false;
	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y  = 0;
	private static final int DEFAULT_SHAPE_INDEX = 1;
	private int myStampID;
	private int myLastID;
	private SLogoWorkspace myWorkspace;

	public SLogoTurtleFactory(SLogoWorkspace myWorkspace) {
		this.myWorkspace = myWorkspace;
		myStampID = Integer.parseInt(new ResourceLoader().getString("StampID"));
	}

	/**
	 * Initializes a turtle with given ID. Makes it available to frontend. If turtle already initialized and is currently contained in the workspace
	 * then that turtle instance is grabbed and added to the active turtles list
	 * @return myTurtle: created turtle
	 * 
	 * @param myX
	 * @param myY
	 * @param requestedID
	 */
	
	public SLogoCharacter createTurtle(int myX, int myY, int requestedID, double myDirection, 
									boolean myHidden, int myShapeIdx) throws SLogoException{
		if (hasTurtleBeenCreated(requestedID) && requestedID > myStampID) {
			return myWorkspace.getCharacterList().get(requestedID);
		}
		SLogoPen myPen = new SLogoPen();
		SLogoTurtle myTurtle;
		if (requestedID == myStampID) {
			myTurtle = new SLogoTurtle(myStampID, myPen, myX, myY, myDirection, myHidden, myShapeIdx);
		}
		else {
			myTurtle = new SLogoTurtle(myLastID++, myPen, myX, myY, DEFAULT_DIRECTION, DEFAULT_HIDDEN, DEFAULT_SHAPE_INDEX);
		}
		myWorkspace.getCharacterList().add(myTurtle);
		SLogoDisplayData turtleData = new SLogoDisplayData(myTurtle.getState());
		turtleData.addObserver(myWorkspace.getView().getObserver());
		myWorkspace.getObservableDataList().add(turtleData);
		return myTurtle;
	}

	/**
	 * Checks whether ID is available
	 */
	private boolean hasTurtleBeenCreated(int requestedID) {
		return (requestedID < myLastID);
	}

	/**
	 * Creates a turtle with default parameters with next ID
	 */
	
	public SLogoCharacter createDefaultTurtle() throws SLogoException {
		return createTurtle(DEFAULT_X, DEFAULT_Y, myLastID, DEFAULT_DIRECTION, DEFAULT_HIDDEN, DEFAULT_SHAPE_INDEX);
	}
		
	public int getDefaultX() {
		return DEFAULT_X;
	}

	public double getDefaultDirection() {
		return DEFAULT_DIRECTION;
	}
	
	public boolean getDefaultHidden() {
		return DEFAULT_HIDDEN;
	}
	
	public int getDefaultShape() {
		return DEFAULT_SHAPE_INDEX;
	}
}