package gui.turtlearea;

public interface GUITurtleAreaRedrawInterface {

    public void drawAll ();

    public void addImage (String image);

    public int getImagesSize ();

    public String getBGColor ();

    public String getImagePathsAsAString ();

	public String getPalette();
}
