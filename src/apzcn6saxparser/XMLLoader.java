/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apzcn6saxparser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author apzib
 */
public class XMLLoader {
 
        private static Stack<Element> stack;
    public static Element current;
    public static Element root;
    public static String input;
    
    public static String getinput(){
    input.trim();
    return input;
    }
    public static Element load(File xmlFile) throws Exception{
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler(){
            
            @Override
            public void startDocument() throws SAXException{
                root = null;
                stack = new Stack<>();
            }
            @Override
            public void characters(char ch[], int start, int length) throws SAXException{
                current.content = new String(ch, start, length);
                input += current.content;
            }
            @Override
            public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
                int j;
                Element node = new Element();
                node.name = qName;
                input += node.name + ":\t";
                node.attributes = new HashMap();
                
                for(j = 0; j<attributes.getLength(); j++){
                    node.attributes.put(attributes.getQName(j), attributes.getValue(j));
                }
                stack.push(node);
                
                if(current != null){
                    if(current.properties !=null){
                        current.properties.add(node);
                    }
                    else{
                        current.properties = new ArrayList();
                    }
                }
                current = node;
            }
            @Override
            public void endElement (String uri, String localName, String qName) throws SAXException{
                Element pop = stack.pop();
                if(pop != null){
                    pop.content = pop.content.trim();
                    
                    if(stack.empty()){
                        root = pop;
                        current = null;
                    }
                    else{
                        current = stack.peek();
                    }
                }
            }
           
            };
             saxParser.parse(xmlFile.getAbsoluteFile(), handler);
        }catch (Exception e){
        throw e;
    }
        return root;
    }
}
