package terzaFSimulator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.*;

public class Prof1 extends Professore{

	enum UmoriProf {ESTASIATO, CONTENTO, LUSINGATO, CALMO, IRRITATO, ARRABBIATO, INDEMONIATO, OMICIDA};
	
	public static UmoriProf umore = UmoriProf.CALMO;

	int i;
	JLabel fraseProf;
	
	Prof1(String nome, String cognome, JLabel icona, int posX, int posY, int vita) {
		super(nome, cognome, icona, posX, posY, vita);
		// TODO Auto-generated constructor stub
	}

	void iniziaLezione() {
		
			
				Color coloreLabel = MetodiUtili.qualeUmore(umore);
				fraseProf = new JLabel("BUONGIORNO...");
				fraseProf.setBounds(374, 21, 120, 20);
				fraseProf.setForeground(coloreLabel);
				fraseProf.setBackground(Color.GRAY);
				
				Main.mappaClasse.add(fraseProf);
				Main.mappaClasse.setComponentZOrder(fraseProf,0);
				Main.mappaClasse.repaint();
				System.out.println("il prof sta parlando");
			
			Timer timer = new Timer(2000, e -> {
				fraseProf.setText("...RAGAZZI");
				fraseProf.setBounds(460, 21, 150, 30);
			});
			timer.setRepeats(false);
			timer.start();
		
			Timer timer1 = new Timer(2000, e -> {
			    new Thread(() -> {
			        for(int i = 60; i < 600; i++) {
			            final int ci = i;
			            
			            SwingUtilities.invokeLater(() -> {
			                ImageIcon imageScalata = new ImageIcon(
			                    new ImageIcon("Texture\\prof_seduto.png")
			                        .getImage()
			                        .getScaledInstance(ci, ci, Image.SCALE_SMOOTH)
			                );
			                icona.setIcon(imageScalata);
			                icona.setBounds(posX + 50 - (ci - 59), posY + 50, ci, ci);
			            });
			            
			            // ✅ "Pausa" senza bloccare EDT
			            try {
			                Thread.sleep(5); // Questo è OK perché in thread separato!
			            } catch (InterruptedException e1) {
			                e1.printStackTrace();
			            }
			        }
			    }).start();
			});
			timer1.setRepeats(false);
			timer1.start();
		
			Timer timerFinale = new Timer(5000, e -> {
				new Thread (() -> {
					JLabel inizioLez = new JLabel(new ImageIcon("Texture\\inizio.png"));
					inizioLez.setBounds(0,0,880, 671);
					SwingUtilities.invokeLater(() -> {
						Main.mappaClasse.add(inizioLez);
						Main.mappaClasse.setComponentZOrder(inizioLez, 0);
					    System.out.println("Primo flash eseguito");
					    Main.mappaClasse.repaint();
					});
					try {
						Thread.sleep(300);
						SwingUtilities.invokeLater(() -> {Main.mappaClasse.remove(inizioLez); Main.mappaClasse.repaint();});
						Thread.sleep(150);
						SwingUtilities.invokeLater(() -> {Main.mappaClasse.add(inizioLez); Main.mappaClasse.repaint();
						Main.mappaClasse.setComponentZOrder(inizioLez, 0); System.out.println("Secondo flash eseguito");});
						Thread.sleep(160);
						SwingUtilities.invokeLater(() -> {Main.mappaClasse.remove(inizioLez); Main.mappaClasse.repaint();});
						Thread.sleep(500);
						Prof1Lezione.creaAmbiente();
					}catch(InterruptedException e5) {
						e5.printStackTrace();
					}
					
				}).start();
			});
			timerFinale.setRepeats(false);
			timerFinale.start();
		
		
	}
	
	
	

	
	
	@Override
	void aprePorta(String iconProf, String portaSemiDes, String portaSemiDesP2, String portaRottaDes, String portaRottaDesP2, String portaSemiSin, String portaSemiSinP2, String portaRottaSin, String portaRottaSinP2) {
		
		if(posX < 626 && posX > 590 && posY < 101 && posY> -7) {
			
			icona.setIcon(new ImageIcon(iconProf));
			
			Main.finestra.repaint();
			
			Icon vecchioDestro = Main.sfondoDestro.getIcon();
			Icon vecchioSinistro = Main.sfondoSinistro.getIcon();
			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				SwingUtilities.invokeLater(() -> {
					String destroSemi = MetodiUtili.forseAperta(portaSemiDes, portaSemiDesP2, Main.èAperta2);
					Main.sfondoDestro.setIcon(new ImageIcon(destroSemi));
					String sinistroSemi = MetodiUtili.forseAperta(portaSemiSin,portaSemiSinP2, Main.èAperta2);
					
					Main.sfondoSinistro.setIcon(new ImageIcon(sinistroSemi));
					Main.finestra.repaint();
				});
				
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Timer timer = new Timer(300, e -> {
					SwingUtilities.invokeLater(() -> {
				String destroRot = MetodiUtili.forseAperta(portaRottaDes, portaRottaDesP2, Main.èAperta2);

				String sinistroRot = MetodiUtili.forseAperta(portaRottaSin, portaRottaSinP2, Main.èAperta2);
				
				Main.sfondoDestro.setIcon(new ImageIcon(destroRot));
				Main.sfondoSinistro.setIcon(new ImageIcon(sinistroRot));
				Main.portaRotta = true;
				Main.finestra.repaint();
				
				
				});
				
				});
				timer.setRepeats(false);
				timer.start();
				System.out.println("Il prof si sta muovendo!");
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

	
	@Override
	void vaAllaCattedra(String profCam1, String profCam2, String profSeduto) {
	    super.vaAllaCattedra(profCam1, profCam2, profSeduto);
	    
	    // Invece di sleep, verificare quando l'animazione è completata
	    Timer checkTimer = new Timer(100, e -> {
	        if (allaCattedra) {
	            ((Timer)e.getSource()).stop();
	            iniziaLezione();
	        }
	    });
	    checkTimer.start();
	}
	
}
