package gui.console;

import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import parser.ParserException;


/**
 * The Class GUIConsole represents the text box where the user types in LOGO commands.
 * @author John
 */
public class GUIConsole extends GUIComponent implements GUIConsoleTextEditable {

    private GUIController myGUIController;
    private TextInputControl console;
    private HBox container;
    private Button submit;
    private final double consoleMaxWidth;

    /**
     * Instantiates a new GUI console.
     *
     * @param guiController the GUI controller
     */
    public GUIConsole (GUIController guiController) {
        myGUIController = guiController;
        setTextResources(ResourceBundle.getBundle("resources.guitext.Console"));
        console = new TextArea();
        console.setPromptText(getTextResources().getString("prompttext"));
        consoleMaxWidth = Double.parseDouble(getTextResources().getString("maxwidth"));
        console.setMaxWidth(consoleMaxWidth);
        submit = new Button(getTextResources().getString("submittext"));
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                String input = console.getText();
                sendCommand(input);
                console.clear();
            }
        });
        container = new HBox();
        container.getChildren().add(console);
        container.getChildren().add(submit);
    }

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        return container;
    }

    /**
     * Sends a command through the controller to the backend which may throw an error.
     *
     * @param string the command
     */
    public void sendCommand (String string) {
        try {
            myGUIController.runCommand(string);
        }
        catch (ParserException e) {
            handleException(e);
        }
    }

    /* (non-Javadoc)
     * @see gui.console.GUIConsoleTextEditable#changeText(java.lang.String)
     */
    @Override
    public void changeText (String input) {
        console.setText(input);
    }

}
