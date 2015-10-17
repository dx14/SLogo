package gui;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import parser.ParserException;

public class GUIConsole extends GUIComponent {

    private GUIController myGUIController;
    private TextInputControl console;
    private HBox container;
    private Button submit;
    private final double CONSOLE_MAX_WIDTH = 400;

    public GUIConsole (GUIController guiController) {
        myGUIController = guiController;
        setTextResources(ResourceBundle.getBundle("resources.guitext.Console"));
        
        console = new TextArea();
        console.setPromptText(getTextResources().getString("prompttext"));
        console.setMaxWidth(CONSOLE_MAX_WIDTH);
        // console.setWrapText(true);

        submit = new Button(getTextResources().getString("submittext"));
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                String input = console.getText();
                //TODO: do we want any formatting or filtering here such as checking if string is blank?
                sendCommand(input);
                System.out.println(input);
                console.clear();
            }
        });
        
        container = new HBox();
        container.getChildren().add(console);
        container.getChildren().add(submit);
    }

    @Override
    public Node returnNodeToDraw () {
        return container;
    }

    public void sendCommand (String string) {
        try {
            myGUIController.runCommand(string);
        }
        catch (ParserException e) {
            handleException(e);
        }
    }

}
