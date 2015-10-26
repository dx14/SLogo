package controller;

import java.util.ArrayList;
import java.util.List;

import gui.GUIController;
import gui.GUIInterface;
import gui.GUITurtle;
import gui.MainGUI;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import parser.ParserException;
import parser.ParserInterface;
import parser.SlogoParser;
import parser.command.CommandList;
import turtle.BackendTurtle;
import util.SlogoPath;

// TODO: backend controller interface

public class SlogoController implements GUIController{
	
	private Stage myPrimaryStage;
	private BorderPane root;

	private ParserInterface myParser;
	private GUIInterface myGUI; //
	
	private List<GUIInterface> myGUIs;
	private List<ParserInterface> myParsers;
	
	
	public SlogoController(Stage primaryStage){
        myGUIs = new ArrayList<GUIInterface>();

		myPrimaryStage = primaryStage;
		
		//initialize with one parser and one GUI
		
		myParser = new SlogoParser(this);
		root = new BorderPane();
	    	
	    	Scene scene = new Scene(root, 1000, 800);

	    	//1366x768
	    	
	        primaryStage.setTitle("Hello World!");
	        primaryStage.setScene(scene);
	        primaryStage.show(); 
	               
	        MainGUI myGui = new MainGUI(root, primaryStage, (GUIController)this);
	        setGUI((GUIInterface)myGui);
	        myGui.draw();
	        
	        myGUIs.add(myGui);
	   myGUIs.stream().forEachOrdered(s -> s.updateGUINumber());

	        
	}
	
	public void addGUI(){
		
		root.getChildren().clear(); 
        MainGUI myGui = new MainGUI(root, myPrimaryStage, (GUIController)this);
        setGUI((GUIInterface)myGui);
        myGui.draw();
        
        myGUIs.add(myGui);
 	   	myGUIs.stream().forEachOrdered(s -> s.updateGUINumber());

	}
	
	
	public void runCommand (String command) throws ParserException {
		
		
		myParser.runCommand(command);
		
		myGUI.showHistory().addToHistory(command);
	}
	
	

	
	public void updateHistory(int turtleId, CommandList command) {
	}
	
	@Override
	public void changeLanguage(String language) throws ParserException {
		myParser.setLanguage(language);
	}
	
	public void setGUI(GUIInterface gui) {
	    myGUI = gui;
	    //System.out.println(myGUI.showObserverVariables() == null);
	    myParser.addVariableObserver(myGUI.showObserverVariables());
	    myParser.addTurtleObserver(myGUI.showTurtleArea());
	    myParser.addCommandObserver(myGUI.showUserDefinedCommands());
	    myParser.addTurtleObserver(myGUI.showTurtlePen());
	}

	@Override
	public int getNumberOfGUIs() {
		// TODO Auto-generated method stub
		return myGUIs.size();
	}

	@Override
	public void changeGUI(int i) {
		// TODO Auto-generated method stub
		root.getChildren().clear();
		setGUI(myGUIs.get(i));
        myGUI.draw();

	}
	
	
	// TODO: implement these (front-end team)
	public void clearStamps(){
		
	}
	
	public void setBackgroundColor(double index){
		
	}
	
	public void setPaletteColor(double index, double r, double g, double b){
		
	}
	
	
	// TODO: implement this function
	@Override
	public GUITurtle getTurtle(int turtleId){
		return null;
	}
	
	// TODO: implement this function
	@Override
	public void outputCommandContainer(String filename) throws ParserException{
		myParser.outputCommandContainer(filename);
	}
}
