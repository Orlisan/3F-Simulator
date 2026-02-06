package terzaFSimulator;

import javax.swing.JButton;

public class Item extends JButton{
	String nome;
	int[] effetti; //Se un effetto: solo alterazione vita, Se 2 effetti, modificamento cooldown, se 3 effetti, attacco player alterato
					//Se 4 effetti: difesa player aumentata (danno enemy diminuito
	Enemy e;
	String description;
	
	public Item(String nome, String description, int[] effetti, Enemy enemy) {
		super(nome);
		this.nome = nome;
		this.effetti = effetti;
		this.e = enemy;
		this.description = description;
		
		this.addActionListener(g -> {
			if(effetti.length == 1) {
				Main.personaggioSelezionato.alteraVita(effetti[0]);
			}else if(effetti.length == 2) {
				e.panel.anima.setCoolDown(effetti[1]);
			}else if(effetti.length == 3) {
				Main.personaggioSelezionato.setDanno(effetti[2]);
			}else if(effetti.length == 4) {
				Main.personaggioSelezionato.setDifesa(effetti[3]);
			}
		});
	}
	
	
}
