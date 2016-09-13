package commandnode;

import model.SLogoCharacterState;
import model.SLogoWorkspace;

/**
 * Node representation of Variable command
 */
public class VariableNode extends NullaryNode{

    private String myName;
    private SLogoWorkspace myWorkspace;

    public VariableNode(String myName){
        this.myName = myName;
    }

    public String getName(){
        return myName;
    }

    /**
     * return current value of variable in current workspace
     */
    public double evaluate(SLogoCharacterState state){
        return myWorkspace.getVarValueByName(myName);
    }

    public SLogoWorkspace getWorkspace(){
        return myWorkspace;
    }

    public void setWorkspace(SLogoWorkspace myWorkspace){
        this.myWorkspace = myWorkspace;
    }

}