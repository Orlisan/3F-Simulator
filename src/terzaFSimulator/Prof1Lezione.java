package terzaFSimulator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import static terzaFSimulator.Prof1.UmoriProf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static terzaFSimulator.MetodiUtili.*;
import static terzaFSimulator.Main.*;


public class Prof1Lezione {

	public static ImageIcon scappaCheÈMeglio = new ImageIcon("Texture\\prof_lavagna_malus.png");
	
	public static JButton manoAlzata;
	public static JButton helpFrasi;
	
	static JPanel panLezione = new JPanel();
	
	static JLabel coseDette;
	static JLabel titoloLav;
	static JLabel scrittaLav;
	static JLabel profAllaLavagna;
	static JLabel maclaurin;
	static JLabel cirGon;
	public static JLabel labelIntervento;
	static JLabel lavagna;
	
	static String intervento = null;
	static String parCorrente = null;
	
	static boolean spiegazioneInCorso = false;
	static boolean unoDue = true;
	static boolean giàCon = false;
	static boolean brill = false;
	static int indiceCasuale;
	static int puntiInPiù = 0;
	
	static Timer timerBrill;
	static Method metodoCorrente;
	
	static ArrayList<Method> metodiSpiegati = new ArrayList<Method>();
	static ArrayList<String> veriCompiti = new ArrayList<String>();
	
	static void creaAmbiente() {
		mappaClasse.setVisible(false);
		
	    panLezione.setBounds(0, 0, 880, 671);
	    panLezione.setLayout(null);
	    
	    JLabel sfondoLez = new JLabel(Main.inizio);
	    sfondoLez.setBounds(0, 0, 880, 671);
	    panLezione.add(sfondoLez);
	    
	    profAllaLavagna = new JLabel(new ImageIcon("Texture\\prof_lavagna.png"));
	    profAllaLavagna.setBounds(200, 100, 200, 200);
	    profAllaLavagna.setOpaque(false);
	    panLezione.add(profAllaLavagna);
	    
	    lavagna = new JLabel(new ImageIcon("Texture\\lavagna.png"));
	    lavagna.setBounds(250, 50, 439, 345);
	    lavagna.setOpaque(false);
	    panLezione.add(lavagna);
	    
	    coseDette = new JLabel("");
	    coseDette.setBounds(75, 70, 200, 200);
	    coseDette.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
	    panLezione.add(coseDette);
	    
	    maclaurin = new JLabel();
		maclaurin.setBounds(330, 260, 206, 61);
		maclaurin.setVisible(false);
		panLezione.add(maclaurin);
	    
		cirGon = new JLabel();
		cirGon.setBounds(430, 60, 340, 340);
		cirGon.setVisible(false);
		panLezione.add(cirGon);
		
	    ImageIcon labelMano = new ImageIcon("Texture\\alza_mano_grigia.png");
	    manoAlzata = new JButton(labelMano);
	    manoAlzata.setBounds(90, 450, 200, 84);
	    manoAlzata.setOpaque(false);
	    manoAlzata.setContentAreaFilled(false);
	    manoAlzata.setBorderPainted(false); 
	    panLezione.add(manoAlzata);
	    
	    helpFrasi = new JButton(new ImageIcon("Texture\\help_frasi_grigia.png"));
	    helpFrasi.setBounds(350, 450, 90, 90);
	    helpFrasi.setOpaque(false);
	    helpFrasi.setContentAreaFilled(false);  // RIMUOVE sfondo del bottone
	    helpFrasi.setBorderPainted(false);      // RIMUOVE bordo del bottone
	    panLezione.add(helpFrasi);
	    
	    labelIntervento = new JLabel("");
	    labelIntervento.setBounds(460, 420, 400, 200);
	    labelIntervento.setForeground(Color.WHITE);
	    labelIntervento.setOpaque(false);
	    panLezione.add(labelIntervento);
	    
	    titoloLav = new JLabel("");
		titoloLav.setBounds(415, 110, 120, 40);
		panLezione.add(titoloLav);
		
		scrittaLav = new JLabel("");
		scrittaLav.setBounds(315, 100, 320, 240);//DISPONIBILI 29 CARATTERI PER RIGA!!!!!!!YAY
		panLezione.add(scrittaLav);
	     
		panLezione.setComponentZOrder(helpFrasi, 0);
		panLezione.setComponentZOrder(labelIntervento, 1);
	    panLezione.setComponentZOrder(coseDette, 2);
	    panLezione.setComponentZOrder(profAllaLavagna, 3);
	    panLezione.setComponentZOrder(manoAlzata, 4);
	    panLezione.setComponentZOrder(scrittaLav, 5);
	    panLezione.setComponentZOrder(titoloLav, 6);
		panLezione.setComponentZOrder(maclaurin, 7); //Dev'essere davanti alla lavagna
		panLezione.setComponentZOrder(cirGon, 8);
	    panLezione.setComponentZOrder(lavagna, 9);
	    panLezione.setComponentZOrder(sfondoLez, 10);      //lo sfondo dev'essere sempre ultimo
	    
	    
	    Main.finestra.add(panLezione, BorderLayout.CENTER);
	    Main.finestra.setComponentZOrder(panLezione, 0);
	    Main.finestra.revalidate();
	    Main.finestra.repaint();
	    
	    introLez();
	    
	}
	

	
	public static void introLez() {
		
		Thread threadScrivProf = new Thread(() -> {
			//
		 
      /*  stampa("Oggi Faremo <br> lezione di <br> Trigonometria, <br> il nuovo <br> semplicissimo <br> argomento", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		
		stampa("A proposito,<br> dopodomani <br>avremo la <br> verifica di <br> questo argomento", coseDette);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
	
		stampa("Ma non <br> preuccupatevi, <br>la verifica <br>durerà ben <br>5 minuti e <br>avrà solamente <br>100 domande!", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		stampa("Più facile di <br>così allora <br>potreste anche<br>prendere 6,<br> che è dopo <br>il massimo ☺ ", coseDette);
		
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		
		stampa( "Adesso inizio<br> a spiegare,<br> vi prometto che<br> userò un <br>linguaggio molto <br>complicato, <br>come volete voi", coseDette);
		*/try {
			spiegaQualcosa(metodiSpiegati, false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		});
		threadScrivProf.start();
	}
		
	
		static void spiegaSeno(String paragrafo, boolean giàIntro, boolean giàSin) {
			spiegazioneInCorso = true;
			cirGon.setVisible(false);
			maclaurin.setVisible(false);
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
				stampa("sin(x)", titoloLav);
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
				maclaurin.setVisible(true);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("Si deve sommare il risultato dell'espressione <br>fino all' \"infinito\" aumentando ad ogni <br>somma n di 1; più volte sommi, più è accurato il risultato<br><br><br>", scrittaLav);
				permettiInterazione();
			}else if(paragrafo.equals("par4")) {
				parCorrente = "par4";
				maclaurin.setVisible(false);
				cirGon.setVisible(false);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
				stampa("In realtà il<br> modo più corretto <br>per calcolare <br>queste formule sarebbe<br> tramite questa<br> circonferenza <br>goniometrica: ", scrittaLav);
				try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
				cirGon.setIcon(new ImageIcon("Texture\\cir_sin().png"));
				cirGon.setVisible(true);
				stampa("È la stessa roba<br> di prima solo <br>che il triangolo<br> è \"incastrato\"<br> tra 2 raggi <br>di angolo x ", scrittaLav);
				permettiInterazione();
			}
		}
		
		static void spiegaCoseno(String paragrafo, boolean giàIntro, boolean giàCos) {
			spiegazioneInCorso = true;
			cirGon.setVisible(false);
			maclaurin.setVisible(false);
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
				stampa("cos(x)", titoloLav);
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
			maclaurin.setVisible(true);
			try {Thread.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
			scrittaLav.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
			stampa("Si deve sommare il risultato dell'espressione <br>fino all' \"infinito\" aumentando ad ogni <br>somma n di 1; più volte sommi, più è accurato il risultato<br><br><br>", scrittaLav);
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
		static void spiegaQualcosa(ArrayList<Method> giàFatti,boolean giàIntro) throws InterruptedException {
		    Random random = new Random();
		    
		    try {
		        Method[] metodiSpiega = {
		             Prof1Lezione.class.getDeclaredMethod("spiegaSeno", String.class, boolean.class, boolean.class),
		             Prof1Lezione.class.getDeclaredMethod("spiegaCoseno",String.class, boolean.class, boolean.class),
		        };
		        
		        if (giàFatti.size() >= metodiSpiega.length) {
		            System.out.println("DEBUG: Completati tutti gli argomenti, ritorno alla mappa");
		            tornAllaMappa();
		            return;
		        }
		        ArrayList<Method> metodiDisponibili = new ArrayList<>();
		        
		        for (Method m : metodiSpiega) {
		            if (!giàFatti.contains(m)) {
		                metodiDisponibili.add(m);
		            }
		        }
		        
		        
		        // 🔥 QUESTA PARTE DEVE ESSERE FUORI DAL IF!
		        int indiceCasuale = random.nextInt(metodiDisponibili.size());
		        metodoCorrente = metodiDisponibili.get(indiceCasuale);
		        metodiSpiegati.add(metodoCorrente);
		        
		        try {
		            metodoCorrente.invoke(null, "par1", giàIntro, false); 
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		    } catch (NoSuchMethodException | SecurityException e) {
		        e.printStackTrace();
		        System.out.println("Metodo Inesistente");
		    }
		}
		
		
		
		static void stampa(String messaggio, JLabel labelDaMod) {
		    StringBuilder messaggioFinOra = new StringBuilder("<html>");
		    char[] messaggioDaDire = messaggio.toCharArray();
		    
		    // Controlla se il font attuale è Wingdings
		    // Nota: confrontiamo il "Family" o il "Font Name" per sicurezza
		    String fontName = labelDaMod.getFont().getFamily();
		    boolean isWingdings = fontName.equalsIgnoreCase("Wingdings");

		    boolean inTag = false;

		    for (char c : messaggioDaDire) {
		        if (c == '<') inTag = true;
		        
		        if (inTag) {
		            messaggioFinOra.append(c);
		            if (c == '>') inTag = false;
		        } else {
		            // Applica l'offset SOLO se il font è Wingdings e non è uno spazio
		            if (isWingdings && c > 32) {
		                messaggioFinOra.append((char) (c + 0xF000));
		            } else {
		                messaggioFinOra.append(c);
		            }
		        }

		        labelDaMod.setForeground(qualeUmore(Prof1.umore)); 
		        labelDaMod.setText(messaggioFinOra.toString() + "</html>");

		        try {
		            Thread.sleep(50);
		        } catch (InterruptedException e) {
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
			helpFrasi.setIcon(new ImageIcon("Texture\\help_frasi.png"));
			
			manoAlzata.addActionListener(e -> {
				panLezione.setFocusable(true);
				panLezione.requestFocus();
				Main.configuraTasti(); 
			});
				helpFrasi.addActionListener(e -> {
				
					JFrame frasiProf1 = new JFrame("Frasi Reattive");
					frasiProf1.setSize(600, 600);
					frasiProf1.setDefaultCloseOperation(frasiProf1.DISPOSE_ON_CLOSE);
					frasiProf1.setLayout(new BorderLayout());
					frasiProf1.setResizable(false);
					
					JPanel panFrasi = new JPanel();
					panFrasi.setLayout(null);
					panFrasi.setOpaque(false);
					frasiProf1.add(panFrasi);
					JLabel frasi = new JLabel("<html><b> <div style='padding: 10px'> <h3>  FRASI CHE SUSCITANO REAZIONI </h3> </b> <br>"
												+ "<ul> "
													+ "<li>Non ho capito</li>"
													+ "spieghi / continui la lezione"
													+ "<li>Sei stupido/fai schifo</li>"
													+ "<li>prof/3f/arenga .delete/uninstall/kill</li>"
													+ "<li>Non mettere vocali nella frase</li>"
													+ "<li>Ho vinto il kangourou</li>"
													+ "<li>\"ah\" 15 volte</li>"
													+ "<li>Chiedere di andare in bagno</li>"
													+ "<li>Elencare sin, cos, tan, cot, sec, e csc, le formule trigonometriche principali</li>"
													+ "<li>Scrivere \"testo\".repeat(numero) ti ripete il testo per \"numero\" volte</li>"
												+ "</ul>");
					frasi.setBounds(10,10, 600, 600);
					frasi.setVerticalAlignment(SwingConstants.TOP); 
					panFrasi.add(frasi);
					frasiProf1.setVisible(true);
				});
			}

		
		
		public static void analizzaRisposta() {
			new Thread(() -> {
				String frisk = intervento.toLowerCase().trim();
				spiegazioneInCorso = false;
				if(frisk.contains(".repeat(") && frisk.contains(")")) {
					int num = Integer.parseInt(frisk.substring(
						    frisk.indexOf(".repeat(") + 8, 
						    frisk.indexOf(")", frisk.indexOf(".repeat(") + 8)
						).trim());
					String nuovoFrisk = frisk.replaceAll("\\.repeat", "").replaceAll("\\("+String.valueOf(num)+"\\)", "");
					frisk = nuovoFrisk.repeat(num);
					System.out.println(frisk);
				}
				
				if(frisk.equals("non ho capito")) {
					if(!brill) {ImageIcon brill1 = new ImageIcon("Texture\\prof_lavagna_brillante1.png");
					ImageIcon brill2 = new ImageIcon("Texture\\prof_lavagna_brillante2.png");
					
					timerBrill = new Timer(300, e -> {
						profAllaLavagna.setIcon(unoDue ? brill1 : brill2);
						unoDue = !unoDue;
					});
					timerBrill.setRepeats(true);
					timerBrill.start();
					brill = true;}
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
					
				} else if(frisk.contains("continui la lezione") || frisk.contains("spieghi")) {
					giàCon = true;
					spieg();
				} else if(frisk.contains("sei stupido") || frisk.contains("è stupido") || frisk.contains("fai schifo")) {
					System.out.println(Prof1.umore);
					cambiaUmore(-20); 
					System.out.println(Prof1.umore + " NUOVO");
					coseDette.setForeground(MetodiUtili.qualeUmore(Prof1.umore));
					stampa("COME OSI!!! +2000 NOTE, +400 4", coseDette);
					Main.personaggioSelezionato.setOro(Main.personaggioSelezionato.getOro() - 100);
					Main.aggiornaOro();
					
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					
				}else if(frisk.contains(decripta("rtrr")) || frisk.contains(decripta("PLZG"))) {
					cambiaUmore(-65); 
					stampa("NON È AFFATTO DIVERTENTE!<br> SIAMO IN UN CONTESTO <br>DIDATTICO!!!", coseDette);
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					stampa("NON VUOL DIRE<br> AFFATTO QUELLO!!!<br> CHE COMPORTAMENTO <br> VERGOGNOSO!", coseDette);
					Main.personaggioSelezionato.setOro(Main.personaggioSelezionato.getOro() - 150);
					Main.aggiornaOro();
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					
				}else if(!frisk.contains("a") && !frisk.contains("à") && !frisk.contains("e") && !frisk.contains("è") && !frisk.contains("é") && !frisk.contains("i")&&!frisk.contains("ì") && !frisk.contains("o")&& !frisk.contains("ò") && !frisk.contains("u") &&!frisk.contains("ù") && !frisk.contains("666") ) {
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
					
				}else if(frisk.contains("ho vinto il kangourou")) {
					cambiaUmore(40);
					stampa("I miei più sinceri complimenti!!!!", coseDette);
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					stampa("Sono stato<br> bravissimo ad<br> insegnarti che <br>sei riuscito a<br> vincerlo,<br> di nuovo <br>complimenti a me!!", coseDette);
					
				}else if(frisk.contains("ah".repeat(15)) || frisk.contains("ha".repeat(15))) {
					cambiaUmore(45);
					stampa("AHAHAHAHA<br>HAHAHAHA<br>HAHAHAHAH<br>AHAHAHAH<br>AHAHAHAHA", coseDette);
					stampa("AHAHAHAHAHAHAHAHAHAHAHAHAH<br>AHAHAHAHAHAHAHAHAHAHA<br>HAHAHAHAHAHAHAHAHAHAHAHAH<br>AHAHAHAHAHAHAHAHAHAHAHAHAHAHAH<br>AHAHAHAHAHA"
							+ "HAHAHAHAHAHAHAHAHAHAH<br>AHAHAHAHAHAHAHAHAHAHAHAHAHAHA".repeat(3),scrittaLav);
					stampa("AHAH<br>AHAHA<br>HAHAHA", titoloLav);
				}else if(frisk.contains("bagno") || frisk.contains("gabinetto")) {
					cambiaUmore(-5);
					stampa("Ci sei stato/a<br> 5 mesi fa!,<br> aspetta <br>l'intervallo", coseDette);
				}else if(frisk.contains("sin") && frisk.contains("cos") && frisk.contains("tan") && frisk.contains("cot") && frisk.contains("sec") && frisk.contains("csc")) {
					cambiaUmore(15);
					stampa("Vedo che hai<br>studiato bene <br>la lezione, <br>avrai sicuramente<br> studiato 1500 ore,<br> se no non ce <br>l'avresti fatta", coseDette);
				}else if(frisk.contains("666")) {
						gaster();
				}
				debugEnter = false;
				checkUmore();
				if(!giàCon) {
					conSpieg();
				}
				giàCon = false;
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
		            if(risposta.contains("sì") || risposta.contains("si")) {
		                spieg();
		             } else {
		                stampa("Ok, fammi tutte le domande che non vuoi", coseDette);
		                
		                }
		       
		       }
		       } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		        
		    }).start();
		}
		static void spieg() {
			if (parCorrente.equals("par1")) {
                sconfiguraTasti();
                try {
                    metodoCorrente.invoke(null, "par2", true, true);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }else if(parCorrente.equals("par2")) {
            	 sconfiguraTasti();
            	try {
					metodoCorrente.invoke(null, "par3", true, true);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            }else if(parCorrente.equals("par3")) {
            	sconfiguraTasti();
            	try {
					metodoCorrente.invoke(null, "par4", true, true);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            } else if(parCorrente.equals("par4")) {
                // 🔥 IMPORTANTE: Aggiungi il metodo corrente alla lista dei completati
                if (metodoCorrente != null) {
                    // Controlla se è già nella lista
                    boolean giàPresente = false;
                    for (Method m : metodiSpiegati) {
                        if (m.equals(metodoCorrente)) {
                            giàPresente = true;
                            break;
                        }
                    }
                    if (!giàPresente) {
                        metodiSpiegati.add(metodoCorrente);
                        System.out.println("DEBUG: Aggiunto " + metodoCorrente.getName() + " a metodiSpiegati");
                        System.out.println("DEBUG: metodiSpiegati ora contiene " + metodiSpiegati.size() + " metodi");
                    }
                }
                try {
					spiegaQualcosa(metodiSpiegati, true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		}
		static void tornAllaMappa() throws InterruptedException {
				cirGon.setVisible(false);
				stampa("Purtroppo, <br>la lezione di oggi<br> è finita, <br>quindi addio e <br>ci rivediamo <br>tra 5 minuti", coseDette);
		
				try {Thread.sleep(s(4));}catch(InterruptedException e) {e.printStackTrace();}
				mappaClasse.remove(prof.icona);
				mappaClasse.remove(prof.fraseProf);
				audio("Sounds\\fine_lezione.wav");
				panLezione.setVisible(false);
				mappaClasse.setVisible(true);
				configuraTasti();
				mappaClasse.setFocusable(true);
				mappaClasse.requestFocus(true);
				
				try {Thread.sleep(s(10));}catch(InterruptedException e) {e.printStackTrace();}
 				 
				mappaClasse.setVisible(false);
				panLezione.setVisible(true);
				if(!mappaClasse.isVisible()) {System.out.println("Mappa sparita");}
				
				stampa("Pensavi di averla fatta franca, eh?, beh, ti sbagli, CI SONO I COMPITI!", coseDette);
				
				String[] compiti = {"P 123 n. 123", "P 321 n. 13", "P 324 n. 576", "P 666 n. 67", "P 28 n. 12", "P 140 n. 50", "P 2.5 n. 1.2", "P sin(90) n. cos(0)", "P 60° n. 32° 30'", "P a n. b", "P sqrt(-100) n. 0 / 0"};
				
				Random random = new Random();
				int ran = compiti.length;
				for(String compito: compiti) {
					int indice = random.nextInt(ran + 1);
					if(indice == ran) {
						veriCompiti.add(compito);
						ran--;
					}else {
						continue;
					}
				}
				
				if(veriCompiti.isEmpty()) {
					cambiaUmore(-40);
					stampa("Maledizione,<br> il libro non ha <br>esercizi adatti!!<br>Uffa, per stavolta<br> siete abbonati<br> dai compiti", coseDette);
				}else if(veriCompiti.size() == 11) {
					cambiaUmore(40);
					stampa("Ah che bello,<br> vi ho rimpinzato <br>di compiti,<br>che soddisfazione!", coseDette);
				} else {
					stampa("Su col morale,<br>avete solo " + veriCompiti.size() + " problemi<br> trigonometrici,<br> cosa volete che siano", coseDette);
					
				}
				StringBuilder dicCom = new StringBuilder();
				for(String veroCompito: veriCompiti) {
			        dicCom.append(veroCompito + ", ");
			        if((dicCom.length() % 30) == 0) {
			        	dicCom.append("<br>");
			        }
		  		}
				stampa("I Compiti sono: " +  dicCom.toString(), scrittaLav);
				Thread.sleep(s(2));
				stampa("Li avete scrittti sul diario?, adesso controllo", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				cambiaUmore(-300);
				checkUmore();
				if(brill) {timerBrill.stop();}
				profAllaLavagna.setIcon(scappaCheÈMeglio);
				stampa("NNNNOOONN HHAII SSCCRITTTO II COMMPITTI!!", coseDette);
				if(Prof1.umore != UmoriProf.OMICIDA) {
					stampa("AADDESSSO...", coseDette);
					try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
					stampa("IO. . .", coseDette);
					try {Thread.sleep(s(3));} catch (InterruptedException e1) {e1.printStackTrace();}
					stampa("TI. . . . . .", coseDette);
					audio("Sounds\\campanella.wav");
					Thread.sleep(500);
				
					stampa("Maledizione", coseDette);
					try {Thread.sleep(s(1));} catch (InterruptedException e1) {e1.printStackTrace();}
					stampa("Sei salvo, la tua esistenza è meno importante che la 3E", coseDette);
					try {Thread.sleep(s(1));} catch (InterruptedException e1) {e1.printStackTrace();}
					stampa("Addio.", coseDette);
					try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
					audio("Sounds\\fine_lezione.wav");
				}
				
				mappaClasse.remove(prof.icona);
				mappaClasse.remove(prof.fraseProf);
				panLezione.setVisible(false);
				mappaClasse.setVisible(true);
				configuraTasti();
				mappaClasse.setFocusable(true);
				mappaClasse.requestFocus(true);
				
				}
		static void checkUmore() {
			if(Prof1.umore == UmoriProf.ESTASIATO) {
				if(brill) {timerBrill.stop();}
				profAllaLavagna.setIcon(new ImageIcon("Texture\\prof_lavagna_estasiato.png"));
				stampa("Oh", coseDette);		
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Mi sento così. . . ", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Non ho mai<br> provato questa <br>sensazione. . . ", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Non so come<br> chiamarla. . . ", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Guardiamo nel<br> vocabolario. . . ", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Uhmm. . . ", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Ecco: <br>“Voglia di non<br> torturare gli<br> alunni” è esattamente <br>come mi sento<br>", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("“Sinonimo di regalare 500 Oro a " + personaggioSelezionato.nome + " " + personaggioSelezionato.cognome+"”",coseDette );
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Beh, allora non mi resta che una cosa da fare", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				personaggioSelezionato.setOro(personaggioSelezionato.getOro() + 500);
				stampa("Fatto", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				stampa("Qua non ho più niente da fare, arrivederci, e grazie dei -500 Oro!", coseDette);
				try {Thread.sleep(s(2));} catch (InterruptedException e1) {e1.printStackTrace();}
				mappaClasse.remove(prof.icona);
				mappaClasse.remove(prof.fraseProf);
				audio("Sounds\\fine_lezione.wav");
				try {Thread.sleep(s(1));} catch (InterruptedException e1) {e1.printStackTrace();}
				panLezione.setVisible(false);
				mappaClasse.setVisible(true);
				configuraTasti();
				mappaClasse.setFocusable(true);
				mappaClasse.requestFocus(true);
				
			}
			else if(Prof1.umore == UmoriProf.OMICIDA) {
				if(brill) {timerBrill.stop();}
				profAllaLavagna.setIcon(scappaCheÈMeglio);
			}
		}
		
		static void gaster() {
			audio("Sounds\\her.wav");
			JLabel gaster = new JLabel("Texture\\misterious_man.png");
			gaster.setBounds(220, 60, 130, 160);
			gaster.setIcon(trasparenza("Texture\\misterious_man.png", 0f));
			panLezione.add(gaster);
			panLezione.setComponentZOrder(gaster, 0);
			new Thread(() -> {
			for(float i = 0f; i <= 1.0f; i += 0.02f) {
				float ci = i;
				SwingUtilities.invokeLater(() -> {gaster.setIcon(trasparenza("Texture\\misterious_man.png", ci));});
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}).start();
			
			try {Thread.sleep(s(5));}catch(InterruptedException e) {e.printStackTrace();}
			scrittaLav.setFont(wingdingsFont);
			stampa("\"Entry number 17<br> Dark Darker Yet Darker <br>The Darkness Keeps Growing<br> The Shadows Cutting Deeper<br> Photon Readings Negative<br>", scrittaLav);
			try {Thread.sleep(s(2));}catch(InterruptedException e) {e.printStackTrace();}
			stampa("This Next Experiment Seems Very Very Interesting ...<br> What Do You Two Think<br>, are you ready, to create,<br> the, first, original, DARK SCHOOL???\" <br>", scrittaLav);
			try {Thread.sleep(s(2));}catch(InterruptedException e) {e.printStackTrace();}
			stampa("This was the beginning of your reality,<br> of your agony, <br>of your Determination. . .<br> But now. . . I must do another experiment,<br> and I need my determination for it,<br>", scrittaLav); 
			try {Thread.sleep(s(2));}catch(InterruptedException e) {e.printStackTrace();}
			gaster.setBounds(200, 40, 150, 180);
			gaster.setIcon(new ImageIcon("Texture\\misterious_man_surprised.png"));
			stampa("your time is up. . . <br>GOODBYE <br>        EXPERIMENT NUMBER 17", scrittaLav);
			try {Thread.sleep(s(4));}catch(InterruptedException e) {e.printStackTrace();}
			Random casuale = new Random();
			Timer timer = new Timer(30, e -> {
			  
			    lavagna.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    coseDette.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    scrittaLav.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    titoloLav.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    maclaurin.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    cirGon.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    manoAlzata.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    helpFrasi.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    labelIntervento.setLocation(casuale.nextInt(880), casuale.nextInt(671));
			    
			});
			timer.setRepeats(true);
			timer.start();
			try {Thread.sleep(s(4));}catch(InterruptedException e) {e.printStackTrace();}
			System.err.println("Exception in thread \"main\" java.lang.GasterException: temporal lines corrupted");
			System.err.println("\tat java.lang.gaster.$_.€£○.♦◙");
			audio("Sounds\\adios.wav");
			try { Thread.sleep(s(2)); } catch(Exception e) {}
			System.exit(1);
		}
		
				
}

