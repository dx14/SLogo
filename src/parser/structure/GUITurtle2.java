package parser.structure;

import java.util.List;

import util.Coordinate;
import util.SlogoPath;

public interface GUITurtle2 {

	public double getHeading();
	
	public Coordinate getCoordinate();
	
	public List<SlogoPath> getPaths();
	
	public Pen getPen();
	
	public boolean isShowing();
	
}
