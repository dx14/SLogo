package gui;

import java.util.Observer;

public interface GUIInterface {

	
	public Observer showObserverVariables();
	public Observer showTurtleArea();
	public UpdatableHistory showHistory();
	public Observer showUserDefinedCommands();
	public Observer showTurtlePen();
	public void draw();
	public Object updateGUINumber();
}
