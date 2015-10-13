package gui;

import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class GUIToolbar extends GUIComponent {

    private SLogoLanguage myLanguage;
    private List<GUITurtle> myTurtles;
    private ToolBar toolBar;
    private GUITurtleAreaRedrawInterface myTurtleArea;
    private Stage myStage;

    public GUIToolbar (Stage stage, List<GUITurtle> turtles, GUITurtleAreaRedrawInterface turtleArea) {
        myTurtles=turtles;
        myTurtleArea=turtleArea;
        myStage=stage;
        
        toolBar = new ToolBar(
                              new Button("Language"),
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
        Button help = new Button("Help");
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                Popup popup = new Popup();
                popup.setX(myStage.getX());
                popup.setY(myStage.getY());
                Button hide = new Button("Hide");
                hide.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        popup.hide();
                    }
                });
                WebView html = new WebView();
                html.getEngine().load("http://www.cs.duke.edu/courses/compsci308/fall15/assign/03_slogo/commands.php");
                popup.getContent().addAll(html);
                popup.getContent().addAll(hide);
                popup.show(myStage);
            }
        });
        return help;
    }

    private void addLanguageDropDown () {

    }

    private Button addImageFileLoader () {
        Button openImage = new Button("Open Image");
        openImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Image for Turtle");;
                fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", ".bmp", ".gif"));
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
                        //TODO: error handling
                        System.out.println("file:"+selectedFile.getAbsolutePath());
                        System.out.println(e);
                    }
                }
            }
        });
        return openImage;
    }
}
