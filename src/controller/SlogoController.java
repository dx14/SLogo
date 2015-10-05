package controller;

import java.util.ArrayList;
import java.util.List;

import gui.GUIController;
import gui.GUIException;
import gui.GUIInterface;
import gui.GUITurtle;
import parser.ParserException;
import parser.ParserInterface;
import parser.command.CommandString;
import turtle.BackendTurtle;
import turtle.Turtle;
import util.SlogoPath;
import util.Variable;

public class SlogoController implements GUIController{

	private ParserInterface myParser;
	private GUIInterface myGUI;
	private List<Turtle> myTurtles;
	
	public void moveTurtle(int turtleId, SlogoPath path){
	}
	
	public BackendTurtle getTurtle(int turtleId){
		return null;
	}
	
	// throws exception if a turtle with that ID already exists
	public void addTurtle(int turtleId) throws GUIException {
	}
	
	public void removeTurtle(int turtleId) throws GUIException {
	}
	
	public void runCommand (String command) throws ParserException {
	}
	
	public void updateHistory(int turtleId, CommandString command) {
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
		
		for(Turtle turt: myTurtles){
			myGUITurtles.add((GUITurtle) turt);
		}
		
		return myGUITurtles;
	}
	
}
