package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class GUIVariableList extends GUIComponent implements Observer{

	GUIController guiController;
	
	
	ObservableList<String> listOfVariables;
	
	public GUIVariableList(){
		
		listOfVariables =(ObservableList<String>) new ArrayList<String>();
		
	}

	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub
		
		ListView whatToGive = new ListView(listOfVariables);
		
		
		
		return null;
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		System.out.println(o.getClass().getSimpleName());
		if(o instanceof List){
			List oo = (List) o;
			System.out.println(oo.getClass().getSimpleName());
			listOfVariables = (ObservableList<String>) (oo.stream().map( e -> toString()).collect(Collectors.toList()));
		}
		
		else{
			System.out.println("update didnt update it; it might not be an instance of GUIVariableContainer");
		}
		
	}


	
	
}
