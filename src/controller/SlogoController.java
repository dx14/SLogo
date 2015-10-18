package controller;

import java.util.ArrayList;
import java.util.List;

import gui.GUIController;
import gui.GUIException;
import gui.GUIInterface;
import gui.GUITurtle;
import parser.ParserException;
import parser.ParserInterface;
import parser.SlogoParser;
import parser.command.CommandList;
import parser.structure.Variable;
import turtle.BackendTurtle;
import turtle.Turtle;
import util.SlogoPath;

public class SlogoController implements GUIController{

	private ParserInterface myParser;
	private GUIInterface myGUI;
	

	public void moveTurtle(int turtleId, List<SlogoPath> paths){
	}
	
	public BackendTurtle getTurtle(int turtleId){
		return null;
		
		
	}
	
	public SlogoController(){
		myParser = new SlogoParser();
		
	}
	
	// throws exception if a turtle with that ID already exists
	public void addTurtle(int turtleId) throws GUIException {
	}
	
	public void removeTurtle(int turtleId) throws GUIException {
	}
	
	public void runCommand (String command) throws ParserException {
		
		myGUI.showHistory().addToHistory(command);
		myParser.runCommand(command);
		
	}

	
	public void updateHistory(int turtleId, CommandList command) {
	}
	
	public Variable getVariable(String variableName) throws ParserException{
		return null;
	}
	
	public void setVariable(String variableName, Double variableValue) throws ParserException{
	}

	@Override
	public void changeLanguage(String commang) throws ParserException {
		
	}

	@Override
	public List<GUITurtle> getGUITurtles() {
		List<GUITurtle> myGUITurtles = new ArrayList<GUITurtle>();
//		
//		for(Turtle turt: myTurtles){
//			myGUITurtles.add((GUITurtle) turt);
//		}
//		
		return myGUITurtles;
	}
	
	public void setGUI(GUIInterface gui) {
	    myGUI = gui;
	    System.out.println(myGUI.showObserverVariables() == null);
	    myParser.addVariableObserver(myGUI.showObserverVariables());
	}
}
