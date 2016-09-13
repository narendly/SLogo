package commandnode;

import java.util.ArrayList;
import java.util.List;

import exception.SLogoException;
import model.SLogoCharacterState;
import model.SLogoWorkspace;
import parser.RootEvaluator;
import parser.TreeFactory;

/**
 * @author Adam
 * Node representation of a List of commands inside of a [ ]
 * Extends CommandNode and can have unlimited commands inside
 */
public abstract class ListNode extends CommandNode{

    private List<Node> myCommands;
    private List<String> myInnerCommands;
    private TreeFactory myTreeFactory;
    private RootEvaluator myRootEvaluator;

    public ListNode(SLogoWorkspace ws) throws SLogoException{
    	myTreeFactory = new TreeFactory(ws);
    	myRootEvaluator = new RootEvaluator(ws);
    }
    
    public List<Node> getInnerRoots() throws SLogoException{
    	return myTreeFactory.createRoots(clone(myInnerCommands));
    }
    
    public double evaluateInnerChild(int child, SLogoCharacterState state) throws SLogoException{
    	return getInnerRoots().get(child).evaluate(state);
    }
    
    /**
     * @return Evaluation of last command
     */
    public double evaluate(SLogoCharacterState state) throws SLogoException {
        List<Node> myRoots = getInnerRoots();
        return myRootEvaluator.evaluateRoots(myRoots);
    }

    public List<Node> getCommands(){
        return myCommands;
    }

    public void setInnerCommands(List<String> myInnerCommands){
        this.myInnerCommands = myInnerCommands;
    }

    public List<String> getInnerCommands(){
        return myInnerCommands;
    }
    
    public RootEvaluator getRootEvaluator(){
    	return myRootEvaluator;
    }
    
    private List<String> clone(List<String> list){
        List<String> copy = new ArrayList<>();
        copy.addAll(list);
        return copy;
    }

}