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
	
	private void run(StackPane root) {

		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);
		border.setLeft(addVBox());
		addStackPane(hbox);         // Add stack to HBox in top region

		root.getChildren().add(border);
		
		//border.setCenter(addGridPane());
		//border.setRight(addFlowPane());
	}

	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonCurrent = new Button("Current");
	    buttonCurrent.setPrefSize(100, 20);

	    Button buttonProjected = new Button("Projected");
	    buttonProjected.setPrefSize(100, 20);
	    hbox.getChildren().addAll(buttonCurrent, buttonProjected);

	    return hbox;
	}
	
	public VBox addVBox() {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text("Data");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Hyperlink options[] = new Hyperlink[] {
	        new Hyperlink("Sales"),
	        new Hyperlink("Marketing"),
	        new Hyperlink("Distribution"),
	        new Hyperlink("Costs")};

	    for (int i=0; i<4; i++) {
	        VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
	        vbox.getChildren().add(options[i]);
	    }

	    return vbox;
	}
	

	public void addStackPane(HBox hb) {
	    StackPane stack = new StackPane();
	    Rectangle helpIcon = new Rectangle(30.0, 25.0);
	    helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	        new Stop[]{
	        new Stop(0,Color.web("#4977A3")),
	        new Stop(0.5, Color.web("#B0C6DA")),
	        new Stop(1,Color.web("#9CB6CF")),}));
	    helpIcon.setStroke(Color.web("#D0E6FA"));
	    helpIcon.setArcHeight(3.5);
	    helpIcon.setArcWidth(3.5);

	    Text helpText = new Text("?");
	    helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	    helpText.setFill(Color.WHITE);
	    helpText.setStroke(Color.web("#7080A0")); 

	    stack.getChildren().addAll(helpIcon, helpText);
	    stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
	    StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"

	    hb.getChildren().add(stack);            // Add to HBox from Example 1-2
	    HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	
		
		 Button btn = new Button();
	        btn.setText("Say 'Hello World'");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });
	        
	        StackPane root = new StackPane();
	        root.getChildren().add(btn);
	    	run(root);
	 Scene scene = new Scene(root, 300, 250);

	        primaryStage.setTitle("Hello World!");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		
		
	}
	
	
	
	
}
