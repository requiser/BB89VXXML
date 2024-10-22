package domBB89VX1022;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWrite1 {

    public static void main(String[] args) {
        try {
            File inputFile = new File("BB89VX_1022/DomParseBB89VX/src/domBB89VX1022/BB89VX_orarend.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("ora");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");
                    String tipus = element.getAttribute("tipus");
                    System.out.println("Óra ID: " + id);
                    System.out.println("Típus: " + tipus);

                    String targy = element.getElementsByTagName("targy").item(0).getTextContent();
                    String nap = element.getElementsByTagName("nap").item(0).getTextContent();
                    String tol = element.getElementsByTagName("tol").item(0).getTextContent();
                    String ig = element.getElementsByTagName("ig").item(0).getTextContent();
                    String helyszin = element.getElementsByTagName("helyszin").item(0).getTextContent();
                    String oktato = element.getElementsByTagName("oktato").item(0).getTextContent();
                    String szak = element.getElementsByTagName("szak").item(0).getTextContent();

                    System.out.println("Tantárgy: " + targy);
                    System.out.println("Nap: " + nap);
                    System.out.println("Időpont: " + tol + " - " + ig);
                    System.out.println("Helyszín: " + helyszin);
                    System.out.println("Oktató: " + oktato);
                    System.out.println("Szak: " + szak);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            StreamResult result = new StreamResult(new File("BB89VX_1022/DomParseBB89VX/src/domBB89VX1022/orarend1BB89VX.xml"));
            transformer.transform(source, result);

            System.out.println("\nAz új XML fájl sikeresen létrehozva: orarend1BB89VX.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
