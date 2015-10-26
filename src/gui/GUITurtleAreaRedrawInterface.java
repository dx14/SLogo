package gui;

import java.util.List;
import javafx.scene.image.Image;

public interface GUITurtleAreaRedrawInterface{

    public void drawAll ();
    public void addImage(String image);
    public int getImagesSize();
	public String getBGColor();
	public String getImagePathsAsAString();
}
