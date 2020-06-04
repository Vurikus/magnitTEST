package magnit.molder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Collection;
import java.util.List;

public class XMLmolder {
    //Field
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private Document doc;
    private File file;
    private String pathXML = "xml/1.xml";

    //Constructor

    //Function

    public void writeXML(int [] numbers) throws ParserConfigurationException, TransformerException {
        factory.setNamespaceAware(true);
        doc = factory.newDocumentBuilder().newDocument();
        file = new File(pathXML);

        Element entries = doc.createElement("entries");
        for (int i = 0; i<numbers.length; i++){
            Element entry = doc.createElement("entry");
            entries.appendChild(entry);
                Element field = doc.createElement("field");
                field.appendChild(doc.createTextNode(String.valueOf(numbers[i])));
            entry.appendChild(field);
        }

        doc.appendChild(entries);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }
}
