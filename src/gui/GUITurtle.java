package gui;

import java.util.List;
import util.GUICoordinate;
import util.SlogoPath;

public interface GUITurtle {
	
	public int getID();

	public double getHeading();
	
	public GUICoordinate getCoordinate();
	
	public List<SlogoPath> getPaths();
	
	public List<SlogoPath> getHistory();
	
	public GUIPen getPen();
	public void setPenColor(String color);
	public void penUp();
	public void penDown();
	
	public boolean isShowing();
	
	public boolean isClear();
	
	public void completeUpdate();
	
	public boolean usingImage();
	public void setUsingImage(boolean useImage);   
	
	public String getDisplayString();
	public void setDisplayString(String display);
	
}
