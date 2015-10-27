package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.UnmodifiableArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class GUIParameter {
	
	//like default background, starting image list, command language

	private Color defaultBackground;
	private List<String> imageList;
	private String commandLanguage;
	private Map<Integer, String> defaultPalette;
	
	public GUIParameter(String defaultBg, String listOfImages, String cmdLang, String paletteMap){
		
		defaultBackground = Color.web(defaultBg);
		
		imageList = new ArrayList<String>(); 
		imageList.addAll(Arrays.asList(listOfImages.split(",")));
		
		commandLanguage = cmdLang;
		
		defaultPalette = new HashMap<Integer, String>();
		String[] myList = paletteMap.split(",");
		for(int i = 0; i < myList.length; i = i + 2){
			defaultPalette.put(Integer.parseInt(myList[i]), myList[i+1]);
		}
		
	}

	public Map<Integer, String> getDefaultPalette(){
		
		return defaultPalette;
	}
	
	public String getCommandLanguage() {
		return commandLanguage;
	}

	public Color getDefaultBackground() {
		return defaultBackground;
	}

//	public ObservableList<String> getImageList(){
//		return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(imageList));	
//	}
      public List<String> getImageList(){
          return imageList;  
      }
	
}
