package terzaFSimulator;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Prof1Lezione {

	public static JButton manoAlzata;
	
	static JPanel panLezione = new JPanel();
	
	static void creaAmbiente() {
		Main.finestra.remove(Main.mappaClasse);
		
		
	    panLezione.setBounds(0, 0, 880, 671);
	    panLezione.setLayout(null);
	    
	    JLabel sfondoLez = new JLabel(Main.inizio);
	    sfondoLez.setBounds(0, 0, 880, 671);
	    panLezione.add(sfondoLez);
	    
	    JLabel profAllaLavagna = new JLabel(new ImageIcon("Texture\\prof_lavagna.png"));
	    profAllaLavagna.setBounds(50, 100, 200, 200);
	    profAllaLavagna.setOpaque(false);
	    panLezione.add(profAllaLavagna);
	    
	    JLabel lavagna = new JLabel(new ImageIcon("Texture\\lavagna.png"));
	    lavagna.setBounds(350, 70, 439, 345);
	    lavagna.setOpaque(false);
	    panLezione.add(lavagna);
	    
	    ImageIcon labelMano = new ImageIcon("Texture\\alza_mano.png");
	    manoAlzata = new JButton(labelMano);
	    manoAlzata.setBounds(100, 400, 200, 84);
	    manoAlzata.setOpaque(false);
	    panLezione.add(manoAlzata);
	     
	    panLezione.setComponentZOrder(profAllaLavagna, 0);
	    panLezione.setComponentZOrder(manoAlzata, 1);
	    panLezione.setComponentZOrder(lavagna, 2);// DAVANTI
	    panLezione.setComponentZOrder(sfondoLez, 3);       // Sfondo DIETRO (Z-Order 1)
	    
	
	    Main.finestra.add(panLezione);
	    Main.finestra.setComponentZOrder(panLezione, 0);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	}
	
}
	