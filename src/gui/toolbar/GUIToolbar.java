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


public class GUIToolbar extends GUIComponent implements GUIToolbarInterface {

    private List<GUITurtle> myTurtles;
    private ToolBar toolBar;
    private GUITurtleAreaRedrawInterface myTurtleArea;
    private Stage myStage;
    private GUIController myGUIController;
    private SLogoLanguage defaultLanguage;
    private SLogoLanguage currentLanguage;
    private GUIDropdownButton guiDropdown;

    public GUIToolbar (Stage stage,
                       List<GUITurtle> turtles,
                       GUITurtleAreaRedrawInterface turtleArea,
                       GUIController GUIController,
                       String defLang) {
        myTurtles = turtles;
        myTurtleArea = turtleArea;
        myStage = stage;
        myGUIController = GUIController;
        setTextResources(ResourceBundle.getBundle("resources.guitext.Toolbar"));
        defaultLanguage = new SLogoLanguage();
        defaultLanguage.setLanguage(defLang);
        currentLanguage = new SLogoLanguage();
        toolBar = new ToolBar();
        initializeToolBar();
    }

    /**
     * Override this if you want to create a new toolbar setup
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

    @Override
    public String getPalette () {
        return myTurtleArea.getPalette();
    }

    @Override
    public String getBGColor () {
        return myTurtleArea.getBGColor();
    }

    @Override
    public String getImageList () {
        return myTurtleArea.getImagePathsAsAString();
    }

    @Override
    public SLogoLanguage getCurrentLanguage () {
        return currentLanguage;
    }

    @Override
    public void setCurrentLanguage (String lang) {
        currentLanguage.setLanguage(lang);
    }

    @Override
    public SLogoLanguage getDefaultLanguage () {
        return defaultLanguage;
    }

    @Override
    public GUIController getController () {
        return myGUIController;
    }

    /**
     * Adds a control element (such as a Button, must inherit from Node) to the
     * end of the toolbar
     *
     * @param the
     *        Control element
     */
    public void addNode (Node c) {
        toolBar.getItems().add(c);
    }

    @Override
    public Node returnNodeToDraw () {
        return toolBar;
    }

    public void updateGUINumber () {
        guiDropdown.updateGUINumber();
    }

    @Override
    public List<GUITurtle> getTurtles () {
        return myTurtles;
    }

    @Override
    public GUITurtleAreaRedrawInterface getTurtleArea () {
        return myTurtleArea;
    }
}
