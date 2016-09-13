package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;

/**
 * SLogo's highest level in Node hierarchy
 * Includes CommandNodes and NumericNode
 *
 */

public interface Node {
    double evaluate(SLogoCharacterState state) throws SLogoException;
    int numRequiredChildren();
    int numCurrentChildren();
    String toString();
    void addChild(Node child);

}