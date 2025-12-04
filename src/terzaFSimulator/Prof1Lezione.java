package terzaFSimulator;

import javax.swing.*;

import terzaFSimulator.Prof1.UmoriProf;

import java.awt.*;
import java.io.File;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static terzaFSimulator.MetodiUtili.*;
public class Prof1Lezione {

	public static JButton manoAlzata;
	
	static JPanel panLezione = new JPanel();
	
	static JLabel coseDette;
	static JLabel titoloLav;
	static JLabel scrittaLav;
	static JLabel profAllaLavagna;
	static JLabel maclaurin;
	static JLabel cirGon;
	public static JLabel labelIntervento;
	
	static String intervento = null;
	static String parCorrente = null;
	
	static boolean spiegazioneInCorso = false;
	static boolean unoDue = true;
	static int indiceCasuale;
	static int puntiInPiù = 0;
	
	static Method metodoCorrente;
	
	static ArrayList<Method> metodiSpiegati = new ArrayList<Method>();
	static void creaAmbiente() {
		Main.finestra.remove(Main.mappaClasse);
		
	    panLezione.setBounds(0, 0, 880, 671);
	    panLezione.setLayout(null);
	    
	    JLabel sfondoLez = new JLabel(Main.inizio);
	    sfondoLez.setBounds(0, 0, 880, 671);
	    panLezione.add(sfondoLez);
	    
	    profAllaLavagna = new JLabel(new ImageIcon("Texture\\prof_lavagna.png"));
	    profAllaLavagna.setBounds(200, 100, 200, 200);
	    profAllaLavagna.setOpaque(false);
	    panLezione.add(profAllaLavagna);
	    
	    JLabel lavagna = new JLabel(new ImageIcon("Texture\\lavagna.png"));
	    lavagna.setBounds(250, 50, 439, 345);
	    lavagna.setOpaque(false);
	    panLezione.add(lavagna);
	    
	    coseDette = new JLabel("");
	    coseDette.setBounds(75, 70, 200, 200);
	    coseDette.setFont(new Font("Serif", Font.BOLD, 20));
	    coseDette.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
	    panLezione.add(coseDette);
	    
	    maclaurin = new JLabel();
		maclaurin.setBounds(330, 260, 206, 61);
		maclaurin.setVisible(false);
		panLezione.add(maclaurin);
	    
		cirGon = new JLabel();
		cirGon.setBounds(370,200, 340, 340);
		cirGon.setVisible(false);
		panLezione.add(cirGon);
		
	    ImageIcon labelMano = new ImageIcon("Texture\\alza_mano_grigia.png");
	    manoAlzata = new JButton(labelMano);
	    manoAlzata.setBounds(90, 450, 200, 84);
	    manoAlzata.setOpaque(false);
	    panLezione.add(manoAlzata);
	    
	    labelIntervento = new JLabel("");
	    labelIntervento.setBounds(460, 420, 400, 200);
	    labelIntervento.setFont(new Font("Serif", Font.ITALIC, 25));
	    labelIntervento.setForeground(Color.WHITE);
	    labelIntervento.setOpaque(false);
	    panLezione.add(labelIntervento);
	    
	    titoloLav = new JLabel("");
		titoloLav.setBounds(415, 110, 120, 40);
		titoloLav.setFont(new Font("Serif", Font.BOLD, 40));
		panLezione.add(titoloLav);
		
		scrittaLav = new JLabel("");
		scrittaLav.setBounds(315, 100, 320, 240);//DISPONIBILI 29 CARATTERI PER RIGA!!!!!!!YAY
		scrittaLav.setFont(new Font("Serif", Font.PLAIN, 20));
		panLezione.add(scrittaLav);
	     
		
		panLezione.setComponentZOrder(labelIntervento, 0);
	    panLezione.setComponentZOrder(coseDette, 1 );
	    panLezione.setComponentZOrder(profAllaLavagna, 2);
	    panLezione.setComponentZOrder(manoAlzata, 3);
	    panLezione.setComponentZOrder(scrittaLav, 4);
	    panLezione.setComponentZOrder(titoloLav, 5);
		panLezione.setComponentZOrder(maclaurin, 6);
		panLezione.setComponentZOrder(cirGon, 7);//Dev'essere davanti alla lavagna
	    panLezione.setComponentZOrder(lavagna, 8);
	    panLezione.setComponentZOrder(sfondoLez, 9);      //lo sfondo dev'essere sempre ultimo
	    
	    
	    Main.finestra.add(panLezione);
	    Main.finestra.setComponentZOrder(panLezione, 0);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	    
	    introLez();
	    
	}
	

	
	public static void introLez() {
		
		Thread threadScrivProf = new Thread(() -> {
			//
		 
     /*   stampa("Oggi Faremo <br> lezione di <br> Trigonometria, <br> il nuovo <br> semplicissimo <br> argomento", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		stampa("A proposito,<br> dopodomani <br>avremo la <br> verifica di <br> questo argomento", coseDette);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	
		stampa("Ma non <br> preuccupatevi, <br>la verifica <br>durerà ben <br>5 minuti e <br>avrà solamente <br>100 domande!", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		stampa("Più facile di <br>così allora <br>potreste anche<br>prendere 6,<br> che è dopo <br>il massimo ☺ ", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		stampa( "Adesso inizio<br> a spiegare,<br> vi prometto che<br> userò un <br>linguaggio molto <br>complicato, <br>come volete voi", coseDette);
		*/spiegaQualcosa(metodiSpiegati, false);
		});
		threadScrivProf.start();
	}
		
	
		static void spiegaSeno(String paragrafo, boolean giàIntro, boolean giàSin) {
			spiegazioneInCorso = true;
			if(!giàIntro) {
				giàSin = true;
				stampa("la lezione <br> di questi <br>3 minuti,<br> si tratterà<br> sulla funzione<br> sin()", coseDette);
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				titoloLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("sin(x)", titoloLav);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				muoviTitolo(titoloLav, true);
			}
			if(!giàSin) {
				stampa("Dato che la semplicissima lezione precedente è terminata adesso impareremo la formula sin()", coseDette);
			}
			if(paragrafo.equals("par1")) {
				parCorrente = "par1";
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("La funzione sin(), in trigonometria, è una delle <br>funzioni <br>principali e, in un triangolo <br>rettangolo, definisce il <br>rapporto tra il lato opposto <br>a un angolo dato e l'ipotenusa", scrittaLav);
				permettiInterazione();
			}else if(paragrafo.equals("par2")) {
				parCorrente = "par2";
				maclaurin.setVisible(false);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("Esso si può calcolare usando una formula chiamata<br> Serie di Maclaurin, una sommatoria infinita:<br><br><br>", scrittaLav);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				maclaurin.setIcon(new ImageIcon("Texture\\formula_sin().png"));
				maclaurin.setVisible(true);
				permettiInterazione();	
			}else if(paragrafo.equals("par3")) {
				parCorrente = "par3";
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("Si deve sommare il risultato dell'espressione <br>fino all'\"infinito\" aumentando ad ogni <br>somma n di 1; più volte sommi, più è accurato il risultato<br><br><br>", scrittaLav);
				permettiInterazione();
			}else if(paragrafo.equals("par4")) {
				parCorrente = "par4";
				maclaurin.setVisible(false);
				cirGon.setVisible(false);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("In realtà il modo più corretto per calcolare queste formule sarebbe tramite questa circonferenza goniometrica: ", scrittaLav);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				cirGon.setIcon(new ImageIcon("Texture\\cir_sin().png"));
				cirGon.setVisible(true);
				stampa("È la stessa roba di prima solo che il triangolo è \"incastrato\" tra 2 raggi di angolo x ", scrittaLav);
				permettiInterazione();
			}
		}
		
		static void spiegaCoseno(String paragrafo, boolean giàIntro, boolean giàCos) {
			spiegazioneInCorso = true;
			if(!giàIntro) {
				giàCos = true;
				stampa("la lezione <br> di questi <br>3 minuti,<br> si tratterà<br> sulla funzione<br> cos()", coseDette);
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				titoloLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("cos(x)", titoloLav);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				muoviTitolo(titoloLav, true);
			}
			if(!giàCos) {
				stampa("Dato che la semplicissima lezione precedente è terminata adesso impareremo il coseno", coseDette);
			}
			
		if(paragrafo.equals("par1")) {
			parCorrente = "par1";
			try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
			scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
			stampa("La funzione cos(), in trigonometria, è una delle <br>funzioni <br>principali e, in un triangolo <br>rettangolo, definisce il <br>rapporto tra il lato adiacente <br>a un angolo dato e l'ipotenusa", scrittaLav);
			permettiInterazione();
		}else if(paragrafo.equals("par2")) {
			parCorrente = "par2";
			maclaurin.setVisible(false);
			try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
			scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
			stampa("Esso si può calcolare usando una formula chiamata<br> Serie di Maclaurin, una sommatoria infinita:<br><br><br>", scrittaLav);
			try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
			maclaurin.setIcon(new ImageIcon("Texture\\formula_cos().png"));
			maclaurin.setVisible(true);
			permettiInterazione();

		}else if(paragrafo.equals("par3")) {
			parCorrente = "par3";
			try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
			scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
			stampa("Si deve sommare il risultato dell'espressione <br>fino all'\"infinito\" aumentando ad ogni <br>somma n di 1; più volte sommi, più è accurato il risultato<br><br><br>", scrittaLav);
			permettiInterazione();
		}else if(paragrafo.equals("par4")) {
			parCorrente = "par4";
			maclaurin.setVisible(false);
			cirGon.setVisible(false);
			try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
			scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
			stampa("In realtà il modo<br> più corretto per<br> calcolare queste<br> formule sarebbe<br> tramite questa <br>circonferenza <br>goniometrica: ", scrittaLav);
			try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
			cirGon.setIcon(new ImageIcon("Texture\\cir_cos().png"));
			cirGon.setVisible(true);
			stampa("È la stessa roba<br> di prima solo <br>che il triangolo<br> è \"incastrato\"<br> tra 2 raggi <br>di angolo x ", scrittaLav);
			permettiInterazione();
		}
		}
		static void spiegaQualcosa(ArrayList<Method> giàFatti,boolean giàIntro) {
			Random random = new Random();
			
			try {
				Method[] metodiSpiega = {
						 Prof1Lezione.class.getDeclaredMethod("spiegaSeno", String.class, boolean.class, boolean.class),
						 Prof1Lezione.class.getDeclaredMethod("spiegaCoseno",String.class, boolean.class, boolean.class),
				};
				
				  ArrayList<Method> metodiDisponibili = new ArrayList<>();
			        for (Method m : metodiSpiega) {
			            if (!giàFatti.contains(m)) {
			                metodiDisponibili.add(m);
			            }
			        }
			    
			        
			        int indiceCasuale = random.nextInt(metodiDisponibili.size());
			        metodoCorrente = metodiDisponibili.get(indiceCasuale);
			        metodiSpiegati.add(metodoCorrente);
			        
				try {
		            metodoCorrente.invoke(null, "par1", giàIntro, false); 
		       
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        
		        
			} catch (NoSuchMethodException | SecurityException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Metodo Inesistente");
			}
			
		}
		
		
		
		static void stampa(String messaggio, JLabel labelDaMod) {
			
			StringBuilder messaggioFinOra = new StringBuilder("<html>");
			char[] messaggioDaDire = messaggio.toCharArray();
			
			for(char ilMessaggio: messaggioDaDire) {
	            	
					messaggioFinOra.append(ilMessaggio);
	            	 labelDaMod.setText(messaggioFinOra.toString() + "</html>");
	            	 try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	             }
		}
		
		static void muoviTitolo(JLabel titolo, boolean suOGiù) {
			new Thread(() -> {
					for(int i = 0; i < 50; i++) {
						titolo.setBounds(titolo.getX(), titolo.getY() + (suOGiù ? -1 : 1), titolo.getWidth(), titolo.getHeight());
						try {
							Thread.sleep(100);
						}catch(InterruptedException e){
							e.printStackTrace();
						}
					} 
			}).start();
		}
		
		static void permettiInterazione() {
			manoAlzata.setIcon(new ImageIcon("Texture\\alza_mano.png"));
			
			manoAlzata.addActionListener(e -> {
				panLezione.setFocusable(true);
				panLezione.requestFocus();
				Main.configuraTasti(); 
			});
			}
		
		
		public static void analizzaRisposta() {
			new Thread(() -> {
				String frisk = intervento.toLowerCase().trim();
				spiegazioneInCorso = false;
				if(frisk.equals("non ho capito")) {
					ImageIcon brill1 = new ImageIcon("Texture\\prof_lavagna_brillante1.png");
					ImageIcon brill2 = new ImageIcon("Texture\\prof_lavagna_brillante2.png");
					
					Timer timer = new Timer(300, e -> {
						profAllaLavagna.setIcon(unoDue ? brill1 : brill2);
						unoDue = !unoDue;
					});
					timer.setRepeats(true);
					timer.start();
					cambiaUmore(60);
					
					coseDette.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
					stampa("AHHHHH!!!<br> Ecco quello che <br>volevo sentire,<br> ti rispiego tutto<br> in modo che tu<br> capisca ancora di meno", coseDette);
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					if(metodoCorrente != null) {
						try {
							metodoCorrente.invoke(null, parCorrente, true, true);
							try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
							
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					
				}  else if(frisk.contains("sei stupido") || frisk.contains("è stupido") || frisk.contains("fai schifo")) {
					System.out.println(Prof1.umore);
					cambiaUmore(-20); 
					System.out.println(Prof1.umore + " NUOVO");
					coseDette.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
					stampa("COME OSI!!! +2000 NOTE, +400 4", coseDette);
					Main.personaggioSelezionato.setOro(Main.personaggioSelezionato.getOro() - 100);
					Main.aggiornaOro();
					
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					
				}else if(frisk.contains(MetodiUtili.decripta("rtrrt")) || frisk.contains(MetodiUtili.decripta("PLZG"))) {
					cambiaUmore(-65); 
					stampa("NON È AFFATTO DIVERTENTE!<br> SIAMO IN UN CONTESTO <br>DIDATTICO!!!", coseDette);
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					stampa("NON VUOL DIRE<br> AFFATTO QUELLO!!!<br> CHE COMPORTAMENTO <br> VERGOGNOSO!", coseDette);
					Main.personaggioSelezionato.setOro(Main.personaggioSelezionato.getOro() - 150);
					Main.aggiornaOro();
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					
				}else if(!frisk.contains("a") && !frisk.contains("à") && !frisk.contains("e") && !frisk.contains("è") && !frisk.contains("é") && !frisk.contains("i")&&!frisk.contains("ì") && !frisk.contains("o")&& !frisk.contains("ò") && !frisk.contains("u") &&!frisk.contains("ù") ) {
					cambiaUmore(-10);
					stampa("Parla in italiano <br> idiota!", coseDette);
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					
				}else if(frisk.contains("3f.delete") || frisk.contains("prof.kill") || frisk.contains("prof.delete")
						|| frisk.contains("arenga.delete")|| frisk.contains("arenga.kill")|| frisk.contains("arenga.uninstall")
						|| frisk.contains("3f.uninstall")) {
					cambiaUmore(-35);
					stampa("È inutile che<br> provi a <br>cancellarmi, <br>tanto ho un <br>backup!", coseDette);
					try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
					stampa("Cheattando<br> non si vince <br>niente!<br> Vigliacco", coseDette);
					
				}else if(frisk.contains("Ho vinto il kangourou")) {
					cambiaUmore(40);
					stampa("I miei più sinceri complimenti!!!!", coseDette);
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					stampa("Sono stato<br> bravissimo ad<br> insegnarti che <br>sei riuscito a<br> vincerlo,<br> di nuovo <br>complimenti a me!!", coseDette);
					
				}
				Main.debugEnter = false;
				conSpieg();
			}).start();
		}
		
		static void cambiaUmore(int quantiPunti) {
		    puntiInPiù += quantiPunti;  
		    if(puntiInPiù >= 100) {
		        Prof1.umore = switch(Prof1.umore) {
		            case OMICIDA -> UmoriProf.INDEMONIATO;
		            case INDEMONIATO -> UmoriProf.ARRABBIATO;
		            case ARRABBIATO -> UmoriProf.IRRITATO;
		            case IRRITATO -> UmoriProf.CALMO;
		            case CALMO -> UmoriProf.LUSINGATO;
		            case LUSINGATO -> UmoriProf.CONTENTO;
		            case CONTENTO, ESTASIATO -> UmoriProf.ESTASIATO;
		        };
		        int puntiRimasti = puntiInPiù - 100; 
		        puntiInPiù = 0;
		        if(puntiRimasti > 0) {
		            cambiaUmore(puntiRimasti);
		        }
		    } else if(puntiInPiù <= -100) { 
		        Prof1.umore = switch(Prof1.umore) {
		            case ESTASIATO -> UmoriProf.CONTENTO;
		            case CONTENTO -> UmoriProf.LUSINGATO;
		            case LUSINGATO -> UmoriProf.CALMO;
		            case CALMO -> UmoriProf.IRRITATO;
		            case IRRITATO -> UmoriProf.ARRABBIATO;
		            case ARRABBIATO -> UmoriProf.INDEMONIATO;
		            case INDEMONIATO, OMICIDA -> UmoriProf.OMICIDA;
		        };
		        int puntiRimasti = puntiInPiù + 100;  
		        puntiInPiù = 0;
		        if(puntiRimasti < 0) {
		            cambiaUmore(puntiRimasti);
		        }
		    }
		}
		static void sconfiguraTasti() {
	    	panLezione.setFocusable(false);
	    	manoAlzata.setIcon(new ImageIcon("Texture\\alza_mano_grigia.png"));
	    }
		static void conSpieg() {
			 if (spiegazioneInCorso || coseDette.getText().contains("Continuo a spiegare")) {
			        return;
			    }
			try {Thread.sleep(s(4));} catch (InterruptedException e) {e.printStackTrace();}
			stampa("Continuo a spiegare?", coseDette);
		    
		    intervento = null;
		    labelIntervento.setText(""); 
		    
		    new Thread(() -> {
		        try {
		            while (intervento == null || intervento.trim().isEmpty()) {
		                Thread.sleep(100); 
		            }
		       if(!intervento.trim().isEmpty()) {
		            String risposta = intervento.toLowerCase().trim();
		            if(risposta.equals("sì") || risposta.equals("si")) {
		                if (parCorrente.equals("par1")) {
		                    sconfiguraTasti();
		                    try {
		                        metodoCorrente.invoke(null, "par2", true, true);
		                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
		                        e1.printStackTrace();
		                    }
		                }else if(parCorrente.equals("par2")) {
							try {
								metodoCorrente.invoke(null, "par3", true, true);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							}
		                }else if(parCorrente.equals("par3")) {
							try {
								metodoCorrente.invoke(null, "par4", true, true);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
		                }else if(parCorrente.equals("par4")) {
		                	spiegaQualcosa(metodiSpiegati, true);
						
		                } else {
		                stampa("Ok, fammi tutte le domande che non vuoi", coseDette);
		            }
		       }
		       } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        
		    }).start();
		}
}
