package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import controller.XMLParserController;

public class XMLParser {
	private File myFile;
	private Node myNode;
	private Element myElement;

	public XMLParser(File f) throws GUIException
	{
		myFile = f;
		myNode = readFile(myFile);
		getParameters();
	}
	
	
	private Node readFile(File f)
	{
		try 
		{
			File XmlFile = myFile;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
					
			doc.getDocumentElement().normalize();		
			NodeList nList = doc.getElementsByTagName("simulation");
			Node node = nList.item(0);
			return node;
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		return null;
	}
	

	private void getParameters() throws GUIException{
		
		myElement = (Element) myNode;
		myElement.getElementsByTagName("simulationName").item(0).getTextContent();
				
	}
		

		
	

}

