package parser.structure;

import util.GUIVariable;

public class Variable implements GUIVariable{

	private String myName;
	private double myValue;
	
	public static final double DEFAULT_VALUE = 0;
	
	public Variable(String name){
		this(name, 0);
	}
	
	public Variable(String name, double value){
		myName = name;
		myValue = value;
	}
	
	@Override
	public String getName() {
		return myName;
	}

	@Override
	public double getValue() {
		return myValue;
	}

	@Override
	public void setValue(double newValue) {
		myValue = newValue;
	}
	
	public double increment(){
		return myValue++;
	}

}
