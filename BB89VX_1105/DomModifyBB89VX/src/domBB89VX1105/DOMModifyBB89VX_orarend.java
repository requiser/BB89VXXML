package domBB89VX1105;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMModifyBB89VX_orarend {

    public static void main(String[] args) {
        try {
            // Load the XML document
            File xmlFile = new File("BB89VX_1105/DomModifyBB89VX/src/domBB89VX1105/BB89VX_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Add an "oraado" element where it's missing
            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);

                // Check if "oraado" element is missing
                if (ora.getElementsByTagName("oraado").getLength() == 0) {
                    Element oraado = doc.createElement("oraado");
                    oraado.setTextContent("Dr. Example Instructor");  // Adding sample content
                    ora.appendChild(oraado);
                }

                // Change "tipus" attribute from "gyakorlat" to "eloadas"
                if ("gyakorlat".equals(ora.getAttribute("tipus"))) {
                    ora.setAttribute("tipus", "eloadas");
                }
            }

            // Output the modified XML to the console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            // Print to console
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            // Write the modified XML to a file
            StreamResult fileResult = new StreamResult(new File("BB89VX_1105/DomModifyBB89VX/src/domBB89VX1105/orarendModifyNeptunkod.xml"));
            transformer.transform(source, fileResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
