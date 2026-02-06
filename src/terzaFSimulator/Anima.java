package terzaFSimulator;

import javax.swing.JLabel;

import java.awt.*;

import javax.swing.*;

public class Anima extends JLabel {
	Persona p;
	public ImageIcon anima = new ImageIcon("Texture/anima.png");
	Rectangle bounds = new Rectangle(29, 29);
	
	
	public Anima(Persona p) {
		this.p = p;
		this.setOpaque(false);
		this.setBounds(bounds);
		this.setIcon(anima);
	}
	public Anima(String text, Persona p) {
		super(text);
		this.setOpaque(false);
		this.p = p;
		this.setBounds(bounds);
		this.setIcon(anima);
	}
	public Anima(ImageIcon icon, Persona p) {
		super(icon);
		this.setOpaque(false);
		this.p = p;
		this.setBounds(bounds);
		this.setIcon(anima);
	}
}
