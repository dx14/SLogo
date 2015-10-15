package parser.structure;

import gui.GUITurtle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import util.Coordinate;

// TODO: modify GUI turtle -> change JavaFX specific commands to Strings

public class Turtle implements GUITurtle{
	
	Coordinate myCoord;
	double myHeading;
	boolean penDown;
	boolean visible;
	
	boolean useImage;
	String myImageOrShape;
	
	String penColor;
	
	public Turtle(){
		myCoord = new Coordinate(0,0);
		myHeading = 0;
		penDown = true;
		visible = true;
	}
	
	public void move(double distance){
		System.out.println("Moving turtle from " + myCoord);
		myCoord.update(distance, myHeading);
		System.out.println("to " + myCoord);
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setImage(Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getXOnGrid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setXOnGrid(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getYOnGrid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setYOnGrid(double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getPenColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPenColor(Color penCol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getIsVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}
