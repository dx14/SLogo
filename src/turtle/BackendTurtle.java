package turtle;

import javafx.scene.paint.Color;
import util.Coordinate;

public interface BackendTurtle {
	
	// id is set during creating
	public int getId();
	
	public Color getPenColor();
	public void setPenColor(Color c);
	
	public Color getfloodColor();
	public void setFloodColor(Color c);

	public Coordinate getCoordinate();
	public void setCoordinate(Coordinate c);
	
	public boolean isVisible();
	public void setVisible(boolean visible);
	
	public boolean isPenDown();
	public void setPenDown(boolean penDown);
	
	public double getHeading();
	public void setHeading(double heading);
	
	public double getPenWidth();
	// can't be less than zero
	public void setPenWidth(double penWidth);
	
}
