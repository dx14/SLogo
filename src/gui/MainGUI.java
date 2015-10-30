package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.ResourceBundle;
import gui.console.GUIConsole;
import gui.listdisplay.GUIHistory;
import gui.listdisplay.GUIUserDefinedCommands;
import gui.listdisplay.GUIVariableList;
import gui.listdisplay.UpdatableHistory;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUIInterface;
import gui.modelinterface.GUITurtle;
import gui.palette.GUIPaletteBackground;
import gui.palette.pen.GUIPenDisplayContainer;
import gui.statedisplay.GUIAnimationSpeedDisplay;
import gui.statedisplay.GUIImageDisplay;
import gui.statedisplay.GUIPaletteDisplay;
import gui.toolbar.GUIToolbar;
import gui.turtlearea.GUITurtleArea;
import gui.xml.GUIParameter;
import gui.xml.XMLParser;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.SlogoPath;


/**
 * This is the class that has the outermost-level Main GUI to be displayed.
 * Implements GUIInterface to hide unneeded methods from the controller.
 * It can be quite large due to the many individual GUI components
 * @author John, Efe
 */

public class MainGUI implements GUIInterface {

    private BorderPane mainRoot;
    private Stage mainStage;
    private List<GUITurtle> turtleList;
    private List<SlogoPath> pathList;
    private GUIController myGUIController;
    private GUITurtleArea myGUITurtleArea;
    private GUIHistory myGUIHistory;
    private GUIPaletteBackground myGUIPaletteBackground;
    private GUIPenDisplayContainer myGUIPen;
    private GUIToolbar myGUIToolbar;
    private GUIConsole myGUIConsole;
    private GUIVariableList myGUIVariables;
    private GUIUserDefinedCommands myUserDefinedCommands;
    private GUIImageDisplay myGUIImageDisplay;
    private GUIPaletteDisplay myGUIPaletteDisplay;
    private GUIAnimationSpeedDisplay myGUIAnimationSpeedDisplay;
    private GUIParameter myParams;
    private ResourceBundle myResources;

    /**
     * Instantiates a new main gui.
     *
     * @param root the root
     * @param stage the stage
     * @param controller the controller
     */
    public MainGUI (BorderPane root, Stage stage, GUIController controller) {
        myResources=ResourceBundle.getBundle("resources.guitext.MainGUI");
        // debating over using src/resources
        try {
            XMLParser a = new XMLParser(new File("src/resources/default.xml"));
            myParams = a.getParameters();

            /*
             * String output = myParams.getImageList().stream().reduce((t, u) -> t + "," + u).
             * get();
             * 
             * a.saveToXML("src/resources/default.xml", myParams.getDefaultBackground().toString(),
             * output, myParams.getCommandLanguage());
             */

        }
        catch (GUIException e) {
            e.printStackTrace();
        }
        myGUIController = controller;
        turtleList = new ArrayList<GUITurtle>();
        pathList = new ArrayList<SlogoPath>();
        mainRoot = root;
        mainStage = stage;
        myGUIConsole = new GUIConsole(myGUIController);
        myGUITurtleArea =
                new GUITurtleArea(mainStage, myParams.getDefaultBackground(), turtleList, pathList,
                                  myParams.getImageList(), myParams.getDefaultPalette(),
                                  myGUIController);
        myGUIHistory = new GUIHistory(myGUIConsole);
        myGUIPaletteBackground = new GUIPaletteBackground(myParams.getDefaultBackground(),
                                                          myGUITurtleArea);
        myGUIPen = new GUIPenDisplayContainer(turtleList, myGUITurtleArea);
        myGUIToolbar = new GUIToolbar(mainStage, turtleList, myGUITurtleArea,
                                      myGUIController, myParams.getCommandLanguage());
        myGUIVariables = new GUIVariableList(myGUIController);
        myUserDefinedCommands = new GUIUserDefinedCommands(myGUIConsole);
        myGUIImageDisplay = new GUIImageDisplay(myGUITurtleArea);
        myGUIPaletteDisplay = new GUIPaletteDisplay(myGUITurtleArea);
        myGUIAnimationSpeedDisplay = new GUIAnimationSpeedDisplay(myGUITurtleArea);
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#draw()
     */
    @Override
    public void draw () {
        mainRoot.setCenter(myGUITurtleArea.returnNodeToDraw());
        mainRoot.setLeft(new VBox(new Label(myResources.getString("history")), myGUIHistory.returnNodeToDraw(),
                                  new Label(myResources.getString("variables")),
                                  myGUIVariables.returnNodeToDraw(),
                                  new Label(myResources.getString("userdefined")),
                                  myUserDefinedCommands.returnNodeToDraw()));

        mainRoot.setRight(new VBox(new Label(myResources.getString("bgcolor")),
                                   myGUIPaletteBackground.returnNodeToDraw(),
                                   new Label(myResources.getString("pencolor")),
                                   myGUIPen.returnNodeToDraw(),
                                   myGUIImageDisplay.returnNodeToDraw(),
                                   myGUIPaletteDisplay.returnNodeToDraw(),
                                   myGUIAnimationSpeedDisplay.returnNodeToDraw()));
        mainRoot.setTop(myGUIToolbar.returnNodeToDraw());
        mainRoot.setBottom(myGUIConsole.returnNodeToDraw());
    }

    /**
     * Update turtle area.
     */
    public void updateTurtleArea () {
        myGUITurtleArea.drawAll();
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#showObserverVariables()
     */
    @Override
    public Observer showObserverVariables () {
        return myGUIVariables;
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#showTurtleArea()
     */
    @Override
    public Observer showTurtleArea () {
        return myGUITurtleArea;
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#showUserDefinedCommands()
     */
    @Override
    public Observer showUserDefinedCommands () {
        return myUserDefinedCommands;
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#showHistory()
     */
    @Override
    public UpdatableHistory showHistory () {
        return myGUIHistory;
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#showTurtlePen()
     */
    @Override
    public Observer showTurtlePen () {
        return myGUIPen;
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#updateGUINumber()
     */
    @Override
    public Object updateGUINumber () {
        myGUIToolbar.updateGUINumber();
        return null;
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#getPalette()
     */
    @Override
    public Map<Integer, String> getPalette () {
        return myGUITurtleArea.getColorMap();
    }

    /* (non-Javadoc)
     * @see gui.modelinterface.GUIInterface#updateBackgroundColor(int)
     */
    @Override
    public void updateBackgroundColor (int index) {
        myGUITurtleArea
                .updateBackgroundColor(Color.valueOf(myGUITurtleArea.getColorMap().get(index)));
    }
}
