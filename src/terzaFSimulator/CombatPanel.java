package terzaFSimulator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;

public class CombatPanel extends JPanel {
	JPanel areAnima;
	JPanel areaTexture;
	JPanel areaScelte;
	JPanel areaVita;
	JPanel areAttacco;
	
	JButton fightButton;
	JButton actButton;
	JButton itemButton;
	JButton mercyButton;
	static boolean combat = false;
	
    Rectangle boxAnima;
    int xInizio = 140;
    int yInizio = 10;
    
    int sizeX = 600;
    int sizeY = 180;
	
	
	Enemy e;
	CombatPanel(Enemy e) {
		this.e = e;
		combat = true;
		creaAmbiente();
	}
	
	void creaAmbiente() {
	    this.setLayout(null);

	    areaTexture = new JPanel();
	    areaTexture.setBounds(0, 0, 880, 268);
	    areaTexture.setBackground(new Color(0, 0, 0, 0));
	    boxAnima = new Rectangle(xInizio, yInizio, sizeX, sizeY);
	    areAnima = new JPanel() {
	        @Override
	        protected void paintComponent(java.awt.Graphics g) {
	            super.paintComponent(g);
	            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
	            
	            g2.setColor(Color.WHITE);
	            g2.setStroke(new java.awt.BasicStroke(5)); 
	            if (boxAnima != null) {
	                g2.draw(boxAnima); 
	            }
	        }
	    };
	    
	    areAnima.setBounds(0, 268, 880, 201);
	    areAnima.setOpaque(false);
	    areAnima.setBackground(new Color(0, 0, 0, 0));
	    
	    
	    areaVita = new JPanel();
	    areaVita.setBounds(0, 603, 880, 67);
	    areaVita.setBackground(new Color(0, 0, 0, 0));
	    areaScelte = new JPanel();
	    areaScelte.setBounds(0, 469, 880, 134);
	    areaScelte.setBackground(new Color(0, 0, 0, 0));
	    areaScelte.setLayout(null);
	    fightButton = new JButton(new ImageIcon("Texture/fight_button.png"));
	    fightButton.setBounds(15, 40, 150, 52);
	    
	    actButton = new JButton(new ImageIcon("Texture/act_button.png"));
	    actButton.setBounds(210, 40, 150, 52);
	    
	    itemButton = new JButton(new ImageIcon("Texture/item_button.png"));
	    itemButton.setBounds(405, 40, 150, 52);
	    
	    mercyButton = new JButton(new ImageIcon("Texture/mercy_button.png"));
	    mercyButton.setBounds(600, 40, 150, 52);
	    
	    areaScelte.add(fightButton);
	    areaScelte.add(actButton);
	    areaScelte.add(itemButton);
	    areaScelte.add(mercyButton);
	    
	
	    
	    areAttacco = new JPanel();
	    areAttacco.setBounds(0, 261, 880, 200);
	    areAttacco.setBackground(new Color(0, 0, 0, 0));
	 //DEBUG 
	   /* areaTexture.setBackground(new Color(255, 0, 0, 100));    // Rosso semi-trasparente
	    areAnima.setBackground(new Color(0, 255, 0, 100));       // Verde semi
	    areaScelte.setBackground(new Color(0, 0, 255, 100));     // Blu semi
	    areaVita.setBackground(new Color(255, 255, 0, 100));    */
	  //DEBUG
	    
	    JLabel sfondo = new JLabel(new ImageIcon("Texture/inizio.png"));
	    sfondo.setBounds(0, 0, 880, 671);
	    
	    

	    this.add(areaTexture);
	    this.add(areaVita);
	    this.add(areaScelte);
	    this.add(areAnima);
	    this.add(sfondo);

	    this.setComponentZOrder(sfondo, this.getComponentCount() - 1);
	    this.setComponentZOrder(areaScelte, 1);
	    
	    Main.mappaClasse.setVisible(false); 
	    Main.finestra.add(this);
	    this.setVisible(true);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	}
}
