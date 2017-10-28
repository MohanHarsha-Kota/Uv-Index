package uv;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLReader {

    void readData(double uv) throws IOException, ParserConfigurationException, SAXException {
        try {
        File br = new File("UvData.xml");
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = df.newDocumentBuilder();
        Document doc = db.parse(br);
        doc.getDocumentElement().normalize();
        NodeList nlist;
        Element edata;
        Node ndata;
        //Fetch Data from XML file
        if(uv < 3)
        {
            nlist = doc.getElementsByTagName("C1");
            ndata = nlist.item(0);
            edata = (Element) ndata;
            System.out.println("Risk is : " +edata.getElementsByTagName("Risk").item(0).getTextContent());
            System.out.println("Safety Measures : " +edata.getElementsByTagName("Protection").item(0).getTextContent());
        }

        if(uv > 3 && uv < 6)
        {
            nlist = doc.getElementsByTagName("C2");
            ndata = nlist.item(0);
            edata = (Element) ndata;
            System.out.println("Risk is : " +edata.getElementsByTagName("Risk").item(0).getTextContent());
            System.out.println("Safety Measures : " +edata.getElementsByTagName("Protection").item(0).getTextContent());
        }

        if(uv > 6 && uv < 8)
        {
            nlist = doc.getElementsByTagName("C3");
            ndata = nlist.item(0);
            edata = (Element) ndata;
            System.out.println("Risk is : " +edata.getElementsByTagName("Risk").item(0).getTextContent());
            System.out.println("Safety Measures : " +edata.getElementsByTagName("Protection").item(0).getTextContent());
        }

        else if(uv > 8 && uv < 11)
        {
            nlist = doc.getElementsByTagName("C4");
            ndata = nlist.item(0);
            edata = (Element) ndata;
            System.out.println("Risk is : " +edata.getElementsByTagName("Risk").item(0).getTextContent());
            System.out.println("Safety Measures : " +edata.getElementsByTagName("Protection").item(0).getTextContent());
        }

        else if(uv > 11)
        {
            nlist = doc.getElementsByTagName("C5");
            ndata = nlist.item(0);
            edata = (Element) ndata;
            System.out.println("Risk is : " +edata.getElementsByTagName("Risk").item(0).getTextContent());
            System.out.println("Safety Measures :" +edata.getElementsByTagName("Protection").item(0).getTextContent());
        }

        } catch (IOException e)
        {
            System.out.println("Error Loading File :"+e.getMessage().toString());
        }
    }
}
