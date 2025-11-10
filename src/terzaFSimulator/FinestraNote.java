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
		
		JLabel nota1 = new JLabel("<html><b> Nota 1: </b>  Avrai sicuramente notato che<br>"
				+ "la disposizioni dei banchi è obsoleta<br>"
				+"questo è causato dal fatto che<br>"
				+"quando ho creato textures e collisioni<br>"
				+ "c'era ancora la vecchia disposizione,<br>"
				+ "dato che è lunghissimo<br>"
				+ "sistemare tutto<br>"
				+ "accontentatevi dei vecchi tempi. ☻</html>");
		
		nota1.setBounds(0, 0, 480, 100); // ← QUESTI BOUNDS
		nota1.setForeground(Color.BLACK);
		nota1.setFont(new Font("Arial", Font.PLAIN, 14));
		panelNote.add(nota1);
		
		finNote.add(panelNote);
		finNote.setVisible(true);
		
		noteAperte = true;
		
	}
	
	
	
}
