package parser.structure;

import parser.ParserException;

public class Pen implements GUIPen{

	private double myWidth;
	private String myColor;
	private boolean down;
	
	public Pen(){
		myColor = "black";
		myWidth = 1;
		down = true;
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
	public void setColor(String color) {
		myColor = color;
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
	public String getColor() {
		return myColor;
	}
}
