package terzaFSimulator;

import javax.swing.*;
import java.awt.*;

public class Prof1Lezione {

	static JPanel panLezione = new JPanel();
	
	static void creaAmbiente(){
	    panLezione.setBounds(0, 0, 880, 671);
	    panLezione.setLayout(null);
	    
	    JLabel sfondoLez = new JLabel(Main.inizio);
	    sfondoLez.setBounds(0, 0, 880, 671);
	    panLezione.add(sfondoLez);
	    
	    JLabel profAllaLavagna = new JLabel(new ImageIcon("Texture\\prof_lavagna.png"));
	    profAllaLavagna.setBounds(50, 100, 200, 200);
	    profAllaLavagna.setOpaque(false);
	    panLezione.add(profAllaLavagna);
	    
	    panLezione.setComponentZOrder(profAllaLavagna, 0); // Prof DAVANTI (Z-Order 0)
	    panLezione.setComponentZOrder(sfondoLez, 1);       // Sfondo DIETRO (Z-Order 1)
	    
	    Main.finestra.add(panLezione);
	    Main.finestra.setComponentZOrder(panLezione, 0);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	}
	
}
	