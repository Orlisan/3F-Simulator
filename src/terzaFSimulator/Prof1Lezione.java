package terzaFSimulator;

import javax.swing.*;

import terzaFSimulator.Prof1.UmoriProf;

import java.awt.*;
import java.io.File;
import java.util.Random;
import java.lang.reflect.Method;

public class Prof1Lezione {

	public static JButton manoAlzata;
	
	static JPanel panLezione = new JPanel();
	
	static JLabel coseDette;
	static JLabel titoloLav;
	static JLabel scrittaLav;
	
	
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
	    coseDette.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
	    panLezione.add(coseDette);
	    
	    ImageIcon labelMano = new ImageIcon("Texture\\alza_mano.png");
	    manoAlzata = new JButton(labelMano);
	    manoAlzata.setBounds(90, 450, 200, 84);
	    manoAlzata.setOpaque(false);
	    panLezione.add(manoAlzata);
	    
	    titoloLav = new JLabel("prova");
		titoloLav.setBounds(415, 110, 120, 40);
		titoloLav.setFont(new Font("Serif", Font.BOLD, 40));
		panLezione.add(titoloLav);
		
		scrittaLav = new JLabel("<html>prova prova prova prova prova <br> prova prova prova prova prova <br> prova prova prova prova prova </html> ");
	scrittaLav.setBounds(315, 130, 320, 240);//DISPONIBILI 29 CARATTERI PER RIGA!!!!!!!YAY
		scrittaLav.setFont(new Font("Serif", Font.PLAIN, 25));
		panLezione.add(scrittaLav);
	     
	    panLezione.setComponentZOrder(coseDette, 0);
	    panLezione.setComponentZOrder(profAllaLavagna, 1);
	    panLezione.setComponentZOrder(manoAlzata,2 );
	    panLezione.setComponentZOrder(scrittaLav, 3);
	    panLezione.setComponentZOrder(titoloLav, 4); //Dev'essere davanti alla lavagna
	    panLezione.setComponentZOrder(lavagna, 5);
	    panLezione.setComponentZOrder(sfondoLez, 6);      //lo sfondo dev'essere sempre ultimo
	    
	    
	    Main.finestra.add(panLezione);
	    Main.finestra.setComponentZOrder(panLezione, 0);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	    
	    introLez();
	    
	}
	

	
	public static void introLez() {
		
		Thread threadScrivProf = new Thread(() -> {
		
        stampa("Oggi Faremo <br> lezione di <br> Trigonometria, <br> il nuovo <br> semplicissimo <br> argomento", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		stampa("A proposito,<br> dopodomani <br>avremo la <br> verifica di <br> questo argomento", coseDette);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	
		stampa("Ma non <br> preuccupatevi, <br>la verifica <br>durerà ben <br>5 minuti e <br>avrà solamente <br>100 domande!", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		stampa("Più facile di <br>così allora <br>potreste <br>prendere 6,<br> che è dopo <br>il massimo ☺ ", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		stampa( "Adesso inizio<br> a spiegare,<br> vi prometto che<br> userò un <br>linguaggio molto <br>complicato, <br>come volete voi", coseDette);
		spiegaQualcosa();
		});
		threadScrivProf.start();
	}
		
	
		static void spiegaSeno() {
			stampa("la lezione <br> di questi <br>3 minuti,<br> si tratterà<br> sulla funzione<br> sin()", coseDette);
			
			
		}
		
		static void spiegaQualcosa() {
			Random random = new Random();
			
			try {
				Method[] metodiSpiega = {
						 Prof1Lezione.class.getDeclaredMethod("spiegaSeno"),
				};
				
				int indiceCasuale = random.nextInt(metodiSpiega.length);
		        try {
		            metodiSpiega[indiceCasuale].invoke(null); 
		       
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			} catch (NoSuchMethodException | SecurityException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Metodo Inesistente");
			}
			
		}
		
		
		
		static void stampa(String messaggio, JLabel labelDaMod) {
			
			StringBuilder messaggioFinOra = new StringBuilder("<html>");
			char[] messaggioDaDire = messaggio.toCharArray();
			
			for(char ilMessaggio: messaggioDaDire) {
	            	
					messaggioFinOra.append(ilMessaggio);
	            	 labelDaMod.setText(messaggioFinOra.toString() + "</html>");
	            	 try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	             }
		}
}