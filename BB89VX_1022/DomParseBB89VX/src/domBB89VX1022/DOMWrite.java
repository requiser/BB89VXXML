package domBB89VX1022;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWrite {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("BB89VX_1022/DomParseBB89VX/src/domBB89VX1022/BB89VX_hallgato.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    String id = elem.getAttribute("id");
                    System.out.println("Hallgató ID: " + id);

                    String keresztnev = elem.getElementsByTagName("keresztnev").item(0).getTextContent();
                    System.out.println("Keresztnév: " + keresztnev);

                    String vezeteknev = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    System.out.println("Vezetéknév: " + vezeteknev);

                    String foglalkozas = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();
                    System.out.println("Foglalkozás: " + foglalkozas);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.setOutputProperty("{http://xml.apache.org/xalan}line-separator", "\n");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");

            StreamResult result = new StreamResult(new File("BB89VX_1022/DomParseBB89VX/src/domBB89VX1022/hallgato1BB89VX.xml"));
            transformer.transform(source, result);

            System.out.println("Az új XML fájl sikeresen létrehozva: hallgato1BB89VX.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
