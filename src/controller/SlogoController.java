package controller;

import java.util.List;

import gui.GUIController;
import gui.GUIInterface;
import parser.ParserException;
import parser.ParserInterface;
import parser.SlogoParser;
import parser.command.CommandList;
import turtle.BackendTurtle;
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
	
	
	public void runCommand (String command) throws ParserException {
		
		
		myParser.runCommand(command);
		
		myGUI.showHistory().addToHistory(command);
	}

	
	public void updateHistory(int turtleId, CommandList command) {
	}
	
	@Override
	public void changeLanguage(String language) throws ParserException {
		myParser.setLanguage(language);
	}
	
	public void setGUI(GUIInterface gui) {
	    myGUI = gui;
	    //System.out.println(myGUI.showObserverVariables() == null);
	    myParser.addVariableObserver(myGUI.showObserverVariables());
	    myParser.addTurtleObserver(myGUI.showTurtleArea());
	    myParser.addCommandObserver(myGUI.showUserDefinedCommands());
	}
}
