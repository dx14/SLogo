package gui.toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import gui.GUIComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import parser.ParserException;


public class LanguageDropdown extends GUIComponent {
    private ComboBox<String> languageDropdown;
    private GUIToolbarInterface myTool;

    public LanguageDropdown (GUIToolbarInterface tool) {
        myTool = tool;
        setTextResources(ResourceBundle.getBundle("resources.guitext.LanguageDropdown"));
        String languagesFileDirectoryName = getTextResources().getString("languagesdirectory");
        languageDropdown = new ComboBox<String>();
        File folder = new File("./" + languagesFileDirectoryName);
        File[] listOfFiles = folder.listFiles();
        List<String> languagesList = new ArrayList<String>();
        for (File file : listOfFiles) {
            if (!file.getName().equals(getTextResources().getString("ignoredsyntaxfile"))) {
                languagesList.add(file.getName()
                        .split(getTextResources().getString("regexforlanguagename"))[0]);
            }
        }
        languageDropdown.getItems().addAll(languagesList);
        languageDropdown.setValue(myTool.getDefaultLanguage().getLanguage());
        updateLanguage(myTool.getDefaultLanguage().getLanguage());
        myTool.setCurrentLanguage(myTool.getDefaultLanguage().getLanguage());
        languageDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                updateLanguage(languageDropdown.getValue());
                tool.setCurrentLanguage(languageDropdown.getValue());
            }
        });
    }

    private void updateLanguage (String language) {
        try {
            myTool.getController().changeLanguage(language);
        }
        catch (ParserException e) {
            handleException(e);
        }
    }

    @Override
    public Node returnNodeToDraw () {
        return languageDropdown;
    }
}
