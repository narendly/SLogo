package parser;

import java.util.ArrayList;
import java.util.List;

import commandnode.BracketNode;
import commandnode.CustomCommandNode;
import commandnode.CustomFunctionNode;
import commandnode.ListNode;
import commandnode.Node;
import commandnode.NumericNode;
import commandnode.ParenthesisNode;
import commandnode.TurtleCommand;
import commandnode.VariableCommand;
import exception.SLogoException;
import model.LanguageLoader;
import model.ResourceLoader;
import model.SLogoWorkspace;
import commandnode.VariableNode;

/**
 * SLogo's Tree Factory creates abstract syntax trees of Nodes for evaluation of commands
 * 
 * @author Adam Tache
 *
 */
public class TreeFactory {

	private ResourceLoader myResourceLoader;
	private LanguageLoader myLanguageLoader;
	private SLogoWorkspace myWorkspace;

	public TreeFactory (SLogoWorkspace myWorkspace) throws SLogoException {
		this.myWorkspace = myWorkspace;
		myResourceLoader = new ResourceLoader();
		myLanguageLoader = new LanguageLoader();
		myLanguageLoader.load(myWorkspace.getView().getLanguage());
	}

	/**
	 * Creates tree structures as List<Node> of roots for evaluation
	 * Uses createRoot helper method to create correct type of root
	 * 
	 * @param List<String> commandParts - 
	 * a list of formated input parsed and ready to be used for creation of Nodes
	 */
	public List<Node> createRoots(List<String> commandParts) throws SLogoException {
		List<Node> myRoots = new ArrayList<>();
		while (!commandParts.isEmpty()) {
			String myRootToken = commandParts.remove(0);
			Node myRoot = createRoot(myRootToken);
			if (isToCommand(myRootToken)) {
				String customName = commandParts.remove(0);
				myRoot.addChild(new CustomCommandNode(customName, myWorkspace));
			}
			while (myRoot.numCurrentChildren() != myRoot.numRequiredChildren()) {
				Node nextChild = createChild(commandParts);
				if(nextChild == null){
					throw new SLogoException(getResourceLoader().getString("InvalidCommandTokens"));
				}
				myRoot.addChild(nextChild);
			}
			myRoots.add(myRoot);
		}
		return myRoots;
	}

	/**
	 * Create children of an already process Node
	 * 
	 * @param List<String> commandParts - a list of formated
	 * input parsed and ready to be used for creation of Nodes
	 */
	private Node createChild(List<String> commandParts) throws SLogoException {
		if (commandParts.isEmpty()) {
			return null;
		}
		String myChildToken = commandParts.remove(0);
		if (isOpenBracket(myChildToken) || isOpenParenthesis(myChildToken)) {
			List<String> innerCommands = createCommandList(commandParts);
			if (isOpenBracket(myChildToken)){
				ListNode listNode = new BracketNode(myWorkspace);
				listNode.setInnerCommands(innerCommands);
				return listNode;
			}
			else if(isOpenParenthesis(myChildToken)){
				ListNode listNode = new ParenthesisNode(myWorkspace);
				listNode.setInnerCommands(innerCommands);
				return listNode;
			}
		}
		if (isVariable(myChildToken)) {
			VariableNode myVar = new VariableNode(myChildToken);
			myVar.setWorkspace(myWorkspace);
			return myVar;
		} else {
			Node myChild = createRoot(myChildToken);
			while (myChild.numCurrentChildren() != myChild.numRequiredChildren()) {
				myChild.addChild(createChild(commandParts));
			}
			return myChild;
		}
	}


	/**
	 * Create necessary tree processing when user inputs a list contained 
	 * within brackets
	 * 
	 * @param List<String> commandParts - a list of formated input parsed 
	 * and ready to be used for creation of Nodes
	 */
	private List<String> createCommandList(List<String> commandParts) 
			throws SLogoException {
		List<String> innerCommands = new ArrayList<>();
		int openBrackets = 1;
		int closedBrackets = 0;
		String currCommand;
		while (openBrackets != closedBrackets) {
			currCommand = commandParts.remove(0);
			if (isOpenBracket(currCommand) || isOpenParenthesis(currCommand)) {
				openBrackets++;
			}
			else if (isClosedBracket(currCommand) || isClosedParenthesis(currCommand)) {
				closedBrackets++;
			}
			if(openBrackets != closedBrackets) {
				innerCommands.add(currCommand);
			}
		}
		return innerCommands;
	}
	/**
	 * Creates root by looking at 
	 * 
	 * @param String strNode - create the necessary node from the string passed
	 */
	private Node createRoot(String rootToken) throws SLogoException {
		Node node;
		String rootName = myLanguageLoader.getTranslation(rootToken.toLowerCase());
		if (isNumeric(rootToken)) {
			return new NumericNode(Double.parseDouble(rootToken));
		} else if (isVariable(rootName)) {
			return new NumericNode(0);
		} else if (isCustom(rootToken)) {
			CustomFunctionNode function = new CustomFunctionNode((myWorkspace.lookupCustomCommand(rootName)));
			function.setWorkspace(myWorkspace);
			return function;
		} else {
			try {
				node = (Node) Class.forName(getResourceLoader().getString("CommandNode") 
						+ rootName + getResourceLoader().getString("Node")).newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new SLogoException(getResourceLoader().getString("Command") 
						+ rootName + getResourceLoader().getString("Implemented"));
			}
		}
		if (node instanceof VariableCommand) {
			((VariableCommand)node).setWorkspace(myWorkspace);
		}
		if (node instanceof TurtleCommand) {
			((TurtleCommand)node).setWorkspace(myWorkspace);
		}
		return node;
	}

	/**
	 * Determine if the string inputed has a string regrex equal to numeric notation
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isNumeric(String str) {
		return str.matches("[-+]?\\d*\\.?\\d+");
	}

	/**
	 * Determine if the string inputed is an open parenthesis
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isOpenBracket(String str) {
		return str.equals("[");
	}

	/**
	 * Determine if the string inputed is an open parenthesis
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isOpenParenthesis(String str) {
		return str.equals("(");
	}

	/**
	 * Determine if the string inputed is regrex equal to variable notation
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isVariable(String str) {
		return str.matches(":[a-zA-Z_]+");
	}

	/**
	 * Determine if the string inputed is a closed bracket
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isClosedBracket(String str) {
		return str.equals("]");
	}

	/**
	 * Determine if the string inputed is a closed parenthesis
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isClosedParenthesis(String currCommand) {
		return currCommand.equals(")");
	}

	/**
	 * Determine if the string inputed is a custom command in current workspace
	 * 
	 * @param String str - string to be compared
	 */
	private boolean isCustom(String str){
		return myWorkspace.lookupCustomCommand(str) != null;
	}

	private boolean isToCommand(String command){
		return myLanguageLoader.getTranslation(command.toLowerCase()).equals(new ResourceLoader().getString("MakeUserInstruction"));
	}

	/**
	 * @return the myResourceLoader
	 */
	private ResourceLoader getResourceLoader () {
		return myResourceLoader;
	}

}