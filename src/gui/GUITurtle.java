package gui;

import java.util.List;
import util.GUICoordinate;
import util.LineStyle;
import util.SlogoPath;

// TODO: change method name in GUITurtleContainer to something else, have it return a separate GUITurtle 
// make classes smaller

public interface GUITurtle {
	
	public int getID();

	public double getHeading();
	
	public GUICoordinate getCoordinate();
	
	public List<SlogoPath> getPaths();
	
	public List<SlogoPath> getHistory();
	
	public GUIPen getPen();
	
	public void setPenColor(double color);
	public void penUp();
	public void penDown();
	public void setPenStyle(LineStyle style);
	public LineStyle getPenStyle();
	
	public boolean isShowing();
	
	public boolean isClear();
	
	public boolean isStamped();
	
	public void completeUpdate(); 
	
	public double getDisplayIndex();
	public void setDisplayIndex(double display);
	
	// TODO: mechanism to get shape, stamps, etc
	
}
