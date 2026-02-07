package terzaFSimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static terzaFSimulator.MetodiUtili.*;
import javax.swing.*;

import terzaFSimulator.Main.Direzione;

import java.util.ArrayList;
public class Persona { 
	
	ArrayList<Item> inventario = new ArrayList<Item>();
	
	
	String nome;
	
	String cognome;
	int età;
	JLabel icona;
	public int posizioneX;
	public int posizioneY;
	public int oro;
	public double vita;
	
	double danno = 10;
	double difesa = 1.5;
	
	public String contenuto;
	
	
	Persona(String nome, String cognome, int età, JLabel icona, int posizioneX, int posizioneY, int oro, double vita) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.età = età;
		this.icona = icona;
		
		this.icona.setOpaque(false);
		this.icona.setBounds(posizioneX, posizioneY, 150, 150);
		
		this.posizioneX = posizioneX;
		this.posizioneY = posizioneY;
		this.oro = oro;
		this.vita = vita;		
	}
	
	public void muovi(int movimentoX, int movimentoY) {
        posizioneX += movimentoX;
        posizioneY += movimentoY;
        icona.setLocation(posizioneX, posizioneY);
    }
    
    public void impostaPosizione(int nuovaX, int nuovaY) {
        posizioneX = nuovaX;
        posizioneY = nuovaY;
        icona.setLocation(posizioneX, posizioneY);
    }
    
    public void seTocca() {
    	if(MetodiUtili.seTocca(posizioneX, posizioneY)) {
    			if(Main.facciata == Direzione.SU) {
    				Main.nonToccaSu = false;
    			}
    			if(Main.facciata == Direzione.GIU) {
    				Main.nonToccaGiù = false;
    			}
    		
    			if(Main.facciata == Direzione.SINISTRA) {
    				Main.nonToccaASinistra = false;
    			}
    			if(Main.facciata == Direzione.DESTRA) {
    				Main.nonToccaADestra = false;
    			}
    			
    		
    	}else {
    		Main.nonToccaSu = true;
            Main.nonToccaGiù = true;
            Main.nonToccaASinistra = true;
            Main.nonToccaADestra = true;
    	}
    }
    
    public int getOro() {
    	return this.oro;
    }
    
    public void setOro(int newOro) {
    	if(this.getOro() < 10) {
    		this.oro = newOro + 50;
    	}else if(this.getOro() > 1000000) {
    		this.oro = newOro - 50;
    	} else {
    		this.oro = newOro;
    	}
    }
    
    public double getDanno() {
    	return danno;
    }
    
    public void setDanno(double newDanno) {
    	danno = newDanno;
    }
    
    public double getDifesa() {
    	return this.difesa;
    }
    
    public void setDifesa(double d) {
    	this.difesa += d;
    }
    public void alteraVita(double vitaDaAlterare) {
	    if(!Anima.isCooldown) {
	        double d = (this.difesa < 0.1) ? 0.1 : this.difesa;
	        if((0 - vitaDaAlterare) > 0) {
	        	double veraVita = vitaDaAlterare / d;
	        	this.vita += veraVita;
	        }else {
	        	this.vita += vitaDaAlterare;
	        }
	        
	        if (this.vita > 100) this.vita = 100;
	        if (this.vita < 0) this.vita = 0;
	        if(Main.bidello.panel != null) {
	        CombatPanel.panel.checkVita();
	        }else {
	        	System.out.println("Errore, pannello null!");
	        }
	        if(vitaDaAlterare < 0) {
	            Anima.cooldown();
	        }
	        
	    }
	}

}
