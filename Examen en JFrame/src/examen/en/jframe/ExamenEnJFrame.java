/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.en.jframe;

/**
 * @(#)Colisiones.java
 *
 * AppletExamen
 *
 * @author David Martinez
 */
import javax.swing.JFrame;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import java.util.LinkedList;
import java.awt.Toolkit;
public class ExamenEnJFrame extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables.

    private int x1; // posicion del mouse en x
    private int y1; // posicion del mouse en y
    //~~~~~~~~~~~~~~~~
    private int direccion;    // Direccion del elefante
    private int movilidad; // determinado por vidas
    private int contador;
    private int conto;
    private int incX;    // Incremento en x
    private int incY;    // Incremento en y
    // ~~~~~~~~~~~~~~~~
    private final int MIN = -5;    //Minimo al generar un numero al azar.
    private final int MAX = 6;    //Maximo al generar un numero al azar.
    private final int MINI = 9;
    private final int MAXI = 10;
    private final int MAXO = 6; // movilidad 
    private final int MIXO = 3; // movilidad
    private final int MINO = 1;
    private final int MAXOR = 3;
    private int inco;
    //~~~~~~~~~~~~~~~~~
    private Image dbImage;	// Imagen a proyectar	
    private Image gameover;
    private Graphics dbg;	// Objeto grafico
    private SoundClip sonido;    // Objeto AudioClip
    private SoundClip rat;    // Objeto AudioClip
    private SoundClip bomb;    //Objeto AudioClip 
    private Bueno dumbo;    // Objeto de la clase Elefante
    private Malo raton;    //Objeto de la clase Raton
    private ImageIcon elefante; // Imagen del elefante.
    private int posrX;
    private int posrY;
    // ~~~~~~~~~~~~~~~~
    private boolean mueve = false;
    private boolean pausar = false;
    private boolean pico = false;
    private boolean choco = false;
    // ~~~~~~~~~~~~~~~~
    private int vidas;
    private LinkedList lista;
    // ~~~~~~~~~~~~~~~~
    //Variables de control de tiempo de la animación
    private long tiempoActual;
    private long tiempoInicial;

    public ExamenEnJFrame() {
        init();
        start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Metodo <I>init</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos a
     * usarse en el <code>Applet</code> y se definen funcionalidades.
     */
    public void init() {
        direccion = 0;
        vidas = 5;
        contador = 0;
        conto = 0;
        inco = ((int) (Math.random() * (MAXOR - MINO))) + MINO;
        if (inco == 1) {
            inco = 6;
        } else if (inco == 2) {
            inco = 10;
        } else if (inco == 3) {
            inco = 12;
        }
        //~~~~~~~~~~~
        this.setSize(1000, 500);
        int posX = (int) (getWidth()/2);    // posicion en x es un cuarto del applet
        int posY = (int) (getHeight()/2);    // posicion en y es un cuarto del applet
        dumbo = new Bueno(posX, posY);
        // Cargamos texto final.
        URL noURL = this.getClass().getResource("Images/text.gif");
        gameover = Toolkit.getDefaultToolkit().getImage(noURL);
        setBackground(Color.black);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        //Se cargan los sonidos.
        sonido = new SoundClip("Musica/lifeloss.wav");
        rat = new SoundClip("Musica/lifeloss.wav");
        bomb = new SoundClip("Musica/explosion.wav");
        // ~~~~~~~~~~~~~~~~
        lista = new LinkedList();
        for (int i = 0; i < inco; i++) {
            int posrX = (int) (Math.random() * (getWidth()));
            int posrY = (int) (Math.random() * (getHeight() / 2));
            //Se cargan las imágenes(cuadros) para la animación
            raton = new Malo(posrX, posrY);
            raton.setPosX(posrX);
            raton.setPosY(posrY);
            lista.addLast(raton);
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
    @Override
    public void run() {
        //Guarda el tiempo actual del sistema
        tiempoActual = System.currentTimeMillis();
        //Ciclo principal del Applet. Actualiza y despliega en pantalla la animación hasta que el Applet sea cerrado
        while (true) {
            if (!pausar) {
                actualiza();
                checaColision();
                repaint(); // Se actualiza el <code>Applet</code> repintando el contenido.
            }
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

        //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        dumbo.actualiza(tiempoTranscurrido);
        raton.actualiza(tiempoTranscurrido);

        if (pico) {
            dumbo.setPosX(x1);
            dumbo.setPosY(y1);
            pico = false;
        }

        movilidad = ((int) (Math.random() * (MAXO - MIXO))) + MIXO;
        // al meterlo en el ciclo for, todos los asteroides se actualizan.
        for (int i = 0; i < lista.size() / 2; i++) {
            Malo raton = (Malo) lista.get(i);
            incX = ((int) (Math.random() * (MAX - MIN))) + MIN;
            incY = ((int) (Math.random() * (MAX - MIN))) + MIN;

            if (raton.getPosX() < getWidth()) {
                // si la posicion x del raton es menor que el ancho 
                raton.setPosX(raton.getPosX() + movilidad);
            }
            if (raton.getPosX() == getWidth()) {
                // si choca con la parte de abajo del applet vuelven a aparecer
                int posrX = (int) (Math.random() * (getWidth() / 2));
                int posrY = (int) (Math.random() * (getHeight()));
                raton.setPosX(posrX);
                raton.setPosY(posrY);

            }
            raton.setPosX(raton.getPosX() + incX);
            raton.setPosY(raton.getPosY() + incY);
        }
        for (int i = lista.size() / 2; i < lista.size(); i++) {
            Malo raton = (Malo) lista.get(i);
            incX = ((int) (Math.random() * (MAX - MIN))) + MIN;
            incY = ((int) (Math.random() * (MAX - MIN))) + MIN;

            if (raton.getPosX() > 0) {
                raton.setPosX(raton.getPosX() - movilidad);
            }
            if (raton.getPosX() == 0) {
                // si choca con la parte de abajo del applet vuelven a aparecer
                int posrX = (int) (Math.random() * (getWidth() / 2));
                int posrY = (int) (Math.random() * (getHeight()));
                raton.setPosX(posrX);
                raton.setPosY(posrY);
            }
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
            Malo raton = (Malo) lista.get(i);
            if (dumbo.intersecta(raton)) {
                //bomb.play();    //sonido al colisionar
                int posrX = (int) (Math.random() * (getWidth()));
                int posrY = (int) (Math.random() * (getHeight() / 2));
                raton.setPosX(posrX);
                raton.setPosY(posrY);
                choco = true;
                contador++;
                bomb.play();
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
    @Override
    public void paint(Graphics g) {
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
        paint1(dbg);

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
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_A) {
            direccion = 3;
            mueve = true;

        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            direccion = 4;
            mueve = true;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            pausar = !pausar;
        }

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
    @Override
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
    @Override
    public void keyReleased(KeyEvent e) {
        direccion = 0;
    }

    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>, heredado
     * de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {
        if (dumbo != null && lista != null) {
            //Dibuja la imagen en la posicion actualizada
            g.drawImage(dumbo.getImagenI(), dumbo.getPosX(), dumbo.getPosY(), this);

            g.setColor(Color.white);
            g.drawString(" Puntaje = " + contador, 60, 60);
            if (pausar) {
                g.setColor(Color.white);
                //g.drawString(Bueno.getPAUSADO(), 500, 250);
                g.drawString(dumbo.getPAUSADO(), dumbo.getPosX() + dumbo.getAncho() / 3, dumbo.getPosY() + dumbo.getAlto() / 2);
            }
            if (choco) {
                g.setColor(Color.white);
                g.drawString(dumbo.getDESAPARECE(), raton.getPosX() + raton.getAncho() / 3, raton.getPosY() + raton.getAlto() / 2);
            }
            for (int i = 0; i < lista.size(); i++) {
                Malo raton = (Malo) lista.get(i);
                g.drawImage(raton.getImagenI(), raton.getPosX(), raton.getPosY(), this);
            }
            choco = false;
        } else {
            //Da un mensaje mientras se carga el dibujo	
            g.drawString("No se cargo la imagen..", 20, 20);
        }
    }

    /**
     * Metodo <I>mouseClicked</I> sobrescrito de la interface
     * <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar el mouse
     *
     * @param e es el <code>evento</code> que se genera en al presionar el mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        x1 = e.getX();//agarras la x del mouse
        y1 = e.getY();
        pico = true;
    }

    /**
     * Metodo <I>mouseEntered</I> sobrescrito de la interface
     * <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al ingresar el mouse.
     *
     * @param e es el <code>evento</code> que se genera en al ingresar el mouse
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Metodo <I>mouseExited</I> sobrescrito de la interface
     * <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al sacar el mouse.
     *
     * @param e es el <code>evento</code> que se genera en al sacar el mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Metodo <I>mousePressed</I> sobrescrito de la interface
     * <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar el mouse.
     *
     * @param e es el <code>evento</code> que se genera en al presionar el mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Metodo <I>mouseReleased</I> sobrescrito de la interface
     * <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar el mouse.
     *
     * @param e es el <code>evento</code> que se genera en al soltar el mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Metodo <I>mouseMoved</I> sobrescrito de la interface
     * <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al mover el mouse.
     *
     * @param e es el <code>evento</code> que se genera en al mover el mouse
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Metodo <I>mouseDragged</I> sobrescrito de la interface
     * <code>MouseListener</code>.<P>
     * En este metodo maneja el evento que se genera al jalar el mouse.
     *
     * @param e es el <code>evento</code> que se genera en al jalar el mouse
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

}
