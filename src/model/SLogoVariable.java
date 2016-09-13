package model;

/**
 * SLogo Variable class that represents variable objects
 *
 * @author Hunter
 */

public class SLogoVariable{

	private String myName;
    private double myValue;

    /**
     * Default constructor for Variable
     * 
     * @param myName
     * @param myValue
     */
    public SLogoVariable(String myName, double myValue) {
    	this.myName = myName;
        this.myValue = myValue;
    }

    /**
     * @return the myValue
     */
    public double getValue() {
        return myValue;
    }

    /**
     * @param myValue the myValue to set
     */
    public void setValue(double myValue) {
        this.myValue = myValue;
    }
    
    public String getName(){
    	return myName;
    }
    
    public void setName(String myName){
    	this.myName = myName;
    }
}