package terzaFSimulator;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Professore {

	public boolean allaCattedra = false;
	boolean textureAlternata = false;
    String nome;
	
	String cognome;
	JLabel icona;
	 public int posX;
	 public int posY;
	public int vita;
	
	boolean motoProf;
	
	
	Professore(String nome, String cognome, JLabel icona, int posX, int posY, int vita) {
		this.nome = nome;
		this.cognome = cognome;
		this.icona = icona;
		
		this.icona.setOpaque(false);
		this.icona.setBounds(posX, posY, 150, 150);
		
		this.posX = posX;
		this.posY = posY;
		this.vita = vita;		
	}
	
	
	
	void entraInAula(String iconOriginal, String iconNew) {
		int vecchiaX = posX;
		Thread thread = new Thread(() -> { // ogni 50 millisecondi
	        
			while(!(posX < 626 && posX > 590 && posY < 101 && posY> -7)) {
			
			posX = posX - 5; // sposta davvero il prof in X
	        icona.setLocation(posX, posY);

	        // Cambia immagine ogni 4 pixel
	        SwingUtilities.invokeLater(() -> {
	        if (posX % 4 == 0) {
	            icona.setIcon(new ImageIcon(iconNew));
		        Main.finestra.repaint();
	        } else {
	            icona.setIcon(new ImageIcon(iconOriginal));
		        Main.finestra.repaint();
	        
			}
	        });
	        Main.finestra.repaint();
	        
	        try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			Main.finestra.repaint();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SwingUtilities.invokeLater(() -> {
                this.aprePorta("Texture\\prof_spaccante.png","Texture\\porta_semi_rotta_des.png",
                    "Texture\\porta_semi_rotta_des_porta2.png","Texture\\porta_rotta_des.png",
                    "Texture\\porta_rotta_des_porta2.png", "Texture\\porta_semi_rotta_sin.png",
                    "Texture\\porta_semi_rotta_sin_porta2.png", "Texture\\porta_rotta_sin.png",
                    "Texture\\porta_rotta_sin_porta2.png");
                	
            });
	        // FERMA il timer quando arriva alla porta
	       
	    });
		thread.start();
		
		if(vecchiaX == posX) {
			motoProf = false;
		}
		
	    assert motoProf == false: "Il prof è in Ritardo";
		
	}

	void aprePorta(String iconProf, String portaSemi, String portaAperta, 
					String portaSemiOpz, String portaApertaOpz, String opz2, String opz3, String opz4, String opz5) {
		if(posX < 626 && posX > 590 && posY < 101 && posY> -7) {
			icona.setIcon(new ImageIcon(iconProf));
			Main.finestra.repaint();
			 
			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Main.sfondoDestro.setIcon(new ImageIcon(portaSemi));
				Main.finestra.repaint();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Main.sfondoDestro.setIcon(new ImageIcon(portaAperta));
				Main.finestra.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SwingUtilities.invokeLater(() -> {
                 	this.vaAllaCattedra("Texture\\prof.png", "Texture\\prof_camminante.png", "Texture\\prof_seduto.png");
                 });    
			});
			thread.start();
		}
		
	}
	
	
	void vaAllaCattedra(String profCam1, String profCam2, String profSeduto) {
	    new Thread(() -> {
	        new Thread(() -> {
	        	while(posX > 326) {
	        		textureAlternata = !textureAlternata;
	                icona.setIcon(new ImageIcon(textureAlternata ? profCam1 : profCam2));
	                
	                Main.mappaClasse.repaint();
	                try {Thread.sleep(50);}catch(InterruptedException e){e.printStackTrace();}
	        	}
	        }).start();
	        
	        while(posX > 530) { // Finché non arriva alla cattedra
	            // 1. Muovi
	            posX -= 5;
	            
	            // 2. Aggiorna GUI nell'EDT
	            SwingUtilities.invokeLater(() -> {
	                icona.setLocation(posX, posY);
	                
	                // Alterna texture
	                Main.mappaClasse.repaint();
	            });
	            
	            // 3. Aspetta
	            try {
	                Thread.sleep(50);
	            } catch (InterruptedException e) {
	                break;
	            }
	        }
	        
	        // Arrivato
	        SwingUtilities.invokeLater(() -> {
	            System.out.print("Il prof è arrivato ai banchi");
	            Main.mappaClasse.repaint();
	        });
	        
	        try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	        while(posY > 10) { // Finché non arriva alla cattedra
	            // 1. Muovi
	            posY -= 5;
	            
	            // 2. Aggiorna GUI nell'EDT
	            SwingUtilities.invokeLater(() -> {
	                icona.setLocation(posX, posY);
	                
	                // Alterna texture
	                Main.mappaClasse.repaint();
	            });
	            
	            // 3. Aspetta
	            try {
	                Thread.sleep(50);
	            } catch (InterruptedException e) {
	                break;
	            }
	           
	        }
	        try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	        while(posX > 326) { // Finché non arriva alla cattedra
	            // 1. Muovi
	            posX -= 1;
	           
	            // 2. Aggiorna GUI nell'EDT
	            SwingUtilities.invokeLater(() -> {
	                icona.setLocation(posX, posY);
	                
	                // Alterna texture
	                Main.mappaClasse.repaint();
	            });
	            
	            // 3. Aspetta
	            try {
	                Thread.sleep(50);
	            } catch (InterruptedException e) {
	                break;
	            }
	           
	        }
	       
	        
	        if(posX == 326) {
	        	SwingUtilities.invokeLater(() -> {
	        		icona.setIcon(new ImageIcon(profSeduto));
	        		Main.finestra.setComponentZOrder(this.icona, 0);
	        		Main.mappaClasse.setComponentZOrder(this.icona, 0);
	        		Main.finestra.repaint();
	        		allaCattedra = true;
	        	});
	        }
	     
	        
	    }).start();
	}
	



	void spiega() {
		
	}
		
}
