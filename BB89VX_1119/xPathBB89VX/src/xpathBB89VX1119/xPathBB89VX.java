package xpathBB89VX1119;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;

public class xPathBB89VX {
    public static void main(String[] args) {
        try {
            // Az XML dokumentum betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("BB89VX_1119/xPathBB89VX/src/xpathBB89VX1119/studentBB89VX.xml"));

            // XPath inicializálása
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            // 1) Válassza ki az összes student elemet, amely a class gyermekei!
            XPathExpression expr1 = xPath.compile("/class/student");
            NodeList nodes1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
            System.out.println("1) Összes student (class gyermeke): " + nodes1.getLength());
            // Eredmény: nodes1 tartalmazza a találatokat.

            // 2) Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "02"!
            XPathExpression expr2 = xPath.compile("/class/student[@id='ST02']");
            Node node2 = (Node) expr2.evaluate(doc, XPathConstants.NODE);
            System.out.println("2) Student id='ST02': " + (node2 != null ? node2.getNodeName() : "Nem található"));
            // Eredmény: node2 tartalmazza az elemet.

            // 3) Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
            XPathExpression expr3 = xPath.compile("//student");
            NodeList nodes3 = (NodeList) expr3.evaluate(doc, XPathConstants.NODESET);
            System.out.println("3) Összes student: " + nodes3.getLength());
            // Eredmény: nodes3 tartalmazza a találatokat.

            // 4) Válassza ki a második student elemet, amely a class root element gyermeke!
            XPathExpression expr4 = xPath.compile("/class/student[2]");
            Node node4 = (Node) expr4.evaluate(doc, XPathConstants.NODE);
            System.out.println("4) Második student: " + (node4 != null ? node4.getNodeName() : "Nem található"));
            // Eredmény: node4 tartalmazza az elemet.

            // 5) Válassza ki az utolsó student elemet, amely a class root element gyermeke!
            XPathExpression expr5 = xPath.compile("/class/student[last()]");
            Node node5 = (Node) expr5.evaluate(doc, XPathConstants.NODE);
            System.out.println("5) Utolsó student: " + (node5 != null ? node5.getNodeName() : "Nem található"));
            // Eredmény: node5 tartalmazza az elemet.

            // 6) Válassza ki az utolsó előtti student elemet, amely a class root element gyermeke!
            XPathExpression expr6 = xPath.compile("/class/student[last()-1]");
            Node node6 = (Node) expr6.evaluate(doc, XPathConstants.NODE);
            System.out.println("6) Utolsó előtti student: " + (node6 != null ? node6.getNodeName() : "Nem található"));
            // Eredmény: node6 tartalmazza az elemet.

            // 7) Válassza ki az első két student elemet, amelyek a root element gyermekei!
            XPathExpression expr7 = xPath.compile("/class/student[position() <= 2]");
            NodeList nodes7 = (NodeList) expr7.evaluate(doc, XPathConstants.NODESET);
            System.out.println("7) Első két student: " + nodes7.getLength());
            // Eredmény: nodes7 tartalmazza a találatokat.

            // 8) Válassza ki class root element összes gyermek elemét!
            XPathExpression expr8 = xPath.compile("/class/*");
            NodeList nodes8 = (NodeList) expr8.evaluate(doc, XPathConstants.NODESET);
            System.out.println("8) class összes gyermeke: " + nodes8.getLength());
            // Eredmény: nodes8 tartalmazza a találatokat.

            // 9) Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
            XPathExpression expr9 = xPath.compile("/class/student[@*]");
            NodeList nodes9 = (NodeList) expr9.evaluate(doc, XPathConstants.NODESET);
            System.out.println("9) Student elemek attribútummal: " + nodes9.getLength());
            // Eredmény: nodes9 tartalmazza a találatokat.

            // 10) Válassza ki a dokumentum összes elemét!
            XPathExpression expr10 = xPath.compile("//*");
            NodeList nodes10 = (NodeList) expr10.evaluate(doc, XPathConstants.NODESET);
            System.out.println("10) Összes elem: " + nodes10.getLength());
            // Eredmény: nodes10 tartalmazza a találatokat.

            // 11) Válassza ki a class root element összes student elemét, amelynél a kor > 20!
            XPathExpression expr11 = xPath.compile("/class/student[number(kor) > 20]");
            NodeList nodes11 = (NodeList) expr11.evaluate(doc, XPathConstants.NODESET);
            System.out.println("11) Student elemek kor > 20: " + nodes11.getLength());
            // Eredmény: nodes11 tartalmazza a találatokat.


            // 12) Válassza ki az összes student elem összes keresztnev vagy vezeteknev csomópontot!
            XPathExpression expr12 = xPath.compile("//student/keresztnev | //student/vezeteknev");
            NodeList nodes12 = (NodeList) expr12.evaluate(doc, XPathConstants.NODESET);
            System.out.println("12) Student elemek keresztnev vagy vezeteknev: " + nodes12.getLength());
            // Eredmény: nodes12 tartalmazza a találatokat.


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
