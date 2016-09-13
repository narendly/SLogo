package commandnode;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoWorkspace;

public class CustomCommandNode extends CommandNode{

    private String myName;
    private SLogoWorkspace myWorkspace;

    public CustomCommandNode(String myName, SLogoWorkspace myWorkspace){
        this.myWorkspace = myWorkspace;
        this.myName = myName;
    }

    public String getName(){
        return myName;
    }

    /**
     * return current value of variable in current workspace
     * @throws SLogoException 
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException{
        return myWorkspace.lookupCustomCommand(myName) == null ? 0 : 1; 
    }

    public SLogoWorkspace getWorkspace(){
        return myWorkspace;
    }

    public void setWorkspace(SLogoWorkspace myWorkspace){
        this.myWorkspace = myWorkspace;
    }

}