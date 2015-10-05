package gui;

import com.sun.javafx.scene.control.skin.ColorPalette;

import javafx.scene.control.ColorPicker;

public abstract class GUIPalette extends GUIComponent{

	private ColorPalette palette;
	
	public GUIPalette(){
		
		ColorPicker picker = new ColorPicker();
		palette = new ColorPalette(picker);
		
		
		
	}
	
	
	
	
	
}
