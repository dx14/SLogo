package gui.statedisplay;

import java.util.Optional;
import gui.GUIComponent;
import gui.turtlearea.GUITADrawerSpeedInterface;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GUIAnimationSpeedDisplay extends GUIComponent{
    private GUITADrawerSpeedInterface myDrawer;
    private Text text;
    public GUIAnimationSpeedDisplay(GUITADrawerSpeedInterface drawer) {
        myDrawer=drawer;
    }
    @Override
    public Node returnNodeToDraw () {
        text = new Text(""+myDrawer.getSpeed());
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setContentText("Enter animation time (milliseconds):");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    try {
                        double speed = Double.parseDouble(result.get());
                        myDrawer.setSpeed(speed);
                        redraw();
                    }
                    catch (Exception e) {
                        handleException(e);
                    }
                }
            }
        });
        return text;
    }
    private void redraw() {
        text.setText(""+myDrawer.getSpeed());;
    }
}
