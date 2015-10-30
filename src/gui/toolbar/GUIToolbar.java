package gui.toolbar;

import java.util.List;
import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaRedrawInterface;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;


/**
 * The Class GUIToolbar extends GUIComponent and represents the whole toolbar.
 * Implements GUIToolbarInterface.
 * Toolbar elements can be added to this component.
 * @author John
 */
public class GUIToolbar extends GUIComponent implements GUIToolbarInterface {

    private List<GUITurtle> myTurtles;
    private ToolBar toolBar;
    private GUITurtleAreaRedrawInterface myTurtleArea;
    private Stage myStage;
    private GUIController myGUIController;
    private SLogoLanguage defaultLanguage;
    private SLogoLanguage currentLanguage;
    private GUIDropdownButton guiDropdown;

    /**
     * Instantiates a new GUI toolbar.
     *
     * @param stage the stage
     * @param turtles the turtles
     * @param turtleArea the turtle area
     * @param guiController the gui controller
     * @param defLang the default language
     */
    public GUIToolbar (Stage stage,
                       List<GUITurtle> turtles,
                       GUITurtleAreaRedrawInterface turtleArea,
                       GUIController guiController,
                       String defLang) {
        myTurtles = turtles;
        myTurtleArea = turtleArea;
        myStage = stage;
        myGUIController = guiController;
        setTextResources(ResourceBundle.getBundle("resources.guitext.Toolbar"));
        defaultLanguage = new SLogoLanguage();
        defaultLanguage.setLanguage(defLang);
        currentLanguage = new SLogoLanguage();
        toolBar = new ToolBar();
        initializeToolBar();
    }

    /**
     * Initialized the toolbar and its components.
     * Override this if you want to create a new toolbar setup.
     */
    protected void initializeToolBar () {
        guiDropdown = new GUIDropdownButton(myGUIController);
        addNode(new LanguageDropdown(this).returnNodeToDraw());
        addNode(new Separator());
        addNode(new ImageLoaderButton(this, myStage).returnNodeToDraw());
        addNode(new Separator());
        addNode(new HelpButton(myStage).returnNodeToDraw());
        addNode(new Separator());
        addNode(new AddGUIButton(myGUIController).returnNodeToDraw());
        addNode(new Separator());
        addNode(guiDropdown.returnNodeToDraw());
        addNode(new Separator());
        addNode(new SaveXMLButton(this).returnNodeToDraw());
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getPalette()
     */
    @Override
    public String getPalette () {
        return myTurtleArea.getPalette();
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getBGColor()
     */
    @Override
    public String getBGColor () {
        return myTurtleArea.getBGColor();
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getImageList()
     */
    @Override
    public String getImageList () {
        return myTurtleArea.getImagePathsAsAString();
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getCurrentLanguage()
     */
    @Override
    public SLogoLanguage getCurrentLanguage () {
        return currentLanguage;
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#setCurrentLanguage(java.lang.String)
     */
    @Override
    public void setCurrentLanguage (String lang) {
        currentLanguage.setLanguage(lang);
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getDefaultLanguage()
     */
    @Override
    public SLogoLanguage getDefaultLanguage () {
        return defaultLanguage;
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getController()
     */
    @Override
    public GUIController getController () {
        return myGUIController;
    }

    /**
     * Adds a control element (such as a Button, must inherit from Node) to the
     * end of the toolbar.
     *
     * @param node the node to add
     */
    public void addNode (Node node) {
        toolBar.getItems().add(node);
    }

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        return toolBar;
    }

    /**
     * Update gui workspace instance number.
     */
    public void updateGUINumber () {
        guiDropdown.updateGUINumber();
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getTurtles()
     */
    @Override
    public List<GUITurtle> getTurtles () {
        return myTurtles;
    }

    /* (non-Javadoc)
     * @see gui.toolbar.GUIToolbarInterface#getTurtleArea()
     */
    @Override
    public GUITurtleAreaRedrawInterface getTurtleArea () {
        return myTurtleArea;
    }
}
