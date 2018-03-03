/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apzcn6saxparser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author apzib
 */
public class Element {


    public Element(){
        
    }
    String name = "";
    String content = "";
    HashMap<String,String> attributes;
    ArrayList<Element> properties;
    
}
