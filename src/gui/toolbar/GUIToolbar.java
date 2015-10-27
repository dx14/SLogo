package gui.toolbar;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import gui.GUIComponent;
import gui.GUIException;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaRedrawInterface;
import gui.xml.XMLParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import parser.ParserException;


public class GUIToolbar extends GUIComponent {

    private final String languagesFileDirectoryName;
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
        languagesFileDirectoryName = getTextResources().getString("languagesdirectory");
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
        addNode(languageDropDown());
        addNode(new Separator());
        addNode(imageFileLoader());
        addNode(new Separator());
        addNode(new HelpButton(myStage).returnNodeToDraw());
        addNode(new Separator());
        addNode(addGUIButton());
        addNode(new Separator());
        addNode(guiDropDown());
        addNode(new Separator());
        addNode(new SaveXMLButton((GUIToolbarInterface)this).returnNodeToDraw());
    }

    public String getPalette () {
        return myTurtleArea.getPalette();
    }
    public String getBGColor () {
        return myTurtleArea.getBGColor();
    }
    public String getImageList () {
        return myTurtleArea.getImagePathsAsAString();
    }
    public String getLanguage () {
        return currentLanguage.getLanguage();
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



    private ComboBox<String> languageDropDown () {
        ComboBox<String> languageDropdown = new ComboBox<String>();
        File folder = new File("./" + languagesFileDirectoryName);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> languagesList = new ArrayList<String>();
        for (File file : listOfFiles) {
            if (!file.getName().equals(getTextResources().getString("ignoredsyntaxfile"))) {
                languagesList.add(file.getName()
                        .split(getTextResources().getString("regexforlanguagename"))[0]);
            }
        }
        languageDropdown.getItems().addAll(languagesList);

        languageDropdown.setValue(defaultLanguage.getLanguage());
        updateLanguage(defaultLanguage.getLanguage());

        currentLanguage = defaultLanguage;

        languageDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                updateLanguage(languageDropdown.getValue());
                currentLanguage.setLanguage(languageDropdown.getValue());
            }
        });
        return languageDropdown;
    }

    private void updateLanguage (String language) {
        try {
            myGUIController.changeLanguage(language);
        }
        catch (ParserException e) {
            handleException(e);
        }
    }

    private Button imageFileLoader () {
        Button openImage = new Button(getTextResources().getString("openimage"));
        openImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                updateTurtleImage();
            }
        });
        return openImage;
    }

    private void updateTurtleImage () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(getTextResources().getString("imagebrowsertitle"));
        fileChooser.getExtensionFilters()
                .addAll(new ExtensionFilter(getTextResources().getString("imageextensionlabel"),
                                            getTextResources()
                                                    .getString("imageextensions")
                                                    .split(getTextResources()
                                                            .getString("imageextensiondelimiter"))));
        File selectedFile = fileChooser.showOpenDialog(myStage);
        if (selectedFile != null) {
            try {
                myTurtleArea.addImage(selectedFile.getAbsolutePath());
                for (GUITurtle t : myTurtles) {
                    t.setDisplayIndex(myTurtleArea.getImagesSize() - 1);
                }

                myTurtleArea.drawAll();
            }
            catch (Exception e) {
                e.printStackTrace();
                handleException(e);
            }
        }
    }

    public void updateGUINumber () {

        guiDropdown.getItems().clear();
        for (int i = 0; i < myGUIController.getNumberOfGUIs(); i++) {
            guiDropdown.getItems().add(i + 1);
        }

    }
}
