package domBB89VX1015;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class DOMWriteBB89VX {

    public static void main(String[] args) {
        try {
            File inputFile = new File("orarendNeptunkod.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("XML Content in Tree Structure:");
            printXMLTree(doc.getDocumentElement(), 0);

            writeXMLToFile(doc, "orarend1Neptunkod.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printXMLTree(Node node, int indentLevel) {
        String indent = "  ".repeat(indentLevel);

        System.out.println(indent + "Node: " + node.getNodeName());

        if (node.hasAttributes()) {
            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                System.out.println(indent + "  Attribute: " + attr.getNodeName() + " = " + attr.getNodeValue());
            }
        }

        if (node.getNodeType() == Node.TEXT_NODE && !node.getTextContent().trim().isEmpty()) {
            System.out.println(indent + "  Value: " + node.getTextContent().trim());
        }

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE || childNode.getNodeType() == Node.TEXT_NODE) {
                printXMLTree(childNode, indentLevel + 1);
            }
        }
    }

    private static void writeXMLToFile(Document doc, String outputFileName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(outputFileName));
            transformer.transform(domSource, streamResult);

            StringWriter writer = new StringWriter();
            transformer.transform(domSource, new StreamResult(writer));
            System.out.println("\nDocument saved as " + outputFileName + ":\n" + writer.toString());

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
