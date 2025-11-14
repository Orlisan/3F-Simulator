package terzaFSimulator;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Prof1Lezione {

	public static JButton manoAlzata;
	
	static JPanel panLezione = new JPanel();
	static JLabel coseDette;
	
	
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
	    
	    coseDette = new JLabel("");
	    coseDette.setBounds(75, 70,200, 200);
	    coseDette.setFont(new Font("Serif", Font.BOLD, 20));
	    coseDette.setForeground(Color.WHITE);
	    panLezione.add(coseDette);
	    
	    ImageIcon labelMano = new ImageIcon("Texture\\alza_mano.png");
	    manoAlzata = new JButton(labelMano);
	    manoAlzata.setBounds(90, 450, 200, 84);
	    manoAlzata.setOpaque(false);
	    panLezione.add(manoAlzata);
	     
	    panLezione.setComponentZOrder(coseDette, 0);
	    panLezione.setComponentZOrder(profAllaLavagna, 1);
	    panLezione.setComponentZOrder(manoAlzata,2 );
	    panLezione.setComponentZOrder(lavagna, 3);// DAVANTI
	    panLezione.setComponentZOrder(sfondoLez, 4);       // Sfondo DIETRO (Z-Order 1)
	    
	
	    Main.finestra.add(panLezione);
	    Main.finestra.setComponentZOrder(panLezione, 0);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	    
	    introLez();
	}
	

	
	public static void introLez() {
		
		Thread threadScrivProf = new Thread(() -> {
		String messaggio = "Oggi Faremo <br> lezione di <br> Trigonometria, <br> il nuovo <br> semplicissimo <br> argomento";
		StringBuilder messaggioFinOra = new StringBuilder("<html>");
             
		class stampa {
			void stampa(char[] messaggioDaDire) {
				for(char ilMessaggio: messaggioDaDire) {
		            	 messaggioFinOra.append(ilMessaggio);
		            	 coseDette.setText(messaggioFinOra.toString() + "</html>");
		            	 try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		             }
			}
		
		}
		
		stampa stampa = new stampa(); //Guarda come sono sofisticato
		stampa.stampa(messaggio.toCharArray());
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		messaggio = "A proposito,<br> dopodomani <br>avremo la <br> verifica di <br> questo argomento";
		messaggioFinOra.setLength(0);
		messaggioFinOra.append("<html>");
		
		stampa.stampa(messaggio.toCharArray());
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		messaggio = "Ma non <br> preuccupatevi, <br>la verifica <br>durerà ben <br>5 minuti e <br>avrà solamente <br>100 domande!";
		messaggioFinOra.setLength(0);
		messaggioFinOra.append("<html>");
		stampa.stampa(messaggio.toCharArray());
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		messaggio = "Più facile di <br>così allora <br>potreste <br>prendere 6,<br> che è dopo <br>il massimo ☺ ";
		messaggioFinOra.setLength(0);
		messaggioFinOra.append("<html>");
		stampa.stampa(messaggio.toCharArray());
		
		});
		threadScrivProf.start();
	}
}