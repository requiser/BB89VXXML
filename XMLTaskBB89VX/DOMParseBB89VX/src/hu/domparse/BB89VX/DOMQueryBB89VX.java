package hu.domparse.BB89VX;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Objects;

public class DOMQueryBB89VX {
    public static void main(String[] args) {
        try {
            // XML beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLTaskBB89VX/XMLBB89VX.xml");
            doc.getDocumentElement().normalize();

            // 1. Miskolcon lakó dolgozók
            String keresettvaros = "Miskolc";
            System.out.println("1. " + keresettvaros + "-i dolgozók:");
            System.out.println("---------------------------");
            NodeList varosok = doc.getElementsByTagName("varos");

            // Keresett város irányítószámának megkeresése
            String keresettirszam = "";
            for (int i = 0; i < varosok.getLength(); i++) {
                Element varos = (Element) varosok.item(i);
                Node nevNode = varos.getElementsByTagName("nev").item(0);
                if (nevNode != null && Objects.equals(nevNode.getTextContent(), keresettvaros)) {
                    keresettirszam = varos.getAttribute("iranyitoszam");
                    break;
                }
            }

            // Irányítószám alapján való dolgozók keresése
            if (!keresettirszam.isEmpty()) {
                NodeList dolgozok = doc.getElementsByTagName("dolgozo");

                for (int i = 0; i < dolgozok.getLength(); i++) {
                    Element dolgozo = (Element) dolgozok.item(i);
                    Node lakhelyNode = dolgozo.getElementsByTagName("lakhely").item(0);

                    if (lakhelyNode instanceof Element) {
                        Element lakhely = (Element) lakhelyNode;
                        String iranyitoszam = lakhely.getAttribute("iranyitoszam");

                        if (Objects.equals(iranyitoszam, keresettirszam)) {
                            Node nevNode = dolgozo.getElementsByTagName("nev").item(0);
                            Node beosztasNode = dolgozo.getElementsByTagName("beosztas").item(0);

                            if (nevNode != null && beosztasNode != null) {
                                String nev = nevNode.getTextContent();
                                String beosztas = beosztasNode.getTextContent();
                                System.out.println("Név: " + nev + ", Beosztás: " + beosztas);
                            }
                        }
                    }
                }
            } else {
                System.out.println("Nem található a keresett Város a xml-ben.");
            }


            // 2. 300 férőhelynél nagyobb menhelyek
            System.out.println("\n2. 300 férőhelynél nagyobb menhelyek:");
            System.out.println("------------------------------------");
            NodeList menhelyek = doc.getElementsByTagName("menhely");
            for (int i = 0; i < menhelyek.getLength(); i++) {
                Element menhely = (Element) menhelyek.item(i);
                int ferohely = Integer.parseInt(menhely.getElementsByTagName("ferohely").item(0).getTextContent());
                if (ferohely > 300) {
                    String nev = menhely.getElementsByTagName("nev").item(0).getTextContent();
                    System.out.println("Név: " + nev + ", Férőhely: " + ferohely);
                }
            }

            // 3. 3 évnél idősebb állatok
            System.out.println("\n3. 3 évnél idősebb állatok:");
            System.out.println("--------------------------");
            NodeList allatok = doc.getElementsByTagName("allat");
            for (int i = 0; i < allatok.getLength(); i++) {
                Element allat = (Element) allatok.item(i);
                String eletkorStr = allat.getElementsByTagName("eletkor").item(0).getTextContent();
                // Az életkor szövegből csak a számot vesszük ki
                int eletkor = Integer.parseInt(eletkorStr.split(" ")[0]);
                if (eletkor >= 3) {
                    String nev = allat.getElementsByTagName("nev").item(0).getTextContent();
                    String faj = allat.getElementsByTagName("faj").item(0).getTextContent();
                    System.out.println("Név: " + nev + ", Faj: " + faj + ", Életkor: " + eletkorStr);
                }
            }

            // 4. 10 napnál hosszabb gyógyulási idejű betegségek
            System.out.println("\n4. 10 napnál hosszabb gyógyulási idejű betegségek:");
            System.out.println("-----------------------------------------------");
            NodeList betegsegek = doc.getElementsByTagName("betegseg");
            for (int i = 0; i < betegsegek.getLength(); i++) {
                Element betegseg = (Element) betegsegek.item(i);
                int gyogyulasiIdo = Integer.parseInt(betegseg.getElementsByTagName("gyogyulasi_ido").item(0).getTextContent());
                if (gyogyulasiIdo > 10) {
                    String nev = betegseg.getElementsByTagName("nev").item(0).getTextContent();
                    String tuntetek = betegseg.getElementsByTagName("tuntetek").item(0).getTextContent();
                    System.out.println("Betegség: " + nev);
                    System.out.println("Gyógyulási idő: " + gyogyulasiIdo + " nap");
                    System.out.println("Tünetek: " + tuntetek + "\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}