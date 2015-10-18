package util;

public class Coordinate implements GUICoordinate{
	
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
	
	// TODO: add wrapper
	public void update(double distance, double angle){
		myX += distance * Math.sin(Math.toRadians(angle));
		myY += distance * Math.cos(Math.toRadians(angle));
	}
	
	public Coordinate updateAndCopy(double distance, double angle){
		return new Coordinate(myX + distance * Math.sin(Math.toRadians(angle)),	myY + distance * Math.cos(Math.toRadians(angle)));
	}
	
	public double set(double x, double y){
		double diffX = Math.pow((x-myX), 2);
		double diffY = Math.pow((y-myY), 2);
		double distance = Math.sqrt(diffX + diffY);
		myX = x;
		myY = y;
		return distance;
	}
	
	@Override
	public String toString(){
		return "(" + myX + ", " + myY + ")";
	}

}
