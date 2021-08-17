import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;



  DocumentBuilderFactory dbfaFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = dbfaFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse("E:\\Projects\\xml\\xml\\src\\main\\resources\\xml_val.xml");
		
		
		System.out.println(doc.getElementsByTagName("date_added").item(1).getTextContent());


