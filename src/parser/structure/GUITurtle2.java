package parser.structure;

import java.util.List;

import util.SlogoPath;

public interface GUITurtle2 {
	
	public int getID();

	public double getHeading();
	
	public GUICoordinate getCoordinate();
	
	public List<SlogoPath> getPaths();
	
	public GUIPen getPen();
	
	public boolean isShowing();
	
	public boolean isClear();
	
	public void completeUpdate();
	
}
