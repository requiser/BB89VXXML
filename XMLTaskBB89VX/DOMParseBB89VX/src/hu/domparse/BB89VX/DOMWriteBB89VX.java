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
            // DocumentBuilder létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML fájl beolvasása
            Document doc = builder.parse("XMLBB89VX.xml");
            removeWhitespace(doc.getDocumentElement());

            // XML struktúra normalizálása
            doc.getDocumentElement().normalize();

            // Gyökérelem kiírása
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            // Verem az aktuális csomópont és behúzás tárolására
            java.util.Stack<Object[]> stack = new java.util.Stack<>();
            stack.push(new Object[]{doc.getDocumentElement(), ""});

            // Fa bejárása és kiírása iteratív módon
            while (!stack.empty()) {
                Object[] current = stack.pop();
                Node node = (Node)current[0];
                String indent = (String)current[1];

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // Elem nevének kiírása
                    System.out.print(indent + node.getNodeName());

                    // Attribútumok kiírása, ha vannak
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

                    // Szöveges tartalom kiírása, ha levél csomópont
                    if (node.getChildNodes().getLength() == 1 &&
                        node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                        System.out.println(": " + node.getTextContent().trim());
                    } else {
                        System.out.println();
                    }

                    // Gyermek elemek hozzáadása a veremhez fordított sorrendben
                    NodeList children = node.getChildNodes();
                    for (int i = children.getLength() - 1; i >= 0; i--) {
                        Node child = children.item(i);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            stack.push(new Object[]{child, indent + "  "});
                        }
                    }
                }
            }

            // XML fájlba íráshoz szükséges objektumok létrehozása
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

            // Forrás és cél beállítása
            DOMSource source = new DOMSource(doc);

            // Kimenet StreamResult-ba
            StreamResult fileResult = new StreamResult(new File("XMLBB89VX1.xml"));

            // A dokumentum transzformálása
            transformer.transform(source, fileResult);

            System.out.println("\nAz XML fájl sikeresen létrejött!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fölösleges üres sorok eltávolítása
    private static void removeWhitespace(Node node) {
        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                String trim = child.getTextContent().trim();
                if (trim.isEmpty()) {
                    node.removeChild(child);
                } else {
                    ((Text) child).setData(trim);
                }
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                removeWhitespace(child);
            }
        }
    }
}