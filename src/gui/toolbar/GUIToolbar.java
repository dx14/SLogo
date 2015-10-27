package gui.toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaRedrawInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import parser.ParserException;


public class GUIToolbar extends GUIComponent implements GUIToolbarInterface {

    private List<GUITurtle> myTurtles;
    private ToolBar toolBar;
    private GUITurtleAreaRedrawInterface myTurtleArea;
    private Stage myStage;
    private GUIController myGUIController;
    private ComboBox<Integer> guiDropdown;
    private SLogoLanguage defaultLanguage;
    private SLogoLanguage currentLanguage;
    private int numberOfGUIs;

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
        addNode(new LanguageDropdown((GUIToolbarInterface)this).returnNodeToDraw());
        addNode(new Separator());
        addNode(new ImageLoaderButton((GUIToolbarInterface)this, myStage).returnNodeToDraw());
        addNode(new Separator());
        addNode(new HelpButton(myStage).returnNodeToDraw());
        addNode(new Separator());
        addNode(addGUIButton());
        addNode(new Separator());
        addNode(guiDropDown());
        addNode(new Separator());
        addNode(new SaveXMLButton((GUIToolbarInterface)this).returnNodeToDraw());
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

    private Button addGUIButton () {
        Button help = new Button(getTextResources().getString("addGUI"));
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myGUIController.addGUI();
            }
        });
        return help;
    }

    private ComboBox<Integer> guiDropDown () {
        guiDropdown = new ComboBox<Integer>();
        for (int i = 0; i < myGUIController.getNumberOfGUIs(); i++) {
            guiDropdown.getItems().add(i + 1);
        }
        guiDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myGUIController.changeGUI(guiDropdown.getValue() - 1); // to
                // align
                // indexing
            }
        });
        return guiDropdown;
    }

    public void updateGUINumber () {
        guiDropdown.getItems().clear();
        for (int i = 0; i < myGUIController.getNumberOfGUIs(); i++) {
            guiDropdown.getItems().add(i + 1);
        }
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
