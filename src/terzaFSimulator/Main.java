package terzaFSimulator;

import javax.swing.*;

import terzaFSimulator.Prof1.UmoriProf;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    
    static ArrayList<Persona> personaggi = new ArrayList<>();
    static ArrayList<String> righeCript = new ArrayList<>();
    
    static JPanel mappaClasse; 
    static JPanel inizioSimulator;
    static JLabel oro;
    
    static Timer movementTimer = null;
    
    static  String userHome = System.getProperty("user.home");
   
    static ImageIcon iconaProf;
    static ImageIcon inizio = new ImageIcon("Texture\\inizio.png");
    
    static String riga1;
    static String riga2;
    static String riga3;
    static String riga5;
    static String riga6;
    static String riga7;
    static String riga8;
    static String riga9;
    
    static Persona personaggioSelezionato;
    static Prof1 prof;
    public static Direzione facciata;
    
    static boolean skippa = false;
    static boolean cèPorta = false;
    static boolean èAperta = false;
    static boolean èAperta2 = false;
    static volatile boolean cèProf = false;
    static volatile boolean portaRotta = false;
    public static boolean saiSkip = false;
    public static boolean cèSkip = false;
    public static boolean presoOro;
    public static boolean debugEnter = false;
    
    public static boolean nonToccaSu = true;
    public static boolean nonToccaGiù = true;
    public static boolean nonToccaASinistra = true;
    public static boolean nonToccaADestra = true;
    
    static boolean movUp = false;
    static boolean movDown = false;
    static boolean movLeft = false;
    static boolean movRight = false;
    
    public static Font determinationFont;

    public static int oroInPiù = 0;
    public static int vel = 1;
   
    public static File salvataggio;
	
    public static JFrame finestra;
    public static ImageIcon iconaSfondoSinistro;
    public static ImageIcon iconaSfondoDestro;
    public static JLabel sfondoSinistro;
    public static JLabel sfondoDestro;
    
    enum Direzione{SU, GIU, SINISTRA, DESTRA};
    
    public static void main(String[] args) throws FontFormatException, IOException {
        // Questo è il progetto di 3F SIMULATOR	(il gioco più bello al mondo)
    	
    	
        JButton bot1 = new JButton("Prova");
        bot1.setBounds(15, 15, 100 , 100);
    	
    	finestra = new JFrame("3F Simulator");
    
        finestra.setSize(880, 671);
        finestra.setResizable(false);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setLayout(new BorderLayout());
        finestra.setLocationRelativeTo(null);
        finestra.setVisible(true);
        mappaClasse = new JPanel();
        
        mappaClasse.setLayout(null);
        mappaClasse.setOpaque(false);
        
    	try {
    	new File(userHome + "\\AppData\\Local\\3FSimulator").mkdir();
    	salvataggio = new File(userHome + "\\AppData\\Local\\3FSimulator\\salvataggio.txt");
    	presoOro = seOroPreso();
    	
    	if(salvataggio.length() == 0) {
            
	    	try {
			FileWriter writer = new FileWriter(salvataggio);
			String nome = MetodiUtili.cripta("Orlando");
			String cognome = MetodiUtili.cripta("Sangiovanni");
			String età = MetodiUtili.cripta(String.valueOf(12));
			String posX = MetodiUtili.cripta(String.valueOf(728));
			String posY = MetodiUtili.cripta(String.valueOf(44));
			String oro = MetodiUtili.cripta(String.valueOf(100));
			String vita = MetodiUtili.cripta(String.valueOf(100));
			String bolOro = MetodiUtili.cripta(String.valueOf(presoOro));
			 
			
			writer.write( nome +"\n");
			writer.append(cognome +"\n");
			writer.append(età + "\n");
			writer.append(posX + "\n");
			writer.append(posY + "\n");
			writer.append(oro + "\n");
			writer.append(vita + "\n");
			writer.append(bolOro + "\n");
			
			writer.close();
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
    	}catch(Exception ec) {
    		System.out.println("File non trovato. Chi se ne frega");
    	}
    	
        
        configuraTasti();
        
        while(!skippa) {
	        
	        inizioSimulator = new JPanel();
	        inizioSimulator.setLayout(null);
	        inizioSimulator.setOpaque(false);
	       
	        inizioSimulator.setBackground(Color.BLACK);
	
	        
	        finestra.add(inizioSimulator);
	        JLabel labelInizio = new JLabel(inizio);
	        labelInizio.setOpaque(false);
	        
	        JLabel scrittaInizio = new JLabel("3F SIMULATOR");
	        JLabel puoiSkippare = new JLabel("(Premi ENTER per skippare)");
	        
	        
	        scrittaInizio.setForeground(Color.RED);
	        puoiSkippare.setForeground(Color.LIGHT_GRAY);
	       
	        puoiSkippare.setBounds(320, 100, 320, 30);
	        
	        labelInizio.setBounds(0, 0, 880, 671);
	        inizioSimulator.add(scrittaInizio);
	        
	        
	        inizioSimulator.add(labelInizio);
	        
	        
	        
	        int larghezza = 250;
	        int lunghezza = 40;
	        
	        for(int i = 0; i < 1000 && !skippa; i++) {
		        larghezza++;
		        lunghezza++;
	        	if(skippa) break;
		        
		        
	        	scrittaInizio.setBounds(340, 135, larghezza * 2, lunghezza);
	        	scrittaInizio.setFont(new Font("Determination Sans", Font.BOLD, 10 + i));
	        	
	        	int tempoDaAspettare = 5;
	        	
	        	if(i == 500) {
	        		inizioSimulator.add(puoiSkippare);
	        		inizioSimulator.setComponentZOrder(puoiSkippare, 0);
	        		inizioSimulator.repaint();
	        		saiSkip = true;
	        		cèSkip = true;
	        		
	        	}
	        
	        	
	        	if(saiSkip && i > 500 && (i % tempoDaAspettare) == 0)  {
	        	    puoiSkippare.setVisible(!cèSkip);
	        	    cèSkip = !cèSkip;
	        	    inizioSimulator.revalidate();
	        	    inizioSimulator.repaint();
	        	}
	        	if(i == 600) {tempoDaAspettare = 4;}
	        	if(i == 700) {tempoDaAspettare = 3;}
	        	if(i == 800) {tempoDaAspettare = 2;}
	        	if(i == 900) {if(!presoOro) {puoiSkippare.setText("Complimenti!! Sei Arrivato fino a questo punto! +100 Oro"); oroInPiù += 100;
	        	   puoiSkippare.setVisible(true); saiSkip = false; presoOro = true;}}
	        	
	        	
	        		
		         try {
		        Thread.sleep(100);
		         }
		         catch(InterruptedException e) {
		        	 e.printStackTrace();
		         }
		         
		         inizioSimulator.revalidate();
		         inizioSimulator.repaint(0, 0, 880, 671);
	        }
	       
	        
	        
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        
	        
	        inizioSimulator.remove(scrittaInizio);
	        inizioSimulator.remove(labelInizio);
	        finestra.remove(inizioSimulator);
	        
	        finestra.revalidate();
	        finestra.repaint();
	        break;
        }
        
        // ti mette le variabili delle immagini dello sfondo
        iconaSfondoSinistro = new ImageIcon("Texture\\mappaclasse_sinistra.png");
        sfondoSinistro = new JLabel(iconaSfondoSinistro);
        
        sfondoSinistro.setBounds(0, 0, 672, 671);
        mappaClasse.add(sfondoSinistro);
        
        iconaSfondoDestro = new ImageIcon("Texture\\mappaclasse_destra.png");
        sfondoDestro = new JLabel(iconaSfondoDestro);
        sfondoDestro.setBounds(672, 0, 208, 671);
        mappaClasse.add(sfondoDestro);
        
       
        ImageIcon iconaOrlando = new ImageIcon("Texture\\personaggio.png");
        JLabel orlandoLabel = new JLabel(iconaOrlando);
        orlandoLabel.setBounds(100, 100, 50, 50);
        orlandoLabel.setOpaque(false);
        
        iconaProf = new ImageIcon("Texture\\prof.png");
        JLabel profLabel = new JLabel(iconaProf);
        profLabel.setBounds(100, 100, 50, 50);
        profLabel.setOpaque(false);
      
        
       
        // valORI di default
        
        try {
			BufferedReader reader = new BufferedReader(new FileReader(salvataggio));
		
			try {
				
				riga1 = reader.readLine();
		        riga2 = reader.readLine();
		        riga3 = reader.readLine();
		        riga5 = reader.readLine();
		        riga6 = reader.readLine();
		        riga7 = reader.readLine();
		        riga8 = reader.readLine();
		        riga9 = reader.readLine();
		        
		        String[] righe = {riga1, riga2, riga3, riga5, riga6, riga7, riga8, riga9};
		        
		      
		        for(String riga : righe) {
		        	String rigaCor = MetodiUtili.decripta(riga);
		        	
		        	righeCript.add(rigaCor);
		        }
		        
		        reader.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        Persona orlando = new Persona(righeCript.get(0), righeCript.get(1), Integer.parseInt(righeCript.get(2)), orlandoLabel,  Integer.parseInt(righeCript.get(3)),  Integer.parseInt(righeCript.get(4)), Integer.parseInt(righeCript.get(5)) + oroInPiù, Integer.parseInt(righeCript.get(6))
        		);
        
        prof = new Prof1("Mario", "Arenga", profLabel, 728, 44, 200 );
        
        personaggi.add(orlando);
        
        
        
        
        // ✅ Aggiungi direttamente alla mappa
        mappaClasse.add(orlandoLabel);
        ;
        personaggioSelezionato = orlando;
        
        salvataggio();
     
        
        oro = new JLabel("Oro:" + orlando.oro);
        oro.setBounds(0, 209, 78, 63);
        oro.setForeground(Color.YELLOW);
        oro.setFont(new Font("Arial", Font.BOLD, 14));
        mappaClasse.add(oro);
        mappaClasse.setComponentZOrder(oro, 0);
        
        
        finestra.revalidate();
        finestra.repaint();
              
        configuraTasti();
        
        
      
                            
                          
                  
        
        
        		
        
        // ✅ Aggiungi i personaggi alla mappa
        for (Persona persona : personaggi) {
            mappaClasse.add(persona.icona);
        }
        
        finestra.add(mappaClasse, BorderLayout.CENTER);

        // ✅ INFINE aggiorna la finestra
           finestra.revalidate();
           finestra.repaint();
           finestra.setVisible(true);
         
       configuraTasti();
       
    }
    	
    
  
        
    
    static void configuraTasti() {
    	 KeyListener[] listeners = mappaClasse.getKeyListeners();
    	    for (KeyListener kl : listeners) {
    	        mappaClasse.removeKeyListener(kl);
    	    }
    	    
    	
    	finestra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    skippa = true;
                    System.out.println("SKIP EFFETTUATO!");
                }
            }
        });
    	mappaClasse.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (personaggioSelezionato == null) return;
                
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP: movUp = true; if(nonToccaSu) { muoviPersonaggio(0, -vel); facciata = Direzione.SU; personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_su.png")); if (movementTimer != null) {
                        movementTimer.stop();
                    }
                    avviaMovementTimer(); 
               	}break;
                   
                    case KeyEvent.VK_DOWN: movDown = true;if(nonToccaGiù) {muoviPersonaggio(0, vel); facciata = Direzione.GIU;personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio.png"));  if (movementTimer != null) {
                        movementTimer.stop();
                    }
                    avviaMovementTimer(); }break;
                   
                    case KeyEvent.VK_LEFT: movLeft = true; if(nonToccaASinistra) { muoviPersonaggio(-vel, 0); facciata = Direzione.SINISTRA; personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_sinistra.png"));  if (movementTimer != null) {
                        movementTimer.stop();
                    }
                    avviaMovementTimer(); }break;
                    
                    case KeyEvent.VK_RIGHT: movRight = true; if(nonToccaADestra) { muoviPersonaggio(vel, 0); facciata = Direzione.DESTRA; personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_destra.png")); if (movementTimer != null) {
                        movementTimer.stop();
                    }
                    avviaMovementTimer(); }break;
                    case KeyEvent.VK_Z: vel = 2; break;
                    case KeyEvent.VK_9: muoviPersonaggio(30, 0); break;
                    case KeyEvent.VK_8: muoviPersonaggio(-30, 0);break;
                    case KeyEvent.VK_7: muoviPersonaggio(0, 30);break;
                    case KeyEvent.VK_6: muoviPersonaggio(0, -30);break;
                    case KeyEvent.VK_S: salvataggio(); break;
                    case KeyEvent.VK_V: viaProf(); break;
                    case KeyEvent.VK_SHIFT:  
                        if(personaggioSelezionato.posizioneX > 574 && personaggioSelezionato.posizioneX < 632 && personaggioSelezionato.posizioneY > -7 && personaggioSelezionato.posizioneY < 101 && !èAperta) {
                            
                            String porta1 = MetodiUtili.forseAperta("Texture\\mappaclasse_porta1_semiaperta.png", "Texture\\mappaclasse_porta1_semiaperta_porta2.png", èAperta2);
                            String porta2 = MetodiUtili.forseAperta("Texture\\mappaclasse_porta1_aperta.png", "Texture\\mappaclasse_porta1_aperta_porta2.png", èAperta2);
                            apriPorta(porta1, porta2);
                            èAperta = true;
                            
                        } else if (personaggioSelezionato.posizioneX > 574 && personaggioSelezionato.posizioneX < 632 && personaggioSelezionato.posizioneY > -10 && personaggioSelezionato.posizioneY < 101 && èAperta) {
                            
                            String porta1 = MetodiUtili.forseAperta("Texture\\mappaclasse_porta1_semiaperta.png", "Texture\\mappaclasse_porta1_semiaperta_porta2.png", èAperta2);
                            String porta2 = MetodiUtili.forseAperta("Texture\\mappaclasse_destra.png", "Texture\\mappaclasse_porta2_aperta_destra.png", èAperta2);
                            apriPorta(porta1, porta2);
                            èAperta = false;
                            
                        } else if (personaggioSelezionato.posizioneX > 574 && personaggioSelezionato.posizioneX < 632 && personaggioSelezionato.posizioneY > 457 && personaggioSelezionato.posizioneY < 550 && !èAperta2) {
                            
                            // ✅ CORREZIONE CRITICA: Texture per porta2 quando si APRE
                            String porta1 = MetodiUtili.forseAperta("Texture\\mappaclasse_porta2_semiaperta_destra.png", "Texture\\mappaclasse_porta1_aperta_porta2.png", èAperta);   
                            String porta2 = MetodiUtili.forseAperta("Texture\\mappaclasse_porta2_aperta_destra.png", "Texture\\mappaclasse_porta1_aperta_porta2.png", èAperta);
                            apriPorta2("Texture\\mappaclasse_porta2_semiaperta_sinistra.png", porta1, "Texture\\mappaclasse_porta2_aperta_sinistra.png", porta2);
                            èAperta2 = true;
                            
                        } else if (personaggioSelezionato.posizioneX > 574 && personaggioSelezionato.posizioneX < 632 && personaggioSelezionato.posizioneY > 457 && personaggioSelezionato.posizioneY < 550 && èAperta2) {
                            
                            // ✅ CORREZIONE CRITICA: Texture per porta2 quando si CHIUDE  
                            String porta1 = MetodiUtili.forseAperta("Texture\\mappaclasse_porta2_semiaperta_destra.png", "Texture\\mappaclasse_porta1_aperta_porta2.png", èAperta);
                            String porta2 = MetodiUtili.forseAperta("Texture\\mappaclasse_destra.png", "Texture\\mappaclasse_porta1_aperta.png", èAperta);
                            apriPorta2("Texture\\mappaclasse_porta2_semiaperta_sinistra.png", porta1, "Texture\\mappaclasse_sinistra.png", porta2);
                            èAperta2 = false;
                            
                        } else if(personaggioSelezionato.posizioneX > -46 && personaggioSelezionato.posizioneX < 32 && personaggioSelezionato.posizioneY > 209 && personaggioSelezionato.posizioneY < 272) {
                            
                            personaggioSelezionato.oro++;
                            aggiornaOro();
                        }
                        break;
                    case KeyEvent.VK_N:
                    	if(FinestraNote.finNote == null || !FinestraNote.finNote.isVisible()) {
                    		FinestraNote.creaNote();
                    	}
                    	break;
                }}
            @Override
            public void keyReleased(KeyEvent e) {
            	 if (personaggioSelezionato == null) return;
            	 switch(e.getKeyCode()) {
            	 case KeyEvent.VK_UP: movUp = false; break;
            	 case KeyEvent.VK_DOWN: movDown = false; break;
            	 case KeyEvent.VK_LEFT: movLeft = false; break;
            	 case KeyEvent.VK_RIGHT: movRight = false; break;
            	 case KeyEvent.VK_Z: vel = 1; break;
            	 }
            	 if (!movUp && !movDown && !movLeft && !movRight) {
            	        if (movementTimer != null) {
            	            movementTimer.stop();
            	        }
            	    }
            }
        });
    	mappaClasse.setFocusable(true);
    	mappaClasse.requestFocus();
    	
    	// ATTENTION PLEASE KEYLISTENER DI PANNNLEZIONE!!!
        Prof1Lezione.panLezione.addKeyListener(new KeyAdapter() {
        	private StringBuilder testoScrivente = new StringBuilder();
        	
        	  @Override
        	    public void keyTyped(KeyEvent e) {
        		  char chara = e.getKeyChar();
        		 
        		  if (Character.isISOControl(chara)) {
        	            return;
        	        }
        		  
        		  if(testoScrivente.length() <= 30) {
	        		  testoScrivente.append(chara);
	        		  Prof1Lezione.labelIntervento.setText(testoScrivente.toString());
        		  }
        	  }
        	  @Override
        	    public void keyPressed(KeyEvent e) {
        	        switch(e.getKeyCode()) {
        	        case KeyEvent.VK_BACK_SPACE: 
        	        	if (testoScrivente.length() > 0) {
                            testoScrivente.setLength(testoScrivente.length() - 1);
                            Prof1Lezione.labelIntervento.setText(testoScrivente.toString());
                        }
                        break;
        	        case KeyEvent.VK_ENTER: 
        	        	if(testoScrivente.length() > 0 && !debugEnter) {
        	        		Prof1Lezione.intervento = testoScrivente.toString();
        	        		testoScrivente.setLength(0);
                            Prof1Lezione.labelIntervento.setText(testoScrivente.toString());
                            Prof1Lezione.analizzaRisposta();
                            Prof1Lezione.manoAlzata.setIcon(new ImageIcon("Texture\\alza_mano_grigia.png"));
                            debugEnter = true;
        	        	}
        	      
        	  }}
        });
   
    }
    
    static void aggiornaOro() {
    	if (oro != null) { 
           oro.setText("Oro: " + personaggioSelezionato.oro);
           assert personaggioSelezionato.oro > 1000000 : "Ti ho scoperto Elon Musk!";
        }
        
        // Aggiorna posizione (se hai la label)
        // Aggiorna vita (se hai la label)
        
        mappaClasse.repaint();
    }
    
    
    static void muoviPersonaggio(int dx, int dy) {
        if (personaggioSelezionato != null) {    	
        	// Aggiorna posizione
        	int vecchiaX = personaggioSelezionato.posizioneX;
            int vecchiaY = personaggioSelezionato.posizioneY;
            personaggioSelezionato.posizioneX += dx;
            personaggioSelezionato.posizioneY += dy;
            
            // Controlla se ora sta toccando
            personaggioSelezionato.seTocca();
            
            // ✅ SE LA DIREZIONE È BLOCCATA, TORNA INDIETRO
            if ((dx < 0 && !Main.nonToccaASinistra) ||  // Se andava a sinistra ed è bloccato
                (dx > 0 && !Main.nonToccaADestra) ||    // Se andava a destra ed è bloccato  
                (dy < 0 && !Main.nonToccaSu) ||         // Se andava su ed è bloccato
                (dy > 0 && !Main.nonToccaGiù)) {        // Se andava giù ed è bloccato
                
                // Ripristina la posizione originale
                personaggioSelezionato.posizioneX = vecchiaX;
                personaggioSelezionato.posizioneY = vecchiaY;
            }
            
            // Aggiorna visualizzazione
            personaggioSelezionato.icona.setLocation(
                personaggioSelezionato.posizioneX,
                personaggioSelezionato.posizioneY
            );
        
    
        }System.out.println(personaggioSelezionato.posizioneX);
         System.out.println(personaggioSelezionato.posizioneY);
         aggiornaOro();
         mappaClasse.requestFocusInWindow();
        
    }
    
    
    static void apriPorta(String icon1, String icon2) {
    	if(!portaRotta) {
	    	ImageIcon iconaPorta1Semi = new ImageIcon(icon1);
	   
	    	sfondoDestro.setIcon(iconaPorta1Semi);
	    	sfondoDestro.setBounds(672, 0, 208, 671); 
	    	
	    	
	    	 // Rimuovi tutto
	       // Riaggiungi sinistra
	       
	    	
	        mappaClasse.revalidate();
	    	mappaClasse.repaint();
	    	Timer timer = new Timer(500, e -> {
	            sfondoDestro.setIcon(new ImageIcon(icon2));
	            mappaClasse.revalidate();
	            mappaClasse.repaint();
	            
	        });
	        timer.setRepeats(false); // Solo una volta
	        timer.start();
    	}
    }
    
    static void apriPorta2(String icon1, String icon2, String icon3, String icon4) {
        // ✅ AGGIUNGI CONTROLLO ALL'INIZIO
        if (prof == null) {
            System.out.println("ATTENZIONE: prof non è stato inizializzato!");
            // Fallback senza gestione ordine Z
            ImageIcon iconaPorta1Semi = new ImageIcon(icon1);
            sfondoSinistro.setIcon(iconaPorta1Semi);
            sfondoSinistro.setBounds(0, 0, 672, 671); 
            sfondoDestro.setIcon(new ImageIcon(icon2));
            mappaClasse.revalidate();
            mappaClasse.repaint();
            
            Timer timer = new Timer(500, e -> {
                sfondoSinistro.setIcon(new ImageIcon(icon3));
                sfondoDestro.setIcon(new ImageIcon(icon4));
                mappaClasse.revalidate();
                mappaClasse.repaint();
            });
            timer.setRepeats(false);
            timer.start();
            return;
        }
        
        ImageIcon iconaPorta1Semi = new ImageIcon(icon1);
        sfondoSinistro.setIcon(iconaPorta1Semi);
        sfondoSinistro.setBounds(0, 0, 672, 671); 
        
        sfondoDestro.setIcon(new ImageIcon(icon2));
        mappaClasse.revalidate();
        mappaClasse.repaint();
        
        Timer timer = new Timer(1000, e -> {
            sfondoSinistro.setIcon(new ImageIcon(icon3));
            
            // ✅ GARANTISCI che prof sia in PRIMO piano
            
            if (personaggioSelezionato != null && personaggioSelezionato.icona.getParent() == mappaClasse) {
                mappaClasse.setComponentZOrder(personaggioSelezionato.icona, 1); // 1 = secondo piano
            }
            
            mappaClasse.revalidate();
            mappaClasse.repaint();

            sfondoDestro.setIcon(new ImageIcon(icon4));
            mappaClasse.revalidate();
            mappaClasse.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    
    static void salvataggio() {
    	String userHome = System.getProperty("user.home");

    	new File(userHome + "\\AppData\\Local\\3FSimulator").mkdir();
    	salvataggio = new File(userHome + "\\AppData\\Local\\3FSimulator\\salvataggio.txt");
    	salvataggio.mkdir();
 
    	try {
		FileWriter writer = new FileWriter(salvataggio);
		
		String nome = MetodiUtili.cripta(personaggioSelezionato.nome);
		String cognome = MetodiUtili.cripta(personaggioSelezionato.cognome);
		String età = MetodiUtili.cripta(String.valueOf(personaggioSelezionato.età));
		String posX = MetodiUtili.cripta(String.valueOf(personaggioSelezionato.posizioneX));
		String posY = MetodiUtili.cripta(String.valueOf(personaggioSelezionato.posizioneY));
		String oro = MetodiUtili.cripta(String.valueOf(personaggioSelezionato.oro));
		String vita = MetodiUtili.cripta(String.valueOf(personaggioSelezionato.vita));
		String bolOro = MetodiUtili.cripta(String.valueOf(presoOro));
		
		writer.write(nome + "\n");
		writer.append(cognome+ "\n");
		writer.append(età + "\n");
		writer.append(posX + "\n");
		writer.append(posY + "\n");
		writer.append(oro+ "\n");
		writer.append(vita + "\n");
		writer.append(bolOro + "\n");
		
		writer.close();
    	
    	
    	System.out.println("SALVATAGGIO COMPLETATO");
    	
    	
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }

      static void viaProf() {
    	  if(!cèProf) {
    		  cèProf =true;
    		  prof.entraInAula("Texture\\prof.png", "Texture\\prof_camminante.png");
              mappaClasse.add(prof.icona);
              mappaClasse.setComponentZOrder(prof.icona, 0);
              mappaClasse.revalidate();
              mappaClasse.repaint();
    	  }
      }
      static boolean seOroPreso() {
    	  if(salvataggio != null) {
    		  try {
				BufferedReader reader = new BufferedReader(new FileReader(salvataggio));
					
				String riga1 = reader.readLine();
				String riga2 = reader.readLine();
				String riga3 = reader.readLine();
				String riga4 = reader.readLine();
				String riga5 = reader.readLine();
				String riga6 = reader.readLine();
				String riga7 = reader.readLine();
				String riga8 = reader.readLine();
    		  
				return Boolean.parseBoolean(MetodiUtili.decripta(riga8));
    		  
    		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			  }
    	  }else {
    		  return false;
    	  }
    	  
      }
      static void avviaMovementTimer() {
    	    if (movementTimer != null) {
    	        movementTimer.stop();
    	    }
    	    
    	    movementTimer = new Timer(16, e -> {
    	        if (personaggioSelezionato == null) return;
    	        
    	        // ⭐⭐ DIAGONALE FUNZIONANTE
    	        int dx = 0, dy = 0;
    	        boolean siMuove = false;
    	        
    	        if (movUp && nonToccaSu) { dy -= vel; siMuove = true; }
    	        if (movDown && nonToccaGiù) { dy += vel; siMuove = true; }
    	        if (movLeft && nonToccaASinistra) { dx -= vel; siMuove = true; }
    	        if (movRight && nonToccaADestra) { dx += vel; siMuove = true; }
    	        
    	        // ⭐⭐ MUOVI IN DIAGONALE
    	        if (siMuove) {
    	            muoviPersonaggio(dx, dy);
    	            
    	            // ⭐⭐ FIX BUG: Resetta movLeft se non premuto
    	            if (!movLeft && dx < 0) {
    	                // dx è negativo (sinistra) ma movLeft è false? BUG!
    	                // Correggi forzando movimento solo se davvero premuto
    	                if (dx < 0 && !movLeft) {
    	                    dx = 0; // Non muovere a sinistra se non premuto
    	                }
    	            }
    	            
    	            // Animazione (priorità diagonale)
    	            if (movUp && movRight && nonToccaSu && nonToccaADestra) {
    	                facciata = Direzione.SU; // o crea texture diagonale
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_su.png"));
    	            } else if (movUp && movLeft && nonToccaSu && nonToccaASinistra) {
    	                facciata = Direzione.SU;
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_su.png"));
    	            } else if (movDown && movRight && nonToccaGiù && nonToccaADestra) {
    	                facciata = Direzione.GIU;
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio.png"));
    	            } else if (movDown && movLeft && nonToccaGiù && nonToccaASinistra) {
    	                facciata = Direzione.GIU;
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio.png"));
    	            } else if (movUp && nonToccaSu) {
    	                facciata = Direzione.SU;
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_su.png"));
    	            } else if (movDown && nonToccaGiù) {
    	                facciata = Direzione.GIU;
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio.png"));
    	            } else if (movLeft && nonToccaASinistra) {
    	                facciata = Direzione.SINISTRA;
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_sinistra.png"));
    	            } else if (movRight && nonToccaADestra) {
    	                facciata = Direzione.DESTRA;
    	                personaggioSelezionato.icona.setIcon(new ImageIcon("Texture\\personaggio_destra.png"));
    	            }
    	        } else {
    	            movementTimer.stop();
    	        }
    	    });
    	    
    	    movementTimer.setInitialDelay(0);
    	    movementTimer.setCoalesce(true);
    	    movementTimer.start();
    	}
     
}



