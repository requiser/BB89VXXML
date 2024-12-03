package SaxBB89VX1203;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxBB89VX {

    public static void main(String[] args) {
        try {
            // SAXParserFactory inicializálása
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Tartalomkezelő handler definiálása
            DefaultHandler handler = new DefaultHandler() {

                int indentLevel = 0; // Indentáció szintjének nyomon követése

                // Indentáció generálása a szintek alapján
                private String getIndentation() {
                    return "  ".repeat(indentLevel); // Kétszeres szóköz minden szintre
                }

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // Kezdőelem kiírása indentációval
                    System.out.print(getIndentation() + qName);
                    if (attributes.getLength() > 0) {
                        System.out.print(", {");
                        for (int i = 0; i < attributes.getLength(); i++) {
                            System.out.print(attributes.getQName(i) + "=" + attributes.getValue(i));
                            if (i < attributes.getLength() - 1) System.out.print(", ");
                        }
                        System.out.print("}");
                    }
                    System.out.println(" start");
                    indentLevel++; // Növeljük az indentációs szintet
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    indentLevel--; // Csökkentjük az indentációs szintet
                    System.out.println(getIndentation() + qName + " end");
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String content = new String(ch, start, length).trim();
                    if (!content.isEmpty()) {
                        System.out.println(getIndentation() + content);
                    }
                }
            };

            // XML feldolgozása
            File inputFile = new File("BB89VX_1203/BB89VX_kurzusfelvetel.xml");
            saxParser.parse(inputFile, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
