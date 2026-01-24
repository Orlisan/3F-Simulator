package terzaFSimulator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import terzaFSimulator.Prof1.UmoriProf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MetodiUtili {
	
	public static Clip clipCorrente = null;
	 public static Font wingdingsFont;
	  public static Font determinationFont; // 👈 Aggiungi questo
	    
	    static {
	        try {
	            // 1. CARICA WINGDINGS
	             wingdingsFont = Font.createFont(Font.TRUETYPE_FONT, 
	             new File("wingding.ttf")).deriveFont(15f);
	            
	            // 2. CARICA DETERMINATION
	            determinationFont = Font.createFont(Font.TRUETYPE_FONT, 
	                new File("determination.otf")).deriveFont(15f); // 👈 Dimensioni diverse se vuoi
	            
	            // Registra entrambi
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(wingdingsFont);
	            ge.registerFont(determinationFont);
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Fallback
	            wingdingsFont = new Font("Serif", Font.PLAIN, 24);
	            determinationFont = new Font("SansSerif", Font.BOLD, 20);
	        }
	    }
	
	public static String forseAperta(String chiusa, String aperta, boolean porta) {
		String forseAperta = null;
		if(porta) {
			forseAperta = aperta;
			
		}
		if(!porta) {
			forseAperta = chiusa;
		} 
		
		return forseAperta;
	}
	
	public static Color qualeUmore(UmoriProf umore) {
		if(umore == UmoriProf.CALMO || umore == UmoriProf.LUSINGATO) {
			return Color.YELLOW;
		}else if(umore == UmoriProf.ESTASIATO) {
			return Color.GREEN;
		}else if(umore == UmoriProf.CONTENTO) {
			return Color.CYAN;
		}else if(umore == UmoriProf.IRRITATO || umore == UmoriProf.ARRABBIATO) {
			return Color.ORANGE;
		}else if(umore == UmoriProf.INDEMONIATO) {
			return Color.RED;
		}else if(umore == UmoriProf.OMICIDA) {
			return Color.DARK_GRAY;
		}else {
			return Color.WHITE;
		}
	}
	
	public static String cripta(String fraseOrigin) {
		StringBuilder parolaCript = new StringBuilder();
		
		HashMap<Character, Character> cript = new HashMap<Character, Character>();
		cript.put('A', 'g');
		cript.put('a', 'G');
		cript.put('B', 'F');
		cript.put('b', 'f');
		cript.put('C', 'h');
		cript.put('c', 'H');
		cript.put('D', 'J');
		cript.put('d', 'j');
		cript.put('E', 'T');
		cript.put('e', 't');
		cript.put('F', 'p');
		cript.put('f', 'P');
		cript.put('G', 'z');
		cript.put('g', 'Z');
		cript.put('H', 'M');
		cript.put('h', 'm');
		cript.put('I', 'l');
		cript.put('i', 'L');
		cript.put('J', 'a');
		cript.put('j', 'A');
		cript.put('K', 'N');
		cript.put('k', 'n');
		cript.put('L', 'S');
		cript.put('l', 's');
		cript.put('M', 'D');
		cript.put('m', 'd');
		cript.put('N', 'o');
		cript.put('n', 'O');
		cript.put('O', 'b');
		cript.put('o', 'B');
		cript.put('P', 'E');
		cript.put('p', 'e');
		cript.put('Q', 'c');
		cript.put('q', 'C');
		cript.put('R', 'k');
		cript.put('r', 'K');
		cript.put('S', 'I');
		cript.put('s', 'i');
		cript.put('T', 'R');
		cript.put('t', 'r');
		cript.put('U', 'y');
		cript.put('u', 'Y');
		cript.put('V', 'W');
		cript.put('v', 'w');
		cript.put('W', 'x');
		cript.put('w', 'X');
		cript.put('X', 'V');
		cript.put('x', 'v');
		cript.put('Y', 'u');
		cript.put('y', 'U');
		cript.put('Z', 'Q');
		cript.put('z', 'q');
		
		cript.put('0', '6');
		cript.put('1', '3');
		cript.put('2', '9');
		cript.put('3', '5');
		cript.put('4', '2');
		cript.put('5', '8');
		cript.put('6', '4');
		cript.put('7', '0');
		cript.put('8', '1');
		cript.put('9', '7');
		
		for(Character lettera: fraseOrigin.toCharArray()) {
			Character letteraCript = cript.get(lettera);
			
			if(letteraCript != null) {
				parolaCript.append(letteraCript);
			}else {
				System.out.println("ERRORE DI CRITTOGRAFIA RILEVATO!!");
			}
		}
		
		return parolaCript.toString();
	}
	
	public static String decripta(String parCript) {
        StringBuilder paroladeCript = new StringBuilder();
		
		HashMap<Character, Character> decript = new HashMap<Character, Character>();
		decript.put('g', 'A');
		decript.put('G', 'a');
		decript.put('F', 'B');
		decript.put('f', 'b');
		decript.put('h', 'C');
		decript.put('H', 'c');
		decript.put('J', 'D');
		decript.put('j', 'd');
		decript.put('T', 'E');
		decript.put('t', 'e');
		decript.put('p', 'F');
		decript.put('P', 'f');
		decript.put('z', 'G');
		decript.put('Z', 'g');
		decript.put('M', 'H');
		decript.put('m', 'h');
		decript.put('l', 'I');
		decript.put('L', 'i');
		decript.put('a', 'J');
		decript.put('A', 'j');
		decript.put('N', 'K');
		decript.put('n', 'k');
		decript.put('S', 'L');
		decript.put('s', 'l');
		decript.put('D', 'M');
		decript.put('d', 'm');
		decript.put('o', 'N');
		decript.put('O', 'n');
		decript.put('b', 'O');
		decript.put('B', 'o');
		decript.put('E', 'P');
		decript.put('e', 'p');
		decript.put('c', 'Q');
		decript.put('C', 'q');
		decript.put('k', 'R');
		decript.put('K', 'r');
		decript.put('I', 'S');
		decript.put('i', 's');
		decript.put('R', 'T');
		decript.put('r', 't');
		decript.put('y', 'U');
		decript.put('Y', 'u');
		decript.put('W', 'V');
		decript.put('w', 'v');
		decript.put('x', 'W');
		decript.put('X', 'w');
		decript.put('V', 'X');
		decript.put('v', 'x');
		decript.put('u', 'Y');
		decript.put('U', 'y');
		decript.put('Q', 'Z');
		decript.put('q', 'z');
		
		decript.put('6', '0');
		decript.put('3', '1');
		decript.put('9', '2');
		decript.put('5', '3');
		decript.put('2', '4');
		decript.put('8', '5');
		decript.put('4', '6');
		decript.put('0', '7');
		decript.put('1', '8');
		decript.put('7', '9');
		
		for(Character lettera: parCript.toCharArray()) {
			Character letteradeCript = decript.get(lettera);
			
			if(letteradeCript != null) {
				paroladeCript.append(letteradeCript);
			}else {
				System.out.println("ERRORE DI DECRITTOGRAFIA RILEVATO!!");
			}
		}
		
		
	     
		
		return paroladeCript.toString();
}
	static int s(double ms) {
		return  (int) ms*1000;
	}
	
	public static void audio(String audioPath) {
	    // Crea un nuovo thread
	    new Thread(() -> {
	        try {
	            // SEMPLICE: ferma tutto subito
	            if (clipCorrente != null) {
	                clipCorrente.stop();
	                clipCorrente.close();
	                clipCorrente = null;
	            }
	            
	            // Breve pausa
	            Thread.sleep(10);
	            
	            // Carica e avvia la nuova
	            File audio = new File(audioPath);
	            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio);
	            Clip clip = AudioSystem.getClip();
	            
	            clipCorrente = clip;
	            clip.open(audioStream);
	            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop infinito e basta
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }).start();
	}
	
	public static ImageIcon trasparenza(String path, float trasparenza) {
	    ImageIcon iconaOriginale = new ImageIcon(path);
	    BufferedImage imgTrasparente = new BufferedImage(
	        iconaOriginale.getIconWidth(),
	        iconaOriginale.getIconHeight(),
	        BufferedImage.TYPE_INT_ARGB
	    );
	    
	    Graphics2D g2d = imgTrasparente.createGraphics();
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, trasparenza));
	    g2d.drawImage(iconaOriginale.getImage(), 0, 0, null);
	    g2d.dispose();
	    
	    return new ImageIcon(imgTrasparente);
	}
	
}