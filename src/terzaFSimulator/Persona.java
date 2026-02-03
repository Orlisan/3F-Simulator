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

public class Persona { 
	 String nome;
	
	String cognome;
	int età;
	JLabel icona;
	public int posizioneX;
	public int posizioneY;
	public int oro;
	public int vita;
	
	
	public String contenuto;
	
	
	Persona(String nome, String cognome, int età, JLabel icona, int posizioneX, int posizioneY, int oro, int vita) {
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

}
