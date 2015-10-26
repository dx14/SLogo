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
	
	// NOTE: don't use this to set values - only to get
	public GUIPen getPen();
	
	public void setPenColor(int color);
	public void penUp();
	public void penDown();
	public void setPenStyle(LineStyle style);
	public LineStyle getPenStyle();
	
	public boolean isShowing();
	
	public boolean isClear();
	
	public boolean isStamped();
	
	public void completeUpdate(); 
	
	public int getDisplayIndex();
	public void setDisplayIndex(int display);
	
	// TODO: mechanism to get shape, stamps, etc
	
}
