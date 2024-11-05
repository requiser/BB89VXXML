package domBB89VX1105;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMModifyBB89VX_hallgato {

    public static void main(String[] args) {
        try {
            // Load the XML document
            File xmlFile = new File("BB89VX_1105/DomModifyBB89VX/src/domBB89VX1105/BB89VX_hallgato.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Find the hallgato element with id="01"
            NodeList hallgatoList = doc.getElementsByTagName("hallgato");
            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Element hallgato = (Element) hallgatoList.item(i);
                if (hallgato.getAttribute("id").equals("01")) {
                    // Modify the keresztnev and vezeteknev elements
                    hallgato.getElementsByTagName("keresztnev").item(0).setTextContent("John");
                    hallgato.getElementsByTagName("vezeteknev").item(0).setTextContent("Doe");
                    break;
                }
            }

            // Print the modified XML to the console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
