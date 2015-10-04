package controller;

import gui.GUIException;
import gui.GUIInterface;
import parser.ParserException;
import parser.ParserInterface;
import parser.command.CommandString;
import turtle.BackendTurtle;
import util.SlogoPath;
import util.Variable;

public class SlogoController {

	private ParserInterface myParser;
	private GUIInterface myGUI;
	
	public void moveTurtle(int turtleId, SlogoPath path){
		;
	}
	
	public BackendTurtle getTurtle(int turtleId){
		return null;
	}
	
	// throws exception if a turtle with that ID already exists
	public void addTurtle(int turtleId) throws GUIException {
		;
	}
	
	public void removeTurtle(int turtleId) throws GUIException {
		;
	}
	
	public void runCommand (String command) throws ParserException {
		;
	}
	
	public void updateHistory(int turtleId, CommandString command) {
		;
	}
	
	public Variable getVariable(String variableName) throws ParserException{
		return null;
	}
	
	public void setVariable(String variableName, Double variableValue) throws ParserException{
		;
	}
	
}
