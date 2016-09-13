package model;

import exception.SLogoException;

/**
 * SLogo's TurtleState, a class extending abstract CharacterState
 *
 */
public class SLogoTurtleState extends SLogoCharacterState {

    public SLogoTurtleState(int ID, SLogoPen myPen, double xCoor, double yCoor, 
                            double direction, boolean isHidden, 
                            int shapeIndex) throws SLogoException {
        super(ID, myPen, xCoor, yCoor, direction, isHidden, shapeIndex);
    }

}