/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;
import java.awt.Point;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import material.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Romy Steve
 */
public class XmlReader {
    
    
    private File xmlFile;
    private DocumentBuilderFactory factory; 
    private DocumentBuilder dBuilder;
    private Document doc;
    
    public XmlReader(String pathFileName)throws SAXException,
            IOException, ParserConfigurationException {
        this.xmlFile = new File(pathFileName);
        this.factory = DocumentBuilderFactory.newInstance();
        this.dBuilder = this.factory.newDocumentBuilder();
        this.doc = this.dBuilder.parse(xmlFile);
        this.doc.getDocumentElement().normalize();
    }
    
    private Composant getComposantSortie(Element element, Point point, String typeUsine){
        //Element elem1 =  (Element)element.getElementsByTagName("sortie").item(0);
        
        NodeList listTypeObjectUsine =  element.getElementsByTagName("usine"); 
        for(int i = 0; i < listTypeObjectUsine.getLength(); i++){
            
            Node nNode = listTypeObjectUsine.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element elem = (Element) nNode;
                if(elem.getAttribute("type").equals(typeUsine)){
                NodeList list = elem.getElementsByTagName("sortie");
                Node nNode2 = list.item(0);
        
                    if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem2 = (Element) nNode2;
                        if(elem2.getAttribute("type").equals("metal")){
                           //icons.add(new Icon(elem.getAttribute("type"),"src/ressources/metal.png" ));
                           return new ComposantMetal( point, new Icon(elem2.getAttribute("type"),"src/ressources/metal.png" ));
                        }else if(elem2.getAttribute("type").equals("moteur")){
                           //icons.add(new Icon(elem.getAttribute("type"),"src/ressources/moteur.png" ));
                           return new ComposantMoteur(point, new Icon(elem2.getAttribute("type"),"src/ressources/moteur.png" ));
                        }else if(elem2.getAttribute("type").equals("aile")){
                          // icons.add(new Icon(elem.getAttribute("type"),"src/ressources/aile.png" ));
                           return new ComposantAile(point, new Icon(elem2.getAttribute("type"),"src/ressources/aile.png" ));
                        }else if(elem2.getAttribute("type").equals("avion")){
                           //icons.add(new Icon(elem.getAttribute("type"),"src/ressources/avion.png" ));
                           return new ComposantAvion( point, new Icon(elem2.getAttribute("type"),"src/ressources/avion.png" ));
                        }
                    }
                }
            }
        }
        
        /*ArrayList<Icon> icons = new ArrayList<Icon>();
        NodeList list = element.getElementsByTagName("sortie");
        Node nNode = list.item(0);
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) nNode;
            if(elem.getAttribute("type").equals("metal")){
               //icons.add(new Icon(elem.getAttribute("type"),"src/ressources/metal.png" ));
               return new ComposantMetal( point, new Icon(elem.getAttribute("type"),"src/ressources/metal.png" ));
            }else if(elem.getAttribute("type").equals("moteur")){
               //icons.add(new Icon(elem.getAttribute("type"),"src/ressources/moteur.png" ));
               return new ComposantMoteur(point, new Icon(elem.getAttribute("type"),"src/ressources/moteur.png" ));
            }else if(elem.getAttribute("type").equals("aile")){
              // icons.add(new Icon(elem.getAttribute("type"),"src/ressources/aile.png" ));
               return new ComposantAile(point, new Icon(elem.getAttribute("type"),"src/ressources/aile.png" ));
            }else if(elem.getAttribute("type").equals("avion")){
               //icons.add(new Icon(elem.getAttribute("type"),"src/ressources/avion.png" ));
               return new ComposantAvion( point, new Icon(elem.getAttribute("type"),"src/ressources/avion.png" ));
            }
        }*/
        return null;
    }
    
    private ArrayList<Icon> getArrListIcon(Element element){
       Element elem1 =  (Element)element.getElementsByTagName("icones").item(0); 
       NodeList listIconUsine =  elem1.getElementsByTagName("icone");
       ArrayList<Icon> listObjectIcon = new ArrayList<Icon>();
        
       for(int i = 0; i < listIconUsine.getLength(); i++){
            Node nNode = listIconUsine.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                 Element elem = (Element) nNode;
                 listObjectIcon.add(new Icon(elem.getAttribute("type"), elem.getAttribute("path")));
            }
       }
       return listObjectIcon;
    }
    
    
    private ArrayList<Icon> getarrListIconUsine(Element element, String typeUsine){
        
       
        NodeList listTypeObjectUsine =  element.getElementsByTagName("usine"); 
        for(int i = 0; i < listTypeObjectUsine.getLength(); i++){
            
            Node nNode = listTypeObjectUsine.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element elem = (Element) nNode;
                if(elem.getAttribute("type").equals(typeUsine)){
                    return this.getArrListIcon(elem);
                }
            }
        }
        
        
        return null;
    }
    
    /*private ArrayList<Composant> getarrListComposantEntreeUsine(Element element, String typeUsine){
        
        return null;
    }*/
    
    public ArrayList<Chemin> getListCheminReseau(){
        Element Element1, Element2;
        ArrayList<Chemin> arrListChemin = new ArrayList<Chemin>();
         // read all Object of Usine in dashboard
        Element1 = (Element) doc.getElementsByTagName("simulation").item(0);
       
       // read all Object of Chemin in dashboard
        Element2 =  (Element)Element1.getElementsByTagName("chemins").item(0);
        NodeList listCheminReseau =  Element2.getElementsByTagName("chemin");
       
        for(int i = 0; i < listCheminReseau.getLength(); i++){
            Node nNode = listCheminReseau.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                 Element elem = (Element) nNode;
                 arrListChemin.add(new Chemin(elem.getAttribute("de"), elem.getAttribute("vers")));
            }
        }
        return arrListChemin;
    }
    
    
    
    
    
    public ArrayList<Usine> getListUsineReseau(){
        Element element = (Element) doc.getElementsByTagName("simulation").item(0);
        NodeList listUsineReseau =  element.getElementsByTagName("usine");
        ArrayList<Usine> listObjectUsine = new ArrayList<Usine>();
        
        for(int i = 0; i < listUsineReseau.getLength(); i++){
            Node nNode = listUsineReseau.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                 Element elem = (Element) nNode;
                 String test = elem.getAttribute("type");
                 listObjectUsine.add(this.createOjectUsine(elem.getAttribute("type"), elem.getAttribute("id")
                         ,elem.getAttribute("x"), elem.getAttribute("y")));
            }
        }
        
        return listObjectUsine;
    }
    
    
    private Usine createOjectUsine(String type, String id, String x, String y){
        Element mainElement1 = (Element) doc.getElementsByTagName("metadonnees").item(0);
        Point point= new Point(Integer.valueOf(x), Integer.valueOf(y));
        Identity identity= new Identity(type, id, point, null);
        Usine usine = null;
       // this.getComposantSortie(mainElement1, point, type);
        if(type.equals("usine-matiere")){
            identity= new Identity(type, id, point,  this.getarrListIconUsine(mainElement1, type));
            usine = new UsineMateriel(identity, 0, this.getComposantSortie(mainElement1, point, type));
        }else if(type.equals("usine-aile")){
            identity= new Identity(type, id, point,  this.getarrListIconUsine(mainElement1, type));
            usine = new UsineAile(identity, 0, this.getComposantSortie(mainElement1, point, type));
        }else if(type.equals("usine-assemblage")){
            identity= new Identity(type, id, point,  this.getarrListIconUsine(mainElement1, type));
            usine = new UsineAssemblage(identity, 0, this.getComposantSortie(mainElement1, point, type));
        }else if(type.equals("usine-moteur")){
            identity= new Identity(type, id, point,  this.getarrListIconUsine(mainElement1, type));
            usine = new UsineMoteur(identity, 0, this.getComposantSortie(mainElement1, point, type));
        }else if(type.equals("entrepot")){
            identity= new Identity(type, id, point,  this.getarrListIconUsine(mainElement1, type));
            usine = new Entrepot(identity, 0);
        }
        
        return usine;
    }
    
    
    
    
}
