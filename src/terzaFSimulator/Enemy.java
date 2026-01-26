package terzaFSimulator;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;
import java.util.function.*;

public class Enemy extends JLabel{
	String nome;
	
	int danno;
	int salute;
	int difesa;
	
	String[] frasi;
	boolean ordineFrasi;
	
	List<Consumer<Persona>> attacchi;
	boolean ordineAttacchi;
	
	List<Consumer<Persona>> metodiSpeciali; //Opzionale, questo per cose particolari
	
	ImageIcon spriteOverworld;
	int xOver;
	int yOver;
	String metodoDiInit;
	JLabel[] sprites;
	int[][] posSprites;
	int numSprites;// X, Y HEIGHT, WITHGT (o inveriti), X, Y Height, ecc...
	
	byte movOver;
	
	HashMap<String , ActionListener> azioni; //Cose fattibili nel Menù ACT
	
	Enemy(String nome, int danno, int salute, int difesa, String[] frasi, boolean ordineFrasi, List<Consumer<Persona>> attacchi, boolean ordineAttacchi, List<Consumer<Persona>> metodiSpeciali, ImageIcon spriteOverworld, int xOver, int yOver, String metodoDiInit, JLabel[] sprites, int[][] posSprites, int numSprites, HashMap<String, ActionListener> azioni, byte movOver) {
		this.nome = nome;
		this.danno = danno;
		this.salute = salute;
		this.difesa = difesa;
		
		this.frasi = frasi;
		this.ordineFrasi = ordineFrasi;
		this.attacchi = attacchi;
		this.ordineAttacchi = ordineAttacchi;
		this.metodiSpeciali = metodiSpeciali;
		
		this.spriteOverworld = spriteOverworld;
		this.xOver = xOver;
		this.yOver = yOver;
		this.sprites = sprites;
		this.numSprites = numSprites;
		this.posSprites = posSprites;
		this.azioni = azioni;
		this.movOver = movOver;
		
		this.spawn();
		if(movOver == 0) {
			this.muoviInOrizzontale();
		}else if(movOver == 1) {
			this.muoviInVerticale();
		}else if(movOver == 2) {
			this.muoviAInseguimento();
		}
	}
	
	void spawn() {
		   this.setVisible(false);
		   MetodiUtili.getCurrentPanel().add(this);
		   this.setIcon(this.spriteOverworld);
		   this.setSize(this.spriteOverworld.getIconWidth(), this.spriteOverworld.getIconHeight());
		   this.setLocation(this.xOver, this.yOver);
		   MetodiUtili.getCurrentPanel().setComponentZOrder(Main.personaggioSelezionato.icona, 0);
		   this.setVisible(true);
		   MetodiUtili.getCurrentPanel().revalidate();
		   MetodiUtili.getCurrentPanel().repaint();
		}
	
	void muoviInOrizzontale() {
		boolean[] dir = {true}; //TRUE: DESTRA, FALSE SINISTRA
		Timer timer = new Timer(10, e -> {
			if(MetodiUtili.seTocca(this.xOver -34, this.yOver)) {
			dir[0] = !dir[0];
		}
		
			if(dir[0]) {
			this.setLocation(this.xOver +1, this.yOver);
			this.xOver++;
		}else if(!dir[0]) {
			this.setLocation(this.xOver -1, this.yOver);
			this.xOver--;
		}
		});
		timer.setRepeats(true);
		timer.start();
		
	}
	
	void muoviInVerticale() {
		boolean[] dir = {true}; //TRUE: SU, FALSE GIù
		Timer timer = new Timer(10, e -> {
			if(MetodiUtili.seTocca(this.xOver, this.yOver -34)) {
			dir[0] = !dir[0];
		}
		
			if(dir[0]) {
			this.setLocation(this.xOver, this.yOver +1);
			this.yOver++;
		}else if(!dir[0]) {
			this.setLocation(this.xOver, this.yOver -1);
			this.yOver--;
		}
		});
		timer.setRepeats(true);
		timer.start();
	}
	
	void muoviAInseguimento() {
	    boolean[] si = {false};
	    
	    boolean[] xBS = {true};
	    boolean[] xBD = {true};
	    
	    boolean[] yBU = {true};
	    boolean[] yBD = {true};
	    
	    Timer timer = new Timer(10, e -> {
	        if(!MetodiUtili.seTocca(this.xOver -33, this.yOver -34)) {
	            xBD[0] = false;
	        } else {
	            xBD[0] = true;
	        }
	        if(!MetodiUtili.seTocca(this.xOver -35, this.yOver -34)) {
	            xBS[0] = false;
	        } else {
	            xBS[0] = true;
	        }
	        if(!MetodiUtili.seTocca(this.xOver -34, this.yOver -33)) {
	            yBD[0] = false;
	        } else {
	            yBD[0] = true;
	        }
	        if(!MetodiUtili.seTocca(this.xOver -34, this.yOver -35)) {
	            yBU[0] = false;
	        } else {
	            yBU[0] = true;
	        }
	        
	        si[0] = true;
	        
	        int playerX = Main.personaggioSelezionato.posizioneX + 60;
	        int playerY = Main.personaggioSelezionato.posizioneY +55;

	        int difX = playerX - this.xOver;
	        int difY = playerY - this.yOver;
	        
	        if(Math.abs(difX) > Math.abs(difY)) {
	            if(difX > 0 && !xBD[0]) {
	                this.xOver++;
	                this.setLocation(this.xOver, this.yOver);
	            } else if(difX < 0 && !xBS[0]) {
	                this.xOver--;
	                this.setLocation(this.xOver, this.yOver);
	            } else if(difY > 0 && !yBD[0]) {
	                this.yOver++;
	                this.setLocation(this.xOver, this.yOver);
	            } else if(difY < 0 && !yBU[0]) {
	                this.yOver--;
	                this.setLocation(this.xOver, this.yOver);
	            }
	        } else {
	            if(difY > 0 && !yBD[0]) {
	                this.yOver++;
	                this.setLocation(this.xOver, this.yOver);
	            } else if(difY < 0 && !yBU[0]) {
	                this.yOver--;
	                this.setLocation(this.xOver, this.yOver);
	            } else if(difX > 0 && !xBD[0]) {
	                this.xOver++;
	                this.setLocation(this.xOver, this.yOver);
	            } else if(difX < 0 && !xBS[0]) {
	                this.xOver--;
	                this.setLocation(this.xOver, this.yOver);
	            }
	        }
	    });
	    
	    timer.setRepeats(true);
	    timer.start();
	}
	
}
