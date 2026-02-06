package terzaFSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CombatPanel extends JPanel {
    JPanel areAnima, areaTexture, areaScelte, areaVita, areAttacco, areInv;
    JButton fightButton, actButton, itemButton, mercyButton;
    static boolean combat = false;
    JLabel vita;
    JLabel numeriVita;
    // Riferimenti per gli occhi
    Sprite occhioSu, occhioGiù, occhioDestra, occhioSinistra;
    Sprite occhioSu2, occhioGiù2, occhioDestra2, occhioSinistra2;
    boolean animOcchi1 = false, animOcchi2 = false;

    // Riferimenti per inversioni
    Sprite tempInversione1, tempInversione2, tempInversione3, tempInversione4;

    Rectangle boxAnima;
    int xInizio = 140, yInizio = 10, sizeX = 600, sizeY = 180;
    Enemy e;
    Anima anima;
    
    private Timer movementTimer;
    private boolean movUp = false, movDown = false, movLeft = false, movRight = false;
    private int animaSpeed = 2;
    CombatPanel(Enemy e) {
        this.e = e;
        combat = true;
        creaAmbiente();
    }

    void creaAmbiente() {
    	
    	
    	this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.BLACK);

        areaTexture = new JPanel();
        areaTexture.setBounds(0, 0, 880, 268);
        areaTexture.setOpaque(false);
        areaTexture.setLayout(null);

        int numLabel = 0;
        for (Sprite sprite : e.sprites) {
            sprite.setOpaque(false);
            sprite.setBounds(e.posSprites[numLabel][0], e.posSprites[numLabel][1], 
                             e.posSprites[numLabel][2], e.posSprites[numLabel][3]);
            areaTexture.add(sprite);

            if (sprite.getTag().contains("occhio")) {
                raccogliOcchi(sprite, numLabel);
            } else if (sprite.getTag().contains("inverti_")) {
                raccoglInversioni(sprite, numLabel);
            }
            numLabel++;
        }

        creaAnima();
        Item gessetto = new Item("Gessetto", "Prova" ,new int[] {3, 5, 2, 4}, Main.bidello);
    	Item grissino = new Item("Grissino", "Altra prova", new int[] {-2}, Main.bidello);
    	Item verifica = new Item("Verifica", "Ennesima prova", new int[] {-2, 3}, Main.bidello);
       Main.personaggioSelezionato.inventario.add(gessetto); //TEMPORANEOOO
       Main.personaggioSelezionato.inventario.add(grissino);
       Main.personaggioSelezionato.inventario.add(verifica);
        inventario();
        
        areaScelte = new JPanel(null);
        areaScelte.setBounds(0, 469, 880, 134);
        areaScelte.setOpaque(false);
        fightButton = new JButton(new ImageIcon("Texture/fight_button.png"));
        actButton   = new JButton(new ImageIcon("Texture/act_button.png"));
        itemButton  = new JButton(new ImageIcon("Texture/item_button.png"));
        mercyButton = new JButton(new ImageIcon("Texture/mercy_button.png"));

        JButton[] bottoni = {fightButton, actButton, itemButton, mercyButton};
        int bx = 15;
        for (JButton b : bottoni) {
            b.setBounds(bx, 40, 150, 52);
            b.setContentAreaFilled(false); 
            b.setBorderPainted(false);
            areaScelte.add(b);
            bx += 195;
        }
        areaVita = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(1));
                
                // Disegna il box vita a coordinate relative al pannello
                g2.drawRect(390, 10, 100, 30);
            }
        };

        areaVita.setOpaque(false);
        areaVita.setLayout(null);
        areaVita.setBounds(0, 580, 880, 67); 

        // Nome personaggio
        JLabel nome = new JLabel(Main.personaggioSelezionato.nome);
        nome.setForeground(Color.WHITE);
        nome.setFont(nome.getFont().deriveFont(Font.BOLD, 16f)); 
        nome.setBounds(200, 10, 200, 30);

        // Barra vita (rettangolo rosso)
        vita = new JLabel();
        vita.setOpaque(true);
        vita.setBackground(Color.RED);
        vita.setBounds(390, 10, (int) checkVita(), 30); 


        // Etichetta testo vita
        JLabel testoVita = new JLabel("LV 1");
        testoVita.setForeground(Color.WHITE);
        testoVita.setBounds(300, 10, 50, 30);

        // Etichetta HP
        JLabel hpText = new JLabel("HP");
        hpText.setForeground(Color.WHITE);
        hpText.setBounds(370, 10, 30, 30);

        // Numeri vita
        numeriVita = new JLabel(checkVita() + " / " + 100);
        numeriVita.setForeground(Color.WHITE);
        numeriVita.setBounds(500, 10, 100, 30);

        // Aggiungi componenti al pannello vita
        areaVita.add(nome);
        areaVita.add(testoVita);
        areaVita.add(hpText);
        areaVita.add(vita); // Barra rossa
        areaVita.add(numeriVita); // Numeri

        JLabel sfondo = new JLabel(new ImageIcon("Texture/inizio.png"));
        sfondo.setBounds(0, 0, 880, 671);

        this.add(areaTexture);
        this.add(areaScelte);
        this.add(areaVita);
        this.add(sfondo);

        this.setComponentZOrder(sfondo, this.getComponentCount() - 1);

        // Thread Safety: Eseguiamo il refresh finale nell'EDT
        SwingUtilities.invokeLater(() -> {
            Main.mappaClasse.setVisible(false);
            Main.finestra.add(this);
            this.setVisible(true);
            Main.finestra.revalidate();
            Main.finestra.repaint();
        });
    }

    void invertiSprites(Sprite inv1, Sprite inv2, int numLabel) {
        // Evitiamo duplicati
        if (inv1.getClientProperty("timerInversione") != null) return;

        // Inizializziamo lo stato: uno visibile, l'altro no
        inv1.setVisible(true);
        inv2.setVisible(false);

        Timer timer = new Timer(300, event -> {
            // Invertiamo lo stato di visibilità di entrambi
            boolean isInv1Visible = inv1.isVisible();
            
            inv1.setVisible(!isInv1Visible);
            inv2.setVisible(isInv1Visible);
            
            // Opzionale: se gli sprite sono pesanti, forza il refresh
            inv1.repaint();
            inv2.repaint();
        });

        inv1.putClientProperty("timerInversione", timer);
        timer.start();
    }

    // Metodi di supporto (raccogliOcchi e raccoglInversioni) rimasti simili 
    // ma ora non chiamano più .add() inutilmente.
    void raccogliOcchi(Sprite occhio, int numLabel) {
        if("occhio_su".equals(occhio.getTag()) && occhioSu == null) occhioSu = occhio;
        else if("occhio_su".equals(occhio.getTag())) occhioSu2 = occhio;
        else if("occhio_giù".equals(occhio.getTag()) && occhioGiù == null) occhioGiù = occhio;
        else if("occhio_giù".equals(occhio.getTag())) occhioGiù2 = occhio;
        else if("occhio_destra".equals(occhio.getTag()) && occhioDestra == null) occhioDestra = occhio;
        else if("occhio_destra".equals(occhio.getTag())) occhioDestra2 = occhio;
        else if("occhio_sinistra".equals(occhio.getTag()) && occhioSinistra == null) occhioSinistra = occhio;
        else if("occhio_sinistra".equals(occhio.getTag())) occhioSinistra2 = occhio;

        if(occhioSu != null && occhioGiù != null && occhioDestra != null && occhioSinistra != null && !animOcchi1) {
            animOcchi1 = true;
            startAnimOcchi(new Sprite[]{occhioSu, occhioGiù, occhioDestra, occhioSinistra});
        }
        if(occhioSu2 != null && occhioGiù2 != null && occhioDestra2 != null && occhioSinistra2 != null && !animOcchi2) {
            animOcchi2 = true;
            startAnimOcchi(new Sprite[]{occhioSu2, occhioGiù2, occhioDestra2, occhioSinistra2});
        }
    }

    void startAnimOcchi(Sprite[] occhi) {
        Icon[] frames = {occhi[0].getIcon(), occhi[1].getIcon(), occhi[2].getIcon(), occhi[3].getIcon()};
        Timer t = new Timer(500, ev -> {
            int current = (int)(System.currentTimeMillis() / 500 % 4);
            occhi[0].setIcon(frames[current]);
        });
        t.start();
    }

    void raccoglInversioni(Sprite inversione, int numLabel) {
        String tagInv = inversione.getTag().substring(inversione.getTag().indexOf("inverti_") + 8);
        
        Sprite match = null;
        if (tempInversione1 != null && tagInv.equals(tempInversione1.getTag())) { match = tempInversione1; tempInversione1 = null; }
        else if (tempInversione2 != null && tagInv.equals(tempInversione2.getTag())) { match = tempInversione2; tempInversione2 = null; }
        else if (tempInversione3 != null && tagInv.equals(tempInversione3.getTag())) { match = tempInversione3; tempInversione3 = null; }
        else if (tempInversione4 != null && tagInv.equals(tempInversione4.getTag())) { match = tempInversione4; tempInversione4 = null; }

        if (match != null) {
            invertiSprites(inversione, match, numLabel);
        } else {
            if (tempInversione1 == null) tempInversione1 = inversione;
            else if (tempInversione2 == null) tempInversione2 = inversione;
            else if (tempInversione3 == null) tempInversione3 = inversione;
            else if (tempInversione4 == null) tempInversione4 = inversione;
            inversione.setTag(tagInv);
        }
    }
    
    
    void configuraTasti() {
        KeyListener[] listeners = areAnima.getKeyListeners();
        for (KeyListener kl : listeners) {
            areAnima.removeKeyListener(kl);
        }
        
        areAnima.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP: movUp = true; break;
                    case KeyEvent.VK_DOWN: movDown = true; break;
                    case KeyEvent.VK_LEFT: movLeft = true; break;
                    case KeyEvent.VK_RIGHT: movRight = true; break;
                }
                avviaMovementTimerAnima();
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP: movUp = false; break;
                    case KeyEvent.VK_DOWN: movDown = false; break;
                    case KeyEvent.VK_LEFT: movLeft = false; break;
                    case KeyEvent.VK_RIGHT: movRight = false; break;
                }
                if (!movUp && !movDown && !movLeft && !movRight) {
                    if (movementTimer != null) {
                        movementTimer.stop();
                    }
                }
            }
        });
        
        areAnima.setFocusable(true);
        areAnima.requestFocusInWindow();
        
        areAnima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                areAnima.requestFocusInWindow();
                System.out.println("Focus su areAnima - Pronto per movimento!");
                
                System.out.println("Anima pos: " + anima.getBounds());
                System.out.println("BoxAnima: " + boxAnima);
                System.out.println("Anima dentro box: " + boxAnima.contains(anima.getBounds()));
            }
        });
    }

    void avviaMovementTimerAnima() {
        if (movementTimer != null) {
            movementTimer.stop();
        }
        
        movementTimer = new Timer(16, e -> { 
            if (!movUp && !movDown && !movLeft && !movRight) {
                movementTimer.stop();
                return;
            }
            
            int dx = 0, dy = 0;
            
            if (movUp) dy -= animaSpeed;
            if (movDown) dy += animaSpeed;
            if (movLeft) dx -= animaSpeed;
            if (movRight) dx += animaSpeed;
            
            int newX = anima.getX() + dx;
            int newY = anima.getY() + dy;
            
            Rectangle newBounds = new Rectangle(newX, newY, 
                                               anima.getWidth(), anima.getHeight());
            
            if (boxAnima.contains(newBounds)) {
                anima.setLocation(newX, newY);
                areAnima.repaint(anima.getBounds());
            } else {
               
                if (dx != 0 && dy != 0) { 
                    Rectangle horizBounds = new Rectangle(anima.getX() + dx, anima.getY(),
                                                         anima.getWidth(), anima.getHeight());
                    if (boxAnima.contains(horizBounds)) {
                        anima.setLocation(anima.getX() + dx, anima.getY());
                    }
                    // Prova solo verticale  
                    Rectangle vertBounds = new Rectangle(anima.getX(), anima.getY() + dy,
                                                        anima.getWidth(), anima.getHeight());
                    if (boxAnima.contains(vertBounds)) {
                        anima.setLocation(anima.getX(), anima.getY() + dy);
                    }
                }
            }
        });
        
        movementTimer.setInitialDelay(0);
        movementTimer.setCoalesce(true);
        movementTimer.start();
    }
    
    void creaAnima() {
    	boxAnima = new Rectangle(xInizio, yInizio, sizeX, sizeY);
        areAnima = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(5));
                if (boxAnima != null) g2.draw(boxAnima);
            }
        };
        configuraTasti();
        areAnima.setFocusable(true);
        areAnima.requestFocusInWindow();
        areAnima.setLayout(null);
        areAnima.setBounds(0, 268, 880, 201);
        areAnima.setOpaque(false);
        anima = new Anima(Main.personaggioSelezionato);
        areAnima.add(anima);
        anima.setLocation(440, 45);
        areAnima.setComponentZOrder(anima, 0);
        anima.setVisible(true);
        this.add(areAnima);
    }
    
    double checkVita() {
        double vitaAttuale = Main.personaggioSelezionato.vita;
        if (this.vita != null) {
            this.vita.setBounds(390, 10, (int) vitaAttuale, 30);
            this.revalidate();
            this.repaint();
        }
        if(this.numeriVita != null) {
        	numeriVita.setText((int) (Main.personaggioSelezionato.vita) + " / " + 100);
            numeriVita.setForeground(Color.WHITE);
            numeriVita.setBounds(500, 10, 100, 30);
            this.revalidate();
            this.repaint();
        }
        return vitaAttuale;
    }
    
    void inventario() {
    	areInv = new JPanel();
    	areInv.setBounds(0, 268, 880, 201);
    	areInv.setLayout(null);
    	areInv.setBackground(Color.BLACK);
    	int spazioX = 30;
    	int spazioY = 30;
    	
    	int countItems = 0;
    	for(Item item: Main.personaggioSelezionato.inventario) {
    		if(countItems >= 8) break; //Per adesso, poi ci sarà qualcos'altro
    		item.setBounds(spazioX, spazioY, 200, 40);
    		item.setFont(item.getFont().deriveFont(Font.BOLD, 20f));
    		item.setForeground(Color.WHITE);
    		item.setBackground(Color.BLACK);
    		areInv.add(item);
    		countItems++;
    		if((countItems % 2) != 0) {
    			spazioY += 100;
    		}else if((countItems % 2) == 0) {
    			spazioY -= 100;
    			spazioX += 250;
    		}
    		
    	}
    	if(areAnima != null && areAnima.getParent() == this) {
    		this.remove(areAnima);
    	}
    	if(areAttacco != null && areAttacco.getParent() == this) {
    		this.remove(areAttacco);
    	}
    	this.add(areInv);
    }
}