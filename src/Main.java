import java.util.Collections;
import java.util.LinkedList;
import controller.SlogoController;
import gui.GUIController;
import gui.MainGUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args){
		
		
		launch();

		
	
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	
		SlogoController controller = new SlogoController();
		
	        BorderPane root = new BorderPane();
	    	//run(root);

	    	MainGUI myGui = new MainGUI(root, (GUIController)controller);
	    	myGui.draw();
	    	
	    	
	    	
	    	Scene scene = new Scene(root, 600, 600);

	        primaryStage.setTitle("Hello World!");
	        primaryStage.setScene(scene);
	        primaryStage.show(); 
	        
	        
		
		
		
		
		
	}
	
	
	
	
}
