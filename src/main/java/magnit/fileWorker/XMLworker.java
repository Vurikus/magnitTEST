package magnit.fileWorker;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class XMLworker implements FileWorker {
    //Field
    private final String PATH_TEMPLATE_XSLT = "src/main/resources/templateXSLT.xsl";
    private final String PATH_1xml = "src/main/resources/1.xml";
    private final String PATH_2xml = "src/main/resources/2.xml";
    private final static Logger logger = Logger.getLogger(XMLworker.class.getName());

    //Constructor

    //Function

    public void write(List list){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            File file = new File(PATH_1xml);

            Element entries = doc.createElement("entries");
            for (int i = 0; i < list.size(); i++) {
                Element entry = doc.createElement("entry");
                entries.appendChild(entry);
                Element field = doc.createElement("field");
                field.appendChild(doc.createTextNode(list.get(i).toString()));
                entry.appendChild(field);
            }

            doc.appendChild(entries);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));
        }
         catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void transformation() {
        try{
            StreamSource templateXSLT = new StreamSource(new File(PATH_TEMPLATE_XSLT));
            StreamSource inputXML = new StreamSource(new File(PATH_1xml));
            StreamResult outputXML = new StreamResult(new File(PATH_2xml));

            Transformer tr = TransformerFactory.newInstance().newTransformer(templateXSLT);

            tr.transform(inputXML, outputXML);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public int parseXML() {
        int result = 0;
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(PATH_2xml);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPathExpression xPathExpression = xPathfactory.newXPath().compile("//entries/entry[@field]");
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            System.out.println("Nodes number=" + nodeList.getLength());

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                String key = node.getAttributes().getNamedItem("field").getNodeValue();
                result += Integer.parseInt(key);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
