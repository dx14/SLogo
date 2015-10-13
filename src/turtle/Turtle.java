package turtle;
import gui.GUITurtle;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Turtle implements GUITurtle{

	Image turtlePic;
	Point2D gridCoords;
	double width;
	boolean isVisible;
	double angle;
	
	/**
	 * Temporary constructor for turtle
	 * @author John
	 */
	public Turtle() {
	    gridCoords=new Point2D(0,0);
	    isVisible=true;
	           Image im = new Image("http://el.media.mit.edu/logo-foundation/what_is_logo/graphics/image4.jpg");
	                setImage(im);
	                angle=90;
	}
	
	@Override
	public Image getImage() {
		return turtlePic;
	}

	@Override
	public void setImage(Image image) {
		turtlePic = image;
		
	}

	@Override
	public double getXOnGrid() {

		return gridCoords.getX();
	}

	@Override
	public void setXOnGrid(double x) {

		gridCoords = new Point2D(x,gridCoords.getY());
	}

	@Override
	public double getYOnGrid() {
		// TODO Auto-generated method stub
		return gridCoords.getY();
	}

	@Override
	public void setYOnGrid(double y) {

		gridCoords = new Point2D(gridCoords.getX(),y);

		
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public boolean getIsVisible() {
		return isVisible;
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

	
	
	
	
}
