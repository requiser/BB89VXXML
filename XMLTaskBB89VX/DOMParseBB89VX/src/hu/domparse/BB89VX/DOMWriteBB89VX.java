package hu.domparse.BB89VX;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class DOMWriteBB89VX {

    public static void main(String[] args) {
        try {
            // XML beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLBB89VX.xml");
            doc.getDocumentElement().normalize();

            // Kiírás
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            java.util.Stack<Object[]> stack = new java.util.Stack<>();
            stack.push(new Object[]{doc.getDocumentElement(), ""});

            while (!stack.empty()) {
                Object[] current = stack.pop();
                Node node = (Node)current[0];
                String indent = (String)current[1];

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(indent + node.getNodeName());

                    if (node.hasAttributes()) {
                        NamedNodeMap attributes = node.getAttributes();
                        System.out.print(" [");
                        for (int i = 0; i < attributes.getLength(); i++) {
                            Node attr = attributes.item(i);
                            System.out.print(attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
                            if (i < attributes.getLength() - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.print("]");
                    }

                    if (node.getChildNodes().getLength() == 1 &&
                        node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                        System.out.println(": " + node.getTextContent().trim());
                    } else {
                        System.out.println();
                    }

                    NodeList children = node.getChildNodes();
                    for (int i = children.getLength() - 1; i >= 0; i--) {
                        Node child = children.item(i);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            stack.push(new Object[]{child, indent + "  "});
                        }
                    }
                }
            }

            // XML fájlba mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLBB89VX1.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}