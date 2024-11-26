package xpathBB89VX1119;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;

public class xPathBB89VX_2 {

    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File inputFile = new File("BB89VX_1119/xPathBB89VX/src/xpathBB89VX1119/BB89VX_orarend.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // XPath készítése
            XPath xPath = XPathFactory.newInstance().newXPath();

            // 1. módosítás: szak nevének módosítása
            XPathExpression expr1 = xPath.compile("//ora/szak");
            NodeList szakNodes = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < szakNodes.getLength(); i++) {
                szakNodes.item(i).setTextContent("Informatikus Mérnök");
            }

            // 2. módosítás: tárgy nevéhez monogram hozzáfűzése
            XPathExpression expr2 = xPath.compile("//ora/targy");
            NodeList targyNodes = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < targyNodes.getLength(); i++) {
                String currentText = targyNodes.item(i).getTextContent();
                targyNodes.item(i).setTextContent(currentText + " (BL)");
            }

            // 3. módosítás: id=3 elem helyszín módosítása
            XPathExpression expr3 = xPath.compile("//ora[@id='03']/helyszin");
            Node helyszinNode = (Node) expr3.evaluate(doc, XPathConstants.NODE);
            if (helyszinNode != null) {
                helyszinNode.setTextContent("XXXVII");
            }

            // Módosított XML mentése új fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("orarendBB89VX1.xml"));
            transformer.transform(source, result);

            // Eredmény konzolra írása
            System.out.println("Módosítások végrehajtva és mentve az orarendBB89VX1.xml fájlba!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
