package domBB89VX1105;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomQueryBB89VX_orarend {

    public static void main(String[] args) {
        try {
            // Load the XML document
            File xmlFile = new File("BB89VX_1105/DomQueryBB89VX/src/domBB89VX1105/BB89VX_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Query (a): Retrieve all course names and store them in a list
            List<String> courseNames = new ArrayList<>();
            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String courseName = ora.getElementsByTagName("targy").item(0).getTextContent();
                courseNames.add(courseName);
            }
            System.out.println("Kurzusnév: " + courseNames);

            // Query (b): Retrieve the first "ora" element and print in structured format
            if (oraList.getLength() > 0) {
                Element elsoOra = (Element) oraList.item(0);
                printStructuredOra(elsoOra);
                saveStructuredOra(elsoOra, "elsoOra.xml");
            }

            // Query (c): Retrieve all instructor names and store them in a list
            List<String> instructorNames = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String instructorName = ora.getElementsByTagName("oktato").item(0).getTextContent();
                instructorNames.add(instructorName);
            }
            System.out.println("Oktatók: " + instructorNames);

            // Query (d): Custom query - retrieve all course names that are lectures and print the day and time
            System.out.println("Lecture courses with schedule:");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                if ("eloadas".equals(ora.getAttribute("tipus"))) {
                    String lectureName = ora.getElementsByTagName("targy").item(0).getTextContent();
                    String day = ora.getElementsByTagName("nap").item(0).getTextContent();
                    String startTime = ora.getElementsByTagName("tol").item(0).getTextContent();
                    String endTime = ora.getElementsByTagName("ig").item(0).getTextContent();
                    System.out.println("Kurzus: " + lectureName + " | Nap: " + day + " | Idő: " + startTime + " - " + endTime);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to print structured data for an "ora" element
    private static void printStructuredOra(Element ora) {
        System.out.println("ID: " + ora.getAttribute("id"));
        System.out.println("Tipus: " + ora.getAttribute("tipus"));
        System.out.println("Targy: " + ora.getElementsByTagName("targy").item(0).getTextContent());
        System.out.println("Nap: " + ora.getElementsByTagName("nap").item(0).getTextContent());
        System.out.println("Tol: " + ora.getElementsByTagName("tol").item(0).getTextContent());
        System.out.println("Ig: " + ora.getElementsByTagName("ig").item(0).getTextContent());
        System.out.println("Helyszin: " + ora.getElementsByTagName("helyszin").item(0).getTextContent());
        System.out.println("Oktato: " + ora.getElementsByTagName("oktato").item(0).getTextContent());
        System.out.println("Szak: " + ora.getElementsByTagName("szak").item(0).getTextContent());
    }

    // Method to save structured data of the first "ora" element to a file
    private static void saveStructuredOra(Element ora, String fileName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document newDoc = dBuilder.newDocument();

            // Import the element to the new document
            Node importedNode = newDoc.importNode(ora, true);
            newDoc.appendChild(importedNode);

            // Save the new document to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("BB89VX_1105/DomQueryBB89VX/src/domBB89VX1105/" + fileName));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
