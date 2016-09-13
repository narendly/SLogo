package commandnode;

import java.util.ArrayList;
import java.util.List;

import exception.SLogoException;
import model.SLogoWorkspace;
import parser.RootEvaluator;
import parser.TreeFactory;

public abstract class VariableCommand extends CommandNode{

    private SLogoWorkspace myWorkspace;
    private TreeFactory myTreeFactory;
    private RootEvaluator myRootEvaluator;

    public SLogoWorkspace getWorkspace(){
        return myWorkspace;
    }

    public void setWorkspace(SLogoWorkspace ws) throws SLogoException{
        myWorkspace = ws;
        myTreeFactory = new TreeFactory(ws);
        myRootEvaluator = new RootEvaluator(getWorkspace());
    }

    public TreeFactory getTreeFactory(){
        return myTreeFactory;
    }

    public RootEvaluator getRootEvaluator(){
        return myRootEvaluator;
    }

    public List<String> clone(List<String> list){
        List<String> copy = new ArrayList<>();
        copy.addAll(list);
        return copy;
    }

}