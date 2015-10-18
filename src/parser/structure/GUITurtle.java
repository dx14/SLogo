package parser.structure;

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
	
	public boolean isShowing();
	
	public boolean isClear();
	
	public void completeUpdate();
	
}
