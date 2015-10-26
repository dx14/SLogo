package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

	public XMLParser(File f) throws GUIException {
		myFile = f;
		myNode = readFile(myFile);
	}

	private Node readFile(File f) {
		try {
			File XmlFile = myFile;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("simulation");
			Node node = nList.item(0);
			System.out.println(node == null);
			return node;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public GUIParameter getParameters() throws GUIException {
		
		myElement = (Element) myNode;
		
		return new GUIParameter(myElement.getElementsByTagName("color").item(0).getTextContent(),
				myElement.getElementsByTagName("imageList").item(0).getTextContent(),
				myElement.getElementsByTagName("commandLanguage").item(0).getTextContent());

	

	}
	
	public void saveToXML(String xml, String color, String listImages, String commandLang) {
	    Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("simulation");

	        // create data elements and place them under root
	        e = dom.createElement("color");
	        System.out.println(color);
	        e.appendChild(dom.createTextNode(color));
	        rootEle.appendChild(e);

	        e = dom.createElement("imageList");
	        System.out.println(listImages);
	        e.appendChild(dom.createTextNode(listImages));
	        rootEle.appendChild(e);

	        e = dom.createElement("commandLanguage");
	        System.out.println(commandLang);
	        e.appendChild(dom.createTextNode(commandLang));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	          //  tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(xml)));

	        
	    } catch (Exception pce) {
	    	pce.printStackTrace();
	    }
	}
	    catch (Exception pce) {
	    	pce.printStackTrace();

	    }
	}
	

}
