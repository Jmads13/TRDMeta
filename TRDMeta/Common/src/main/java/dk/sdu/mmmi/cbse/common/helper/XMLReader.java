/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Pasoa
 */
public class XMLReader {
    
    public static List<Element> readXML(String s){
        List<Element> elements = new ArrayList<>();
        try {
            elements.clear();

            File file = new File(s);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("staff");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        elements.add(eElement);
//			System.out.println("Staff id : " + eElement.getAttribute("id"));
//			System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//			System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//			System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
        
    }
        return elements;
  }
}

//Example of XML
//<company>
//	<staff id="1001">
//		<firstname>yong</firstname>
//		<lastname>mook kim</lastname>
//		<nickname>mkyong</nickname>
//		<salary>100000</salary>
//	</staff>
//	<staff id="2001">
//		<firstname>low</firstname>
//		<lastname>yin fong</lastname>
//		<nickname>fong fong</nickname>
//		<salary>200000</salary>
//	</staff>
//</company>