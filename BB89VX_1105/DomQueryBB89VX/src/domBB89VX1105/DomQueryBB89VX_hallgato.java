package domBB89VX1105;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomQueryBB89VX_hallgato {

    public static void main(String[] args) {
        try {
            // Load the XML document
            File xmlFile = new File("BB89VX_1105/DomQueryBB89VX/src/domBB89VX1105/BB89VX_hallgato.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Retrieve all "hallgato" elements
            NodeList hallgatoList = doc.getElementsByTagName("hallgato");

            // Loop through each "hallgato" element and print the "vezeteknev" content
            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Element hallgato = (Element) hallgatoList.item(i);
                String vezeteknev = hallgato.getElementsByTagName("vezeteknev").item(0).getTextContent();
                System.out.println("Vezetéknév: " + vezeteknev);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
