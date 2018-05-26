package xmlParser;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DomParserProductionLine {

	private NodeList usineList;
	private NodeList simulationList;
	private Document doc;
	
	public DomParserProductionLine(NodeList usineList, NodeList simulationList
			, Document doc) {
		
		this.usineList = usineList;
		this.simulationList = usineList;
		this.doc = doc;
	}
	
	public void buildAndGetDoc() {
		
	}
}
