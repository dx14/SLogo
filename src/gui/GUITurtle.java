package gui;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface GUITurtle{

public Image getImage();

public void setImage(Image image);
	
public double getXOnGrid();

public void setXOnGrid(double x);

public double getYOnGrid();

public void setYOnGrid(double y);

public void setWidth(double width);

public Color getPenColor();

public void setPenColor(Color penCol);

public double getWidth();

public double getAngle();

public boolean getIsVisible();







}
