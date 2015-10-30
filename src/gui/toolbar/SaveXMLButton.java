package gui.toolbar;

import java.io.File;
import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.GUIException;
import gui.xml.XMLParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class SaveXMLButton extends GUIComponent {
    private GUIToolbarInterface myTool;
    private Button save;

    public SaveXMLButton (GUIToolbarInterface tool) {
        myTool = tool;
        setTextResources(ResourceBundle.getBundle("resources.guitext.SaveXMLButton"));
        save = new Button(getTextResources().getString("savexml"));
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                try {
                    XMLParser parser =
                            new XMLParser(new File(getTextResources().getString("defaultxml")));
                    String imageList = myTool.getImageList();
                    String backgroundColor = myTool.getBGColor();
                    String defaultPalette = myTool.getPalette();
                    parser.saveToXML(getTextResources().getString("defaultxml"), backgroundColor,
                                imageList, myTool.getCurrentLanguage().getLanguage(),
                                defaultPalette);
                }
                catch (GUIException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Node returnNodeToDraw () {
        return save;
    }
}
