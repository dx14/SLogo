package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import gui.MainGUI;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUIInterface;
import gui.modelinterface.GUITurtle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import parser.ParserException;
import parser.ParserInterface;
import parser.SlogoParser;
import parser.command.CommandList;


// TODO: backend controller interface

public class SlogoController implements GUIController {
    private Stage myPrimaryStage;
    private BorderPane root;
    private ParserInterface myParser;
    private GUIInterface myGUI;
    private List<GUIInterface> myGUIs;
    private List<ParserInterface> myParsers;

    public SlogoController (Stage primaryStage) {
        myGUIs = new ArrayList<GUIInterface>();
        myParsers = new ArrayList<ParserInterface>();
        myPrimaryStage = primaryStage;

        // initialize with one parser and one GUI

        myParser = new SlogoParser(this);
        root = new BorderPane();
        Scene scene =
                new Scene(root, Double.parseDouble(ResourceBundle
                        .getBundle("resources.config.Controller").getString("width")),
                          Double.parseDouble(ResourceBundle.getBundle("resources.config.Controller")
                                  .getString("height")));
        // 1366x768

        primaryStage.setTitle(ResourceBundle.getBundle("resources.config.Controller")
                .getString("title"));
        primaryStage.setScene(scene);
        primaryStage.show();
        MainGUI myGui = new MainGUI(root, primaryStage, this);
        setGUI(myGui);
        setParser(myParser);
        myGui.draw();
        myGUIs.add(myGui);
        myParsers.add(myParser);
        myGUIs.stream().forEachOrdered(s -> s.updateGUINumber());
        try {
            myParser.runCommand(ResourceBundle.getBundle("resources.config.Controller")
                    .getString("initialcommand"));
        }
        catch (ParserException e) {
        }
    }

    @Override
    public void addGUI () {
        root.getChildren().clear();
        MainGUI myGui = new MainGUI(root, myPrimaryStage, this);
        myParser = new SlogoParser(this);
        setGUI(myGui);
        myGUIs.add(myGui);
        myParsers.add(myParser);
        setParser(myParser);
        myGui.draw();
        myGUIs.stream().forEachOrdered(s -> s.updateGUINumber());
        try {
            myParser.runCommand(ResourceBundle.getBundle("resources.config.Controller")
                    .getString("initialcommand"));
        }
        catch (ParserException e) {
        }
    }

    @Override
    public void runCommand (String command) throws ParserException {
        myParser.runCommand(command);
        myGUI.showHistory().addToHistory(command);
    }

    public void updateHistory (int turtleId, CommandList command) {
    }

    @Override
    public void changeLanguage (String language) throws ParserException {
        myParser.setLanguage(language);
    }

    public void setGUI (GUIInterface gui) {
        myGUI = gui;
        myParser.addVariableObserver(myGUI.showObserverVariables());
        myParser.addTurtleObserver(myGUI.showTurtleArea());
        myParser.addCommandObserver(myGUI.showUserDefinedCommands());
        myParser.addTurtleObserver(myGUI.showTurtlePen());
    }

    public void setParser (ParserInterface parser) {
        myParser = parser;
    }

    @Override
    public int getNumberOfGUIs () {
        return myGUIs.size();
    }

    @Override
    public void changeGUI (int i) {
        root.getChildren().clear();
        setParser(myParsers.get(i));
        setGUI(myGUIs.get(i));
        myGUI.draw();
    }

    // TODO: implement these (front-end team)
    public void clearStamps () {

    }

    public void setBackgroundColor (int index) {
        myGUI.updateBackgroundColor(index);
    }

    public void setPaletteColor (int index, int r, int g, int b) {
        myGUI.getPalette().put(index, Color.rgb(r, g, b, 1).toString());
    }

    @Override
    public GUITurtle getTurtle (int turtleId) {
        return myParser.getTurtle(turtleId);
    }

    @Override
    public void outputCommandContainer (String filename) throws ParserException {
        myParser.outputCommandContainer(filename);
    }
}
