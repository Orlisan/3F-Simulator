package terzaFSimulator;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;

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
				Main.personaggioSelezionato.alteraVita(effetti[0]);
				e.panel.anima.setCoolDown(effetti[1]);
			}else if(effetti.length == 3) {
				Main.personaggioSelezionato.alteraVita(effetti[0]);
				e.panel.anima.setCoolDown(effetti[1]);
				Main.personaggioSelezionato.setDanno(effetti[2]);
			}else if(effetti.length == 4) {
				Main.personaggioSelezionato.alteraVita(effetti[0]);
				e.panel.anima.setCoolDown(effetti[1]);
				Main.personaggioSelezionato.setDanno(effetti[2]);
				Main.personaggioSelezionato.setDifesa(effetti[3]);
			}
			JLabel descrizione = new JLabel();
		    descrizione.setBounds(30, 30, 600, 100);
		    descrizione.setForeground(Color.WHITE);
		    Container parentContainer = this.getParent();
		    
		    if (parentContainer != null && parentContainer == e.panel.areInv) 
		        parentContainer.removeAll(); 
		        parentContainer.add(descrizione);
		        e.panel.areInv.revalidate();
	            e.panel.areInv.repaint();
	            
		        new Thread(() -> {
		            StringBuilder messaggioFinOra = new StringBuilder("<html>");
		            char[] messaggioDaDire = description.toCharArray();
		            
		            for (char c : messaggioDaDire) {
		                messaggioFinOra.append(c);
		                
		                SwingUtilities.invokeLater(() -> {
		                    descrizione.setText(messaggioFinOra.toString() + "</html>");
		                    descrizione.setForeground(Color.WHITE); 
		                });
		                
		                try {
		                    Thread.sleep(50);
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		            
		            // 5. DOPO stampa, avvia timer
		            SwingUtilities.invokeLater(() -> {
		                Timer t = new Timer(1500, y -> {
		                    parentContainer.remove(descrizione);
		                    
		                    if(Main.personaggioSelezionato.inventario != null) {
		                        Main.personaggioSelezionato.inventario.remove(this);
		                        e.panel.areInv.revalidate();
		                        e.panel.areInv.repaint();
		                        if(e.panel != null) {
		                            e.panel.inventario(); 
		                            e.panel.areInv.revalidate();
		                            e.panel.areInv.repaint();
		                        }
		                    }
		                });
		                t.setRepeats(false);
		                t.start();
		            });
		        }).start();
		    
		});
	}
	
	
}
