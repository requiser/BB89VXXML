package domBB89VX1022;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class DOMRead {

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
                    String vezeteknev = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    String foglalkozas = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();

                    System.out.println("Keresztnév: " + keresztnev);
                    System.out.println("Vezetéknév: " + vezeteknev);
                    System.out.println("Foglalkozás: " + foglalkozas);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}