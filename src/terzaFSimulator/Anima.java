package terzaFSimulator;

import javax.swing.JLabel;

import java.awt.*;

import javax.swing.*;

public class Anima extends JLabel {
	Persona p;
	static String pathAnima = "Texture/anima.png";
	public ImageIcon anima = new ImageIcon(pathAnima);
	Rectangle bounds = new Rectangle(29, 29);
	static boolean isCooldown = false;
	static int tCoolDown = 3;
	
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
	public double getVita() {
		return Main.personaggioSelezionato.vita;
	}
	
	
	
	static void cooldown() {
		new Thread(() -> {
			isCooldown = true;
			for(int i = 0; i < tCoolDown; i++) {
				SwingUtilities.invokeLater(() -> {
					MetodiUtili.trasparenza(pathAnima, 0.5f);
				});
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			isCooldown = false;
		}).start();
	
	}
	
	int getCoolDown() {
		return this.tCoolDown;
	}
	
	void setCoolDown(int newCoolDown) {
		this.tCoolDown = newCoolDown;
	}
}
