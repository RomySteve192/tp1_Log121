/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;



/**
 *
 * @author Romy Steve
 */
public class Icon {
    
    private String type;
    private String path;
    
    public Icon(String typ, String pat){
        this.type = typ;
        this.path = pat;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public void setPath(String pat){
        this.path = pat;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String typ){
        this.type = typ;
    }
    
}
