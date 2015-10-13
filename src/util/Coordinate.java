package util;

public class Coordinate {
	
	private double myX;
	private double myY;
	
	public Coordinate(double x, double y){
		myX = x;
		myY = y;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	@Override
	public String toString(){
		return "(" + myX + ", " + myY + ")";
	}

}