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
	    profAllaLavagna.setBounds(200, 100, 200, 200);
	    profAllaLavagna.setOpaque(false);
	    panLezione.add(profAllaLavagna);
	    
	    JLabel lavagna = new JLabel(new ImageIcon("Texture\\lavagna.png"));
	    lavagna.setBounds(250, 50, 439, 345);
	    lavagna.setOpaque(false);
	    panLezione.add(lavagna);
	    
	    JLabel coseDette = new JLabel("");
	    coseDette.setFont(new Font("Serif", Font.BOLD, 20));
	    
	    ImageIcon labelMano = new ImageIcon("Texture\\alza_mano.png");
	    manoAlzata = new JButton(labelMano);
	    manoAlzata.setBounds(90, 450, 200, 84);
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
	

	
	public static void introLez() {
		
	}
}