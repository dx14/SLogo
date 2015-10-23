package parser.structure;

import parser.ParserException;

public class Pen implements GUIPen{

	private double myWidth;
	private double myColor;
	private boolean down;
	
	public Pen(){
		this(0, 1, true);
	}
	
	public Pen(double color, double width, boolean down){
		myColor = color;
		myWidth = width;
		this.down = down;
	}
	
	public void setDown(boolean down){
		this.down = down;
	}

	@Override
	public boolean isDown() {
		return down;
	}

	@Override
	public double getWidth() {
		return myWidth;
	}

	@Override
	public void setColor(double d) {
		myColor = d;
	}
	
	public void setWidth(double width) throws ParserException {
		if(width > 0){
			myWidth = width;
		}
		else{
			throw new ParserException("Error: pen width must be greater than 0.");
		}
	}

	@Override
	public double getColor() {
		return myColor;
	}
	
	public Pen clone() {
		return new Pen(myColor, myWidth, down);
	}
}
