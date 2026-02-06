package terzaFSimulator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite extends JLabel {
    String tag; 
    //Sprite is simply JLabel with a tag
   
    public Sprite(ImageIcon icon, String tag) {
        super(icon); 
        this.tag = tag;
        setOpaque(false);
    }
    

    public Sprite(String text, String tag) {
        super(text);
        this.tag = tag;
        setOpaque(false);
    }
    
  
    public Sprite(String tag) {
        super();
        this.tag = tag;
        setOpaque(false);
    }
    
    
    public String getTag() {
    	return this.tag;
    }
    
    public void setTag(String newTag) {
    	this.tag = newTag;
    }
}