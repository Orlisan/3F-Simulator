package terzaFSimulator;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Prof1Lezione {

	public static JButton manoAlzata;
	public static boolean eccolezion;
	
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
	    
	    ImageIcon labelMano = new ImageIcon("Texture\\alza_mano.png");
	    String stringMano = "Texture\\alza_mano.png";
	    File test = new File(stringMano);
	    System.out.println(test.getAbsolutePath());
	    System.out.println(test.exists());
	    manoAlzata = new JButton(labelMano);
	    manoAlzata.setBounds(320, 555, 100, 42);
	    manoAlzata.setOpaque(false);
	    panLezione.add(manoAlzata);
	    
	    panLezione.setComponentZOrder(profAllaLavagna, 0);
	    panLezione.setComponentZOrder(manoAlzata, 0);// DAVANTI
	    panLezione.setComponentZOrder(sfondoLez, 1);       // Sfondo DIETRO (Z-Order 1)
	    
	
	    Main.finestra.add(panLezione);
	    Main.finestra.setComponentZOrder(panLezione, 0);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	}
	
}
	