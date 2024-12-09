package hu.domparse.BB89VX;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class DOMModifyBB89VX {
    public static void main(String[] args) {
        try {
            // XML beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLBB89VX.xml");
            removeWhitespace(doc.getDocumentElement());
            doc.getDocumentElement().normalize();

            // 1. Minden dolgozó fizetésének hozzáadása (új elem beszúrása)
            System.out.println("1. Módosítás: Dolgozói fizetések hozzáadása");
            System.out.println("--------------------------------------------");
            NodeList dolgozok = doc.getElementsByTagName("dolgozo");
            for (int i = 0; i < dolgozok.getLength(); i++) {
                Element dolgozo = (Element) dolgozok.item(i);
                Element fizetes = doc.createElement("fizetes");
                String beosztas = dolgozo.getElementsByTagName("beosztas").item(0).getTextContent();
                if (beosztas.equals("Állatorvos")) {
                    fizetes.setTextContent("450000");
                } else if (beosztas.equals("Gondozó")) {
                    fizetes.setTextContent("300000");
                } else {
                    fizetes.setTextContent("400000");
                }

                dolgozo.appendChild(fizetes);
                System.out.println(dolgozo.getElementsByTagName("nev").item(0).getTextContent() +
                                 " fizetése: " + fizetes.getTextContent() + " Ft");
            }

            // 2. Állatok életkorának növelése 1 évvel
            System.out.println("\n2. Módosítás: Állatok életkorának növelése");
            System.out.println("------------------------------------------");
            NodeList allatok = doc.getElementsByTagName("allat");
            for (int i = 0; i < allatok.getLength(); i++) {
                Element allat = (Element) allatok.item(i);
                Element eletkor = (Element) allat.getElementsByTagName("eletkor").item(0);
                String eletkorStr = eletkor.getTextContent();
                int kor = Integer.parseInt(eletkorStr.split(" ")[0]) + 1;
                eletkor.setTextContent(kor + " év");
                System.out.println(allat.getElementsByTagName("nev").item(0).getTextContent() +
                                 " új életkora: " + kor + " év");
            }

            // 3. Menhelyek férőhelyének bővítése 10%-kal
            System.out.println("\n3. Módosítás: Menhelyek férőhelyének bővítése");
            System.out.println("----------------------------------------------");
            NodeList menhelyek = doc.getElementsByTagName("menhely");
            for (int i = 0; i < menhelyek.getLength(); i++) {
                Element menhely = (Element) menhelyek.item(i);
                Element ferohely = (Element) menhely.getElementsByTagName("ferohely").item(0);
                int ujFerohely = (int)(Integer.parseInt(ferohely.getTextContent()) * 1.1);
                ferohely.setTextContent(String.valueOf(ujFerohely));
                System.out.println(menhely.getElementsByTagName("nev").item(0).getTextContent() +
                                 " új férőhelye: " + ujFerohely);
            }

            // 4. Betegségek súlyosságának megállapítása és attribútum hozzáadása
            System.out.println("\n4. Módosítás: Betegségek súlyosságának megjelölése");
            System.out.println("--------------------------------------------------");
            NodeList betegsegek = doc.getElementsByTagName("betegseg");
            for (int i = 0; i < betegsegek.getLength(); i++) {
                Element betegseg = (Element) betegsegek.item(i);
                int gyogyulasiIdo = Integer.parseInt(
                    betegseg.getElementsByTagName("gyogyulasi_ido").item(0).getTextContent()
                );

                String sulyossag;
                if (gyogyulasiIdo > 14) {
                    sulyossag = "súlyos";
                } else if (gyogyulasiIdo > 7) {
                    sulyossag = "közepes";
                } else {
                    sulyossag = "enyhe";
                }
                betegseg.setAttribute("sulyossag", sulyossag);

                System.out.println(betegseg.getElementsByTagName("nev").item(0).getTextContent() +
                                 " súlyossága: " + sulyossag);
            }

            // XML fájlba mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLBB89VX_modify.xml"));
            transformer.transform(source, result);

            System.out.println("\nA módosított XML fájl mentése sikeres!");

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