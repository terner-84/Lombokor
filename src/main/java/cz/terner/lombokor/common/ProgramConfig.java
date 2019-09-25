
package cz.terner.lombokor.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProgramConfig {
    
    private static final Logger LOGGER = Logger.getLogger(ProgramConfig.class.getName());
    
    public File getFileConfig() {
        File file = null;
        try {
            //Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("setup/configuration.xml");
            if (resource == null) {
                throw new IllegalArgumentException("file not found");
            } else {
                file = new File(resource.getFile());
            }
        } catch (IllegalArgumentException e) {
            LOGGER.severe(e.getMessage());
        }
        return file;
    }
    
    public void handleConfig(File configFile) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(configFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("case");
            Node item = nodeList.item(0);
            Element element = (Element) item;
            int id = Integer.parseInt(element.getAttribute("id"));
            System.out.println("id parsed to int: " + id + " " + element.getElementsByTagName("expected").item(0).getTextContent());

        } catch (IOException | ParserConfigurationException | SAXException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
}
