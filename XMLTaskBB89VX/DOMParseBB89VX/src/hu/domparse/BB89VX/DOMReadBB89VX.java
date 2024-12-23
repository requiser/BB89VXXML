package hu.domparse.BB89VX;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class DOMReadBB89VX {
    public static void main(String[] args) {
        try {
            // XML beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLBB89VX.xml");
            doc.getDocumentElement().normalize();

            // Adatok kiírása

            // Városok
            System.out.println("VÁROSOK ADATAI:");
            System.out.println("===============");
            NodeList varosok = doc.getElementsByTagName("varos");
            for (int i = 0; i < varosok.getLength(); i++) {
                Element varos = (Element) varosok.item(i);
                String nev = varos.getElementsByTagName("nev").item(0).getTextContent();
                String terulet = varos.getElementsByTagName("terulet").item(0).getTextContent();
                String lakossag = varos.getElementsByTagName("lakossag").item(0).getTextContent();
                String iranyitoszam = varos.getAttribute("iranyitoszam");

                System.out.println("\nVáros neve: " + nev);
                System.out.println("Irányítószám: " + iranyitoszam);
                System.out.println("Terület: " + terulet + " km²");
                System.out.println("Lakosság: " + lakossag + " fő");
                System.out.println("--------------------");
            }

            // Dolgozók
            System.out.println("\nDOLGOZÓK ADATAI:");
            System.out.println("================");
            NodeList dolgozok = doc.getElementsByTagName("dolgozo");
            for (int i = 0; i < dolgozok.getLength(); i++) {
                Element dolgozo = (Element) dolgozok.item(i);
                Element lakhely = (Element) dolgozo.getElementsByTagName("lakhely").item(0);

                System.out.println("\nSzemélyi szám: " + dolgozo.getAttribute("szemelyi_szam"));
                System.out.println("Név: " + dolgozo.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Beosztás: " + dolgozo.getElementsByTagName("beosztas").item(0).getTextContent());
                System.out.println("Nem: " + dolgozo.getElementsByTagName("nem").item(0).getTextContent());
                System.out.println("Édesanyja neve: " + dolgozo.getElementsByTagName("edesanyja_leanykori_neve").item(0).getTextContent());
                System.out.println("Lakcím: " + lakhely.getElementsByTagName("utca").item(0).getTextContent() + " " +
                                 lakhely.getElementsByTagName("hazszam").item(0).getTextContent() +
                                 " (" + lakhely.getAttribute("iranyitoszam") + ")");
                System.out.println("Telefonszám: " + dolgozo.getElementsByTagName("telefonszam").item(0).getTextContent());
                System.out.println("--------------------");
            }

            // Állatok
            System.out.println("\nÁLLATOK ADATAI:");
            System.out.println("===============");
            NodeList allatok = doc.getElementsByTagName("allat");
            for (int i = 0; i < allatok.getLength(); i++) {
                Element allat = (Element) allatok.item(i);

                System.out.println("\nAzonosító: " + allat.getAttribute("azonosito_kod"));
                System.out.println("Név: " + allat.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Életkor: " + allat.getElementsByTagName("eletkor").item(0).getTextContent());
                System.out.println("Faj: " + allat.getElementsByTagName("faj").item(0).getTextContent());
                System.out.println("--------------------");
            }

            // Menhelyek
            System.out.println("\nMENHELYEK ADATAI:");
            System.out.println("=================");
            NodeList menhelyek = doc.getElementsByTagName("menhely");
            for (int i = 0; i < menhelyek.getLength(); i++) {
                Element menhely = (Element) menhelyek.item(i);
                Element cim = (Element) menhely.getElementsByTagName("cim").item(0);

                System.out.println("\nAzonosító: " + menhely.getAttribute("nyilvantartasi_azonosito"));
                System.out.println("Név: " + menhely.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Férőhely: " + menhely.getElementsByTagName("ferohely").item(0).getTextContent());
                System.out.println("Telefonszám: " + menhely.getElementsByTagName("telefonszam").item(0).getTextContent());
                System.out.println("Cím: " + cim.getElementsByTagName("utca").item(0).getTextContent() + " " +
                                 cim.getElementsByTagName("hazszam").item(0).getTextContent() +
                                 " (" + cim.getAttribute("iranyitoszam") + ")");
                System.out.println("--------------------");
            }

            // Betegségek
            System.out.println("\nBETEGSÉGEK ADATAI:");
            System.out.println("=================");
            NodeList betegsegek = doc.getElementsByTagName("betegseg");
            for (int i = 0; i < betegsegek.getLength(); i++) {
                Element betegseg = (Element) betegsegek.item(i);

                System.out.println("\nLatin név: " + betegseg.getAttribute("latin_nev"));
                System.out.println("Név: " + betegseg.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Gyógymód: " + betegseg.getElementsByTagName("gyogymod").item(0).getTextContent());
                System.out.println("Gyógyulási idő: " + betegseg.getElementsByTagName("gyogyulasi_ido").item(0).getTextContent() + " nap");
                System.out.println("Tünetek: " + betegseg.getElementsByTagName("tuntetek").item(0).getTextContent());
                System.out.println("--------------------");
            }

            // Örökbefogadók
            System.out.println("\nÖRÖKBEFOGADÓK ADATAI:");
            System.out.println("=====================");
            NodeList orokbefogadok = doc.getElementsByTagName("orokbefogado");
            for (int i = 0; i < orokbefogadok.getLength(); i++) {
                Element orokbefogado = (Element) orokbefogadok.item(i);
                Element lakhely = (Element) orokbefogado.getElementsByTagName("lakhely").item(0);

                System.out.println("\nSzemélyi szám: " + orokbefogado.getAttribute("szemelyi_szam"));
                System.out.println("Név: " + orokbefogado.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Születési idő: " + orokbefogado.getElementsByTagName("szul_ido").item(0).getTextContent());
                System.out.println("Telefonszám: " + orokbefogado.getElementsByTagName("telefonszam").item(0).getTextContent());
                System.out.println("Lakcím: " + lakhely.getElementsByTagName("utca").item(0).getTextContent() + " " +
                                 lakhely.getElementsByTagName("hazszam").item(0).getTextContent() +
                                 " (" + lakhely.getAttribute("iranyitoszam") + ")");
                System.out.println("--------------------");
            }

            // XML fájlba mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLBB89VX_read.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}