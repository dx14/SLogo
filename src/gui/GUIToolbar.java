package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import parser.ParserException;


public class GUIToolbar extends GUIComponent {

    private final String languagesFileDirectoryName;
    private SLogoLanguage myLanguage;
    private List<GUITurtle> myTurtles;
    private ToolBar toolBar;
    private GUITurtleAreaRedrawInterface myTurtleArea;
    private Stage myStage;
    private GUIController myGUIController;

    public GUIToolbar (Stage stage, List<GUITurtle> turtles, GUITurtleAreaRedrawInterface turtleArea, GUIController GUIController) {
        myTurtles=turtles;
        myTurtleArea=turtleArea;
        myStage=stage;
        myGUIController=GUIController;
        setTextResources(ResourceBundle.getBundle("resources.guitext.Toolbar"));
        languagesFileDirectoryName = getTextResources().getString("languagesdirectory");
        
        toolBar = new ToolBar(
                              addLanguageDropDown(),
                              new Separator(),
                              addImageFileLoader(),
                              new Separator(),
                              addHelpButton());
    }

    @Override
    public Node returnNodeToDraw () {
        return toolBar;
    }

    private void updateTurtleImage () {

    }

    private void updateLanguage () {
        
    }

    private Button addHelpButton () {
        Button help = new Button(getTextResources().getString("help"));
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                Popup popup = new Popup();
                popup.setX(myStage.getX());
                popup.setY(myStage.getY());
                Button hide = new Button(getTextResources().getString("hide"));
                hide.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        popup.hide();
                    }
                });
                WebView html = new WebView();
                html.getEngine().load(getTextResources().getString("helpurl"));
                popup.getContent().addAll(html);
                popup.getContent().addAll(hide);
                popup.show(myStage);
            }
        });
        return help;
    }

    private ComboBox<String> addLanguageDropDown () {
        ComboBox<String> languageDropdown = new ComboBox<String>();
        // get list of XML files from local eclipse directory
        File folder = new File("./" + languagesFileDirectoryName);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> languagesList = new ArrayList<String>();
        for (File file : listOfFiles) {
            if (!file.getName().equals(getTextResources().getString("ignoredsyntaxfile"))) {
                languagesList.add(file.getName().split(getTextResources().getString("regexforlanguagename"))[0]);
            }
        }
        languageDropdown.getItems().addAll(languagesList);
        languageDropdown.setValue(getTextResources().getString("defaultlanguage"));
        languageDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println(languageDropdown.getValue());
                try {
                    myGUIController.changeLanguage(languageDropdown.getValue());
                }
                catch (ParserException e) {
                    handleException(e);
                }
            }
        });
        return languageDropdown;
    }

    private Button addImageFileLoader () {
        Button openImage = new Button(getTextResources().getString("openimage"));
        openImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle(getTextResources().getString("imagebrowsertitle"));
                fileChooser.getExtensionFilters().addAll(new ExtensionFilter(getTextResources().getString("imageextensionlabel"), 
                                                                             getTextResources().getString("imageextensions").split(getTextResources().getString("imageextensiondelimiter"))));
                File selectedFile = fileChooser.showOpenDialog(myStage);
                if (selectedFile!=null) {
                    try {
                        Image image = new Image("file:"+selectedFile.getAbsolutePath());
                        for (GUITurtle t: myTurtles) {
                            t.setImage(image);
                        }
                        myTurtleArea.drawAll();
                    }
                    catch (Exception e) {
                        handleException(e);
                    }
                }
            }
        });
        return openImage;
    }
}
