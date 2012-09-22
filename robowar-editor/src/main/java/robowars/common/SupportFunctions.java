/*
 * $Id: SupportFunctions.java 19 2004-07-05 06:35:52Z bdickie $
 *
 * Created on January 19, 2004, 11:32 AM
 */

package robowars.common;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;

import java.io.*;
import java.util.*;

/** This class provides a number of static support methods which the other
 *  classes use.
 *
 * @author  Blake Dickie
 */
public class SupportFunctions {
    private static String m_path;
    public static final String xmlnsURI = "http://www.w3.org/2000/xmlns/";
    public static final String schemaInstanceNS = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String xsdBase = "http://www.csc.uvic.ca/~bdickie/seng450/";
    
    /*********************** PUBLIC METHODS ********************/
    
    /** Finds the base uri of the code and returns it (as a String).
     *  This is used to find the location of configuration files (in
     *  particular the Itinerary for mobile agents).
     *  @return The URI of the directory which contains the <code>bdickie</code> package.
     */
    public static synchronized String getPath() {
        // It's a rather long series of methods calls to get the path so cache it.
        if (m_path == null)
        {
            m_path = SupportFunctions.class.getProtectionDomain().getCodeSource().getLocation().toString();
            m_path = m_path.substring(5);
        }
        
        return m_path;
    }
    
    /** Converts a String into a DOM tree.
     * @param xml The string containing the XML content to parse into a DOM tree.
     * @param validating Weither the parser should do schema validation on the input.
     * @return The document root of the object if successful, null otherwise.
     */
    public static Document parseXML(String xml, boolean validating) {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            if (validating)
                f.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            
            f.setNamespaceAware(true);
            f.setValidating(validating);
            DocumentBuilder db = f.newDocumentBuilder();
            SAXHandler handle = new SAXHandler();
            db.setErrorHandler(handle);
            InputSource src = new InputSource(new StringReader(xml));
            Document doc = db.parse(src);
            if (handle.isError())
                return null;
            return doc;
        } catch (Exception e) {
            System.err.print(e);
            return null;
        }
    }
    
    /** Converts a InputStream into a DOM tree.
     * @param xml The string containing the XML content to parse into a DOM tree.
     * @param validating Weither the parser should do schema validation on the input.
     * @return The document root of the object if successful, null otherwise.
     */
    public static Document parseXML(InputStream xml, boolean validating) {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            if (validating)
                f.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            
            f.setNamespaceAware(true);
            f.setValidating(validating);
            DocumentBuilder db = f.newDocumentBuilder();
            SAXHandler handle = new SAXHandler();
            db.setErrorHandler(handle);
            Document doc = db.parse(xml);
            if (handle.isError())
                return null;
            return doc;
        } catch (Exception e) {
            System.err.print(e);
            return null;
        }
    }
    
    /** Converts a DOM Tree to a String.
     * @param xml The DOM document to convert.
     * @return A string containing the XML if successful, false otherwise.
     */
    public static String xmlToString(Document xml) {
        try {
            TransformerFactory f = TransformerFactory.newInstance();
            StringWriter writer = new StringWriter();
            Transformer t = f.newTransformer();
            DOMSource src = new DOMSource(xml);
            StreamResult result = new StreamResult(writer);
            t.transform(src, result);
            return writer.toString();
        } catch (TransformerException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }
    
    
    /** Runs the given SAXHandler on the input string
     * @param xml The String to parse.
     * @param handler The SAXHandler to use on the xml.
     * @param validating Weither the parser should do schema validation on the input.
     * @return true iff no errors were reported in the parsing.
     */
    public static boolean saxParse(String xml, SAXHandler handler, boolean validating) {
        return saxParse(new InputSource(new StringReader(xml)), handler, validating);
    }
    
    
    /** Runs the given SAXHandler on the input string
     * @param xml The InputSource to parse.
     * @param handler The SAXHandler to use on the xml.
     * @param validating Weither the parser should do schema validation on the input.
     * @return true iff no errors were reported in the parsing.
     */
    public static boolean saxParse(InputSource xml, SAXHandler handler, boolean validating) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(validating);
            factory.setNamespaceAware(true);
            
            SAXParser parser = factory.newSAXParser();
            if (validating)
                parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            
            parser.parse(xml, handler);
            return !handler.isError();
        } catch (Exception e) {
            return false;
        }
    }
    
    /** Creates a new DOM Tree with a root element containing the schema attributes.
     * @param rootName The name of the root element.
     * @param namespaceURI The uri of the namespace of the document.
     * @param namespacePrefix The prefix to use for the namespace (ie. the part
     * before the ':' in the root element.
     * @param namespaceXSD The name of the xsd file used to validate the file.
     * Should be given relative to xsdBase.
     */
    public static Document newXMLTree(String rootName, String namespaceURI, String namespacePrefix, String namespaceXSD) {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.newDocument();
            Element rootNode = doc.createElementNS(namespaceURI, rootName);
            rootNode.setPrefix(namespacePrefix);
            
            rootNode.setAttributeNS(xmlnsURI, "xmlns:xsi", schemaInstanceNS);
            rootNode.setAttributeNS(xmlnsURI, "xmlns:" + namespacePrefix, namespaceURI);
            rootNode.setAttributeNS(schemaInstanceNS, "xsi:schemaLocation",
            namespaceURI + " " + xsdBase + namespaceXSD);
            
            doc.appendChild(rootNode);
            return doc;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }
    }
    
    /** Copies all the children elements and text nodes from one Element to another.
     * Will copy attributes on children elements.
     * @param src The element to copy from.
     * @param dst The element to copy to.
     * @param dstDoc The Document which contains the dst Element.
     */
    public static void convertChildren(Element src, Element dst, Document dstDoc) {
        String ns = dst.getNamespaceURI();
        NodeList children = src.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node curNode = children.item(i);
            if (curNode.getNodeType() == curNode.ELEMENT_NODE) {
                Element elm = (Element)curNode;
                Element newNode = dstDoc.createElementNS(ns, curNode.getLocalName());
                NamedNodeMap attrs = elm.getAttributes();
                for(int j = 0; j < attrs.getLength(); j++) {
                    Attr attr = (Attr)attrs.item(j);
                    newNode.setAttributeNS(ns, attr.getName(), attr.getValue());
                }
                
                dst.appendChild(newNode);
                
                convertChildren(elm, newNode, dstDoc);
                
            } else if (curNode.getNodeType() == curNode.TEXT_NODE) {
                Text txt = (Text)curNode;
                Text newNode = dstDoc.createTextNode(txt.getData());
                dst.appendChild(newNode);
            }
        }
    }
    
    /** Applies an XSLT.
     * @param xml The document to translate.
     * @param xsltFilename The filename of the xsl file to use.
     * @param params A map of parameters to pass to the transformation.
     * if null is passed none are used.
     * @return The string representing of the result of the transformation.
     */
    public static String applyXSLT(Document xml, String xsltFilename, Map params) {
        return applyXSLT(new DOMSource(xml), xsltFilename, params);
    }
    
    /** Applies an XSLT.
     * @param xml The inputstream to read the xml from.
     * @param xsltFilename The filename of the xsl file to use.
     * @param params A map of parameters to pass to the transformation.
     * if null is passed none are used.
     * @return The string representing of the result of the transformation.
     */
    public static String applyXSLT(InputStream in, String xsltFilename, Map params)
    {
        StreamSource src = new StreamSource(in);
        return applyXSLT(src, xsltFilename, params);
    }
    
    /** Applies an XSLT.
     * @param xml The soucrce to translate.
     * @param xsltFilename The filename of the xsl file to use.
     * @param params A map of parameters to pass to the transformation.
     * if null is passed none are used.
     * @return The string representing of the result of the transformation.
     */
    public static String applyXSLT(Source xml, String xsltFilename, Map params) {
        try {
            String fullPath = getPath() + xsltFilename;
            File xslt = new File(fullPath);
            TransformerFactory f = TransformerFactory.newInstance();
            StreamSource xsltSrc = new StreamSource(xslt);
            StringWriter out = new StringWriter();
            StreamResult res = new StreamResult(out);
            Transformer t = f.newTransformer(xsltSrc);
            
            if (params != null)
            {
                Iterator i = params.keySet().iterator();
                while(i.hasNext())
                {
                    Object obj = i.next();
                    t.setParameter(obj.toString(), params.get(obj));
                }
            }
            t.transform(xml, res);
            
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }
    }
    
    /** Gets the text value contained in an element.
     * @param node The Element to get the text value of.
     * @return The text in the element.
     */
    public static String getTextValue(Element node)
    {
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++)
        {
            Node n = nodes.item(i);
            if (n.getNodeType() == n.TEXT_NODE)
                return n.getNodeValue();
        }
        return null;
    }
    
    /** Trys to find a child element in the given parent element.
     * @param parent The Element to search in.
     * @param name The name of the element to search for.
     * @return The Element if found, null otherwise.
     */
    public static Element findElement(Element parent, String name)
    {
        NodeList l = parent.getChildNodes();
        for(int i = 0; i < l.getLength(); i++)
        {
            Node n = l.item(i);
            if (n.getNodeType() == n.ELEMENT_NODE)
            {
                Element e = (Element)n;
                if (e.getNodeName().equals(name))
                    return e;
            }
        }
        return null;
    }   
    
    public static String pluralize(String word)
    {
        if (word == null)
            return null;
        
        if (word.endsWith("y"))
        {
            return word.substring(0,word.length() - 1) + "ies";
        } else {
            return word + "s";
        }
    }
    
    public static String depluralize(String word)
    {
        if (word == null)
            return null;
        
        if (word.endsWith("ies"))
        {
            return word.substring(0,word.length() - 3) + "y";
        } else {
            return word.substring(0, word.length() - 1);
        }
    }
    
}

/* ChangeLog:
 *   $Log: SupportFunctions.java,v $
 *   Revision 1.2  2004/07/05 06:35:52  bdickie
 *   Done first build of card editor.
 *
 *   Revision 1.1  2004/07/04 18:43:00  bdickie
 *   XML Helpers from SEng 450.
 */
