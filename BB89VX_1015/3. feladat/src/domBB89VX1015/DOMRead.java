package domBB89VX1015;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMRead {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("BB89VX_orarend.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList oraList = doc.getElementsByTagName("ora");

            for (int i = 0; i < oraList.getLength(); i++) {
                Node oraNode = oraList.item(i);

                if (oraNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElement = (Element) oraNode;

                    System.out.println("\nOra ID: " + oraElement.getAttribute("id"));
                    System.out.println("Tipus: " + oraElement.getAttribute("tipus"));
                    System.out.println("Targy: " + oraElement.getElementsByTagName("targy").item(0).getTextContent());

                    Element idopont = (Element) oraElement.getElementsByTagName("idopont").item(0);
                    System.out.println("Nap: " + idopont.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("Tol: " + idopont.getElementsByTagName("tol").item(0).getTextContent());
                    System.out.println("Ig: " + idopont.getElementsByTagName("ig").item(0).getTextContent());

                    System.out.println("Helyszin: " + oraElement.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktato: " + oraElement.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak: " + oraElement.getElementsByTagName("szak").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
