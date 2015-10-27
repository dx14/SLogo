package gui.toolbar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import gui.GUIComponent;
import gui.GUIException;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaRedrawInterface;
import gui.xml.XMLParser;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import parser.ParserException;

public class GUIToolbar extends GUIComponent {

	private final String languagesFileDirectoryName;
	private SLogoLanguage myLanguage;
	private List<GUITurtle> myTurtles;
	private ToolBar toolBar;
	private GUITurtleAreaRedrawInterface myTurtleArea;
	private Stage myStage;
	private GUIController myGUIController;
	private ComboBox<Integer> guiDropdown;
	private Scanner scanner;
	private PrintWriter out;
	
	
	private String defaultLanguage;
	private String currentLanguage;
	
	private int numberOfGUIs;

	public GUIToolbar(Stage stage, List<GUITurtle> turtles, GUITurtleAreaRedrawInterface turtleArea,
			GUIController GUIController, String defLang) {
		myTurtles = turtles;
		myTurtleArea = turtleArea;
		myStage = stage;
		myGUIController = GUIController;
		setTextResources(ResourceBundle.getBundle("resources.guitext.Toolbar"));
		languagesFileDirectoryName = getTextResources().getString("languagesdirectory");

		defaultLanguage = defLang;
		toolBar = new ToolBar();
		initializeToolBar();
	}

	/**
	 * Override this if you want to create a new toolbar setup
	 */
	protected void initializeToolBar() {
		toolBar.getItems().add(languageDropDown());
		toolBar.getItems().add(new Separator());
		toolBar.getItems().add(imageFileLoader());
		toolBar.getItems().add(new Separator());
		toolBar.getItems().add(helpButton());
		toolBar.getItems().add(new Separator());
		toolBar.getItems().add(addGUIButton());
		toolBar.getItems().add(new Separator());
		toolBar.getItems().add(guiDropDown());
		toolBar.getItems().add(new Separator());
		toolBar.getItems().add(saveXML());
	}

	private Node saveXML() {
		// TODO Auto-generated method stub
		Button help = new Button(getTextResources().getString("savexml"));
		help.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					XMLParser a = new XMLParser(new File("src/resources/default.xml"));
					
					String imageList = getImageList();
					String backgroundColor = getBGColor();
					String defaultPalette = getPalette();
					
					a.saveToXML("src/resources/default.xml", backgroundColor,
							imageList, currentLanguage, defaultPalette); 
					
					
				} catch (GUIException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		return help;
		
			}
	
	protected String getPalette() {
		// TODO Auto-generated method stub
		return myTurtleArea.getPalette();
	}

	private String getBGColor() {
		
		return myTurtleArea.getBGColor();
		// TODO Auto-generated method stub
	}

	private String getImageList() {
		// TODO Auto-generated method stub
		return myTurtleArea.getImagePathsAsAString();
	}

	/**
	 * Adds a control element (such as a Button, must inherit from Node) to the
	 * end of the toolbar
	 * 
	 * @param the
	 *            Control element
	 */
	public void addControlElement(Control c) {
		toolBar.getItems().add(c);
	}

	@Override
	public Node returnNodeToDraw() {

		toolBar.getItems().clear();
		initializeToolBar();

		return toolBar;
	}

	private Button helpButton() {
		Button help = new Button(getTextResources().getString("help"));
		help.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				displayHelp();
			}
		});
		return help;
	}

	private Button addGUIButton() {
		Button help = new Button(getTextResources().getString("addGUI"));
		help.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				myGUIController.addGUI();
			}
		});
		return help;
	}

	private ComboBox<Integer> guiDropDown() {

		guiDropdown = new ComboBox<Integer>();

		for (int i = 0; i < myGUIController.getNumberOfGUIs(); i++) {
			guiDropdown.getItems().add(i + 1);
		}

		guiDropdown.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myGUIController.changeGUI(guiDropdown.getValue() - 1); // to
																		// align
																		// indexing
			}
		});

		return guiDropdown;
	}

	private void displayHelp() {
		Popup popup = new Popup();
		popup.setX(myStage.getX());
		popup.setY(myStage.getY());
		Button hide = new Button(getTextResources().getString("hide"));
		hide.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.hide();
			}
		});
		WebView html = new WebView();
		html.getEngine().load(getTextResources().getString("helpurl"));
		popup.getContent().addAll(html);
		popup.getContent().addAll(hide);
		popup.show(myStage);
	}

	private ComboBox<String> languageDropDown() {
		ComboBox<String> languageDropdown = new ComboBox<String>();
		File folder = new File("./" + languagesFileDirectoryName);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> languagesList = new ArrayList<String>();
		for (File file : listOfFiles) {
			if (!file.getName().equals(getTextResources().getString("ignoredsyntaxfile"))) {
				languagesList.add(file.getName().split(getTextResources().getString("regexforlanguagename"))[0]);
			}
		}
		languageDropdown.getItems().addAll(languagesList);

		languageDropdown.setValue(defaultLanguage);
		updateLanguage(defaultLanguage);
		
		currentLanguage = defaultLanguage;
		
		languageDropdown.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				updateLanguage(languageDropdown.getValue());
				currentLanguage = languageDropdown.getValue();
			}
		});
		return languageDropdown;
	}

	private void updateLanguage(String language) {
		try {
			myGUIController.changeLanguage(language);
		} catch (ParserException e) {
			handleException(e);
		}
	}

	private Button imageFileLoader() {
		Button openImage = new Button(getTextResources().getString("openimage"));
		openImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				updateTurtleImage();
			}
		});
		return openImage;
	}

	private void updateTurtleImage() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(getTextResources().getString("imagebrowsertitle"));
		fileChooser.getExtensionFilters()
				.addAll(new ExtensionFilter(getTextResources().getString("imageextensionlabel"), getTextResources()
						.getString("imageextensions").split(getTextResources().getString("imageextensiondelimiter"))));
		File selectedFile = fileChooser.showOpenDialog(myStage);
		if (selectedFile != null) {
			try {
				myTurtleArea.addImage(selectedFile.getAbsolutePath());
				for (GUITurtle t : myTurtles) {
					t.setDisplayIndex(myTurtleArea.getImagesSize() - 1);
				}

				myTurtleArea.drawAll();
			} catch (Exception e) {
			    e.printStackTrace();
				handleException(e);
			}
		}
	}

	public void updateGUINumber() {

		guiDropdown.getItems().clear();
		for (int i = 0; i < myGUIController.getNumberOfGUIs(); i++) {
			guiDropdown.getItems().add(i + 1);
		}

	}
}
