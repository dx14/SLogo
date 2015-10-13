package gui;

import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class GUIToolbar extends GUIComponent {

    private SLogoLanguage myLanguage;
    private List<GUITurtle> myTurtles;
    private ToolBar toolBar;

    public GUIToolbar (Stage stage, List<GUITurtle> turtles, GUITurtleAreaRedrawInterface turtleArea) {
        Button openImage = new Button("Open Image");
        myTurtles=turtles;
        openImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Image for Turtle");;
                fileChooser.getExtensionFilters().addAll(
                                                         new ExtensionFilter("Image Files", "*.png", "*.jpg", ".bmp", ".gif"));
                File selectedFile = fileChooser.showOpenDialog(stage);
                if (selectedFile!=null) {
                    try {
                        Image image = new Image("file:"+selectedFile.getAbsolutePath());
                        for (GUITurtle t: myTurtles) {
                            t.setImage(image);
                        }
                        turtleArea.drawAll();
                    }
                    catch (Exception e) {
                        System.out.println("file:"+selectedFile.getAbsolutePath());
                        System.out.println(e);
                    }
                }
            }
            
        });
        toolBar = new ToolBar(
                              new Button("New"),
                              new Button("Open"),
                              new Button("Save"),
                              new Separator(),
                              openImage,
                              new Button("Run"),
                              new Separator(),
                              new Button("Debug"),
                              new Button("Profile"));
    }

    @Override
    public Node returnNodeToDraw () {
        // TODO Auto-generated method stub
        return toolBar;
    }

    private void updateTurtleImage () {

    }

    private void updateLanguage () {

    }

    private void addHelpButton () {

    }

    private void addLanguageDropDown () {

    }

    private void addImageFileLoader () {

    }

}
