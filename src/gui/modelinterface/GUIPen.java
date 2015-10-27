package gui.modelinterface;

import parser.ParserException;
import util.LineStyle;

public interface GUIPen {

	public boolean isDown();
	
	public double getWidth();
	public void setWidth(double width) throws ParserException;
	
	public int getColor();
	
	public void setColor(int color);
	
	public LineStyle getStyle();
	public void setStyle(LineStyle style);
}
