package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.javafx.UnmodifiableArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class GUIParameter {
	
	//like default background, starting image list, command language

	private Color defaultBackground;
	private List<String> imageList;
	private String commandLanguage;
	
	public GUIParameter(String defaultBg, String listOfImages, String cmdLang){
		
		defaultBackground = Color.web(defaultBg);
		
		imageList = new ArrayList<String>(); 
		imageList.addAll(Arrays.asList(listOfImages.split(",")));
		
		commandLanguage = cmdLang;
		
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
