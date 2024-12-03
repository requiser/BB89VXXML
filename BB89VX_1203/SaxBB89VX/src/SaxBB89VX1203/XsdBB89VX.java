package SaxBB89VX1203;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XsdBB89VX {

    public static void main(String[] args) {
        // Fájlok elérési útvonala
        String xmlFilePath = "BB89VX_1203/BB89VX_kurzusfelvetel.xml"; // XML fájl neve
        String xsdFilePath = "BB89VX_1203/BB89VX_kurzusfelvetel.xsd"; // XSD fájl neve

        try {
            // Séma gyár létrehozása
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // XSD séma betöltése
            Schema schema = factory.newSchema(new File(xsdFilePath));

            // Validator objektum létrehozása
            Validator validator = schema.newValidator();

            // XML validálása
            validator.validate(new StreamSource(new File(xmlFilePath)));

            // Ha a validáció sikeres
            System.out.println("Successful validation!");
        } catch (SAXException e) {
            System.out.println("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
