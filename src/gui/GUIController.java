package gui;

import java.util.List;

import parser.ParserException;

public interface GUIController {

	public void runCommand (String command) throws ParserException;
	
	public void changeLanguage (String commang) throws ParserException;
	
	public void addGUI();
	
	public int getNumberOfGUIs();

	public void changeGUI(int i);
	
	//public List<GUITurtle> getGUITurtles();
	
	
	
}
