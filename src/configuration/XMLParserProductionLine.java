package configuration;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParserProductionLine {

	
	private NodeList usineList;
	private NodeList pathList;
	private NodeList usineAttributeList;
	private Document doc;

	public XMLParserProductionLine(File xmlFiles) {

		buildAndGetDoc(xmlFiles);
		setList();
		/*this.usineList = usineList;
		this.simulationList = usineList;
		this.doc = doc;*/
	}

	private void buildAndGetDoc(File xmlFiles) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {

			db = dbf.newDocumentBuilder();
			this.doc = db.parse(xmlFiles);

		} catch (ParserConfigurationException e) {

			System.err.println("The XML isn't well structured");
			
		} catch (SAXException e) {

			System.err.println("The XML isn't well structured");
		} catch (IOException e) {

			System.err.println("The XML isn't well structured");
		}


		this.doc.getDocumentElement().normalize();
		System.out.println(this.doc.getDocumentElement().getNodeName());
	}
	
	private void setList() {
		
		Element metaElement = (Element) doc.getElementsByTagName("metadonnees").item(0);
		Element simuElement = (Element) doc.getElementsByTagName("simulation").item(0);
		
		this.usineList = metaElement.getElementsByTagName("usine");
		this.usineAttributeList = simuElement.getElementsByTagName("usine");
		this.pathList = simuElement.getElementsByTagName("chemin");
		
	}

	public NodeList getUsineList() {
		return usineList;
	}

	public NodeList getPathList() {
		return pathList;
	}

	public NodeList getUsineAttributeList() {
		return usineAttributeList;
	}

}
