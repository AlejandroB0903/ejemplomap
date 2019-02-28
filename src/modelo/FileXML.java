/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author javier.benitez
 */
public class FileXML {
    
    public static boolean crearArchivoXML (Map<Integer, LinkedList<Persona>> m){
        
     boolean t = false;
     
     try{
         
         Element personas = new Element("Personas");
         Document doc = new  Document(personas);
     
         Iterator<Map.Entry<Integer,LinkedList<Persona>>> it = m.entrySet().iterator();
        
         while(it.hasNext()){
            
            Map.Entry<Integer,LinkedList<Persona>> pair = it.next();
            Element key = new Element("key");
            key.setAttribute(new Attribute("id", String.valueOf(pair.getKey())));
            doc.getRootElement().addContent(key);
        
            for(int i = 0 ; i < pair.getValue().size() ; i++){
                
                Persona objp = pair.getValue().get(i);
                Element per = new Element("persona");
                
                per.setAttribute(new Attribute("Nombre",objp.getNombre()));
                per.setAttribute(new Attribute("id",objp.getId()));
                per.setAttribute(new Attribute("Edad",String.valueOf(objp.getEdad())));
                
                doc.getRootElement().addContent(per);          
            }
        }
        
        XMLOutputter xmlOutput = new XMLOutputter();
        
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(doc, new FileWriter("FileP.xml"));
        t = true;
        
     }catch(IOException io){
         
         System.out.println(io.getMessage());
     }   
     
     return t;
    }
}
