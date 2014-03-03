
/**
 * @(#)Colisiones.java
 *
 * Colisiones Applet application
 *
 * @author Antonio Mejorado
 * @version 1.00 2008/6/18
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import java.util.LinkedList;

public class Colisiones extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables.
    private int x1; // posicion del mouse en x
    private int y1; // posicion del mouse en y
    private int direccion;    // Direccion del elefante
    private int movilidad = 2; // determinado por vidas
    private int contador = 0;
    private int conto = 0;
    private int incX;    // Incremento en x
    private int incY;    // Incremento en y
    private int inco;
    private final int MIN = -5;    //Minimo al generar un numero al azar.
    private final int MAX = 6;    //Maximo al generar un numero al azar.
    private final int MINI = 3;   //Para determinar el numero minimo de asteroi.
    private final int MAXI = 6;
    private Image dbImage;	// Imagen a proyectar	
    private Image gameover;
    private Graphics dbg;	// Objeto grafico
    private AudioClip sonido;    // Objeto AudioClip
    private AudioClip rat;    // Objeto AudioClip
    private AudioClip bomb;    //Objeto AudioClip 
    private Elefante dumbo;    // Objeto de la clase Elefante
    private Raton raton;    //Objeto de la clase Raton
    private int ancho;	//Ancho del elefante
    private int alto;	//Alto del elefante
    private ImageIcon elefante; // Imagen del elefante.
    private boolean pico = false;
    private boolean choco = false;
    private boolean chioco = false;
    private boolean lastima = false;
    private int x_pos;
    private int y_pos;
    private int vidas = 5;
    private LinkedList lista;

    /**
     * Metodo <I>init</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos a
     * usarse en el <code>Applet</code> y se definen funcionalidades.
     */
    public void init() {
        direccion = 0;
        int posX = (int) (450);    // posicion en x es un cuarto del applet
        int posY = (int) (500);    // posicion en y es un cuarto del applet
        URL eURL = this.getClass().getResource("Imagenes/planeta.gif");
        dumbo = new Elefante(posX, posY, Toolkit.getDefaultToolkit().getImage(eURL));
        // Cargamos texto final.
        URL noURL = this.getClass().getResource("Imagenes/text.gif");
        gameover = Toolkit.getDefaultToolkit().getImage(noURL);
        setBackground(Color.black);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setSize(1000, 500);
        // ~~~~~~~~~~~~~~~~

        //Se cargan los sonidos.
        URL eaURL = this.getClass().getResource("musica/lifeloss.wav");
        sonido = getAudioClip(eaURL);
        URL raURL = this.getClass().getResource("musica/hit.wav");
        rat = getAudioClip(raURL);
        URL baURL = this.getClass().getResource("musica/explosion.wav");
        bomb = getAudioClip(baURL);
        // ~~~~~~~~~~~~~~~~

        lista = new LinkedList();
        inco = ((int) (Math.random() * (MAXI - MINI))) + MINI;
        while (inco != 0) {
            int posrX = (int) (Math.random() * ((getWidth() - 10) - 10)) + 10;    //posision x en arriba del applet
            int posrY = (int) 0;   //debe aparecer arriba namas
            URL rURL = this.getClass().getResource("Imagenes/asteroide.gif");
            raton = new Raton(posrX, posrY, Toolkit.getDefaultToolkit().getImage(rURL));
            raton.setPosX(raton.getPosX() - raton.getAncho());
            raton.setPosY(raton.getPosY() - raton.getAlto());
            lista.addLast(raton);
            inco--;
        }

    }

    /**
     * Metodo <I>start</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se crea e inicializa el hilo para la animacion este metodo
     * es llamado despues del init o cuando el usuario visita otra pagina y
     * luego regresa a la pagina en donde esta este <code>Applet</code>
     *
     */
    public void start() {
        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }

    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     *
     */
    public void run() {
        while (vidas > 0) {
            actualiza();
            checaColision();
            repaint();    // Se actualiza el <code>Applet</code> repintando el contenido.
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }

        }
    }

    /**
     * Metodo usado para actualizar la posicion de objetos elefante y raton.
     *
     */
    public void actualiza() {
        // en estos ifs es donde se especifica que persiga a el dumbo.
        // debes hacer si el la posicion es menor que el x mayor. se vaya pa ya
/*        if (vidas == 6) {
         movilidad = 1;
         } else if (vidas == 5) {
         movilidad = 2;
         } else if (vidas == 4) {
         movilidad = 3;
         } else if (vidas == 3) {
         movilidad = 4;
         } else if (vidas == 2) {
         movilidad = 5;
         } else if (vidas == 1) {
         movilidad = 6;
         }
         */
        if (pico) { // ya declara que es true.
            dumbo.setPosX(x1 - 30);
            dumbo.setPosY(y1 - 30);
            pico = false;
        }

        // al meterlo en el ciclo for, todos los asteroides se actualizan.
        for (int i = 0; i < lista.size(); i++) {
            Raton raton = (Raton) lista.get(i);
            incX = ((int) (Math.random() * (MAX - MIN))) + MIN;
            incY = ((int) (Math.random() * (MAX - MIN))) + MIN;
            if (raton.getPosY() < getHeight()) {
                raton.setPosY(raton.getPosY() + movilidad);
            } else {
                raton.setPosY(raton.getPosY() - movilidad);
            }
            if (raton.getPosY() == getHeight()) {
                int posrX = (int) (Math.random() * ((getWidth() - 10) - 10)) + 10;
                raton.setPosY(0);
                raton.setPosX(posrX);
                contador = contador - 20;
                conto++;
                rat.play();
                if (conto == 10) {
                    vidas--;
                    movilidad++;
                    conto = 0;
                    sonido.play();
                }
            }
            //Acutalizo la posicion del raton
            raton.setPosX(raton.getPosX() + incX);
            raton.setPosY(raton.getPosY() + incY);
        }
    }

    /**
     * Metodo usado para checar las colisiones del objeto elefante y raton con
     * las orillas del <code>Applet</code>.
     */
    public void checaColision() {
        // checa colision con el applet para que no se salga.
        if (dumbo.getPosX() + dumbo.getAncho() > getWidth()) {
            dumbo.setPosX(getWidth() - dumbo.getAncho());
        }
        if (dumbo.getPosX() < 0) {
            dumbo.setPosX(0);
        }
        if (dumbo.getPosY() + dumbo.getAlto() > getHeight()) {
            dumbo.setPosY(getHeight() - dumbo.getAlto());
        }
        if (dumbo.getPosY() < 0) {
            dumbo.setPosY(0);
        }

        //Colision entre objetos
        for (int i = 0; i < lista.size(); i++) {
            Raton raton = (Raton) lista.get(i);
            if (dumbo.intersecta(raton) && (raton.getPosY() + raton.getAlto() - 5) < dumbo.getPosY()) {
                // si el planeta choca con el asteroide y la altura del asteroide es menor que la de el planeta
                bomb.play();    //sonido al colisionar
                raton.setPosX((int) (Math.random() * (getWidth())));
                raton.setPosY((int) (0));
                chioco = true;
                contador = contador + 100;
            }
        }
    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void update(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Metodo <I>keyPressed</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar cualquier la
     * tecla.
     *
     * @param e es el <code>evento</code> generado al presionar las teclas.
     */
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Metodo <I>keyTyped</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una tecla que
     * no es de accion.
     *
     * @param e es el <code>evento</code> que se genera en al presionar las
     * teclas.
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metodo <I>keyReleased</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar la tecla
     * presionada.
     *
     * @param e es el <code>evento</code> que se genera en al soltar las teclas.
     */
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>, heredado
     * de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        if (vidas > 0) {
            if (dumbo != null && raton != null) {
                //Dibuja la imagen en la posicion actualizada
                g.drawImage(dumbo.getImagenI(), dumbo.getPosX(), dumbo.getPosY(), this);
                g.drawImage(raton.getImagenI(), raton.getPosX(), raton.getPosY(), this);
                g.setColor(Color.white);
                g.drawString(" Vidas = " + vidas, 10, 30);
                g.drawString(" Score = " + contador, 10, 10);
            } else {
                //Da un mensaje mientras se carga el dibujo	
                g.drawString("No se cargo la imagen..", 20, 20);
            }
        } else {
            g.drawImage(gameover, 400, 150, this);
        }

        for (int i = 0; i < lista.size(); i++) {
            Raton raton = (Raton) lista.get(i);
            g.drawImage(raton.getImagenI(), raton.getPosX(), raton.getPosY(), this);
        }

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        x1 = e.getX();//agarras la x del mouse
        y1 = e.getY();
        if (dumbo.contiene(x1, y1)) {
            pico = true;

        }
    }

}
