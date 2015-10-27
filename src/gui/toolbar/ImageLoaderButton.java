package gui.toolbar;

import java.io.File;
import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUITurtle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class ImageLoaderButton extends GUIComponent {
    Button openImage;
    GUIToolbarInterface myTool;

    public ImageLoaderButton (GUIToolbarInterface tool, Stage stage) {
        myTool = tool;
        setTextResources(ResourceBundle.getBundle("resources.guitext.ImageLoaderButton"));
        openImage = new Button(getTextResources().getString("openimage"));
        openImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                updateTurtleImage(stage);
            }
        });
    }

    private void updateTurtleImage (Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(getTextResources().getString("imagebrowsertitle"));
        fileChooser.getExtensionFilters()
                .addAll(new ExtensionFilter(getTextResources().getString("imageextensionlabel"),
                                            getTextResources()
                                                    .getString("imageextensions")
                                                    .split(getTextResources()
                                                            .getString("imageextensiondelimiter"))));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                myTool.getTurtleArea().addImage(selectedFile.getAbsolutePath());
                for (GUITurtle t : myTool.getTurtles()) {
                    t.setDisplayIndex(myTool.getTurtleArea().getImagesSize() - 1);
                }
                myTool.getTurtleArea().drawAll();
            }
            catch (Exception e) {
                handleException(e);
            }
        }
    }

    @Override
    public Node returnNodeToDraw () {
        return openImage;
    }
}
