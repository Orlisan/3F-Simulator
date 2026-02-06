package terzaFSimulator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class FinestraNote {
	static JFrame finNote;
	
	public static boolean noteAperte = false;
	
	public static void creaNote() {
		
		finNote = new JFrame("3F Simulator Notes");
		finNote.setSize(600, 600);
		
		finNote.setDefaultCloseOperation(finNote.DISPOSE_ON_CLOSE);
		JPanel panelNote = new JPanel();
		
		JLabel nota1 = new JLabel("<html><b> <div style='padding: 10px'>Nota 1: </b>  Avrai sicuramente notato che<br>"
				+ "la disposizioni dei banchi è obsoleta<br>"
				+"questo è causato dal fatto che<br>"
				+"quando ho creato textures e collisioni<br>"
				+ "c'era ancora la vecchia disposizione,<br>"
				+ "dato che è lunghissimo<br>"
				+ "sistemare tutto<br>"
				+ "accontentatevi dei vecchi tempi. ☻</html>");
		
		nota1.setBounds(0, 0, 480, 100); // ← QUESTI BOUNDS
		nota1.setForeground(Color.BLACK);
		JLabel nota2 = new JLabel("<html><b><div style='padding: 10px'> Nota 2: </b> È possibile che durante<br>"
				+ "il movimento dell'anima o del<br>"
				+ "personaggio, esso/a inizi a muoversi<br>"
				+ "in automatico verso una direzione<br>"
				+ "per risolvere ciò bisogna<br>"
				+ "fare come una rotazione completa di 360°<br>"
				+ "ovvero premere Su, Destra, Giù e Sinistra <br>"
				+ "in quest'ordine velocemente<br>"
				+ "Se non funziona schiacciare tasti a caso<br>"
				+ "finchè non si sistema");
		nota2.setBounds(0, 0, 480, 100);
		nota2.setForeground(Color.BLACK);
		
		panelNote.add(nota1);
		panelNote.add(nota2);
		
		
		finNote.add(panelNote);
		finNote.setVisible(true);
		
		noteAperte = true;
		
	}
	
	
	
}
