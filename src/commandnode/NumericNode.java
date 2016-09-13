package commandnode;

import model.SLogoCharacterState;

/**
 * SLogo's NumericNode, a class representing a numerical value (evaluate returns
 * the value) with no children
 *
 */

public class NumericNode extends NullaryNode {

    private double myValue;

    public NumericNode(double value) {
        myValue = value;
    }

    public double evaluate(SLogoCharacterState state) {
        return myValue;
    }

    public double getNumericValue(){
        return myValue;
    }

}