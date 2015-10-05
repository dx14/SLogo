package gui;

import java.util.List;

import parser.ParserException;

public interface GUIController {

	public void runCommand (String command) throws ParserException;
	
	public void changeLanguage (String commang) throws ParserException;
	
	public List<GUITurtle> getGUITurtles();
	
	
	
	
}
