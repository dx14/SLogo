package gui.toolbar;

import java.util.ResourceBundle;
import gui.GUIComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class HelpButton extends GUIComponent{
    Button help;
    public HelpButton (Stage stage) {
        setTextResources(ResourceBundle.getBundle("resources.guitext.HelpButton"));
        help = new Button(getTextResources().getString("help"));
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                displayHelp(stage);
            }
        });
    }
    private void displayHelp (Stage stage) {
        Popup popup = new Popup();
        popup.setX(stage.getX());
        popup.setY(stage.getY());
        Button hide = new Button(getTextResources().getString("hide"));
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                popup.hide();
            }
        });
        WebView html = new WebView();
        html.getEngine().load(getTextResources().getString("helpurl"));
        popup.getContent().addAll(html);
        popup.getContent().addAll(hide);
        popup.show(stage);
    }
    @Override
    public Node returnNodeToDraw () {
        return help;
    }
}
