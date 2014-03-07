/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreak;

/**
 *
 * @author David Martinez A01191485
 */
import javax.swing.JFrame;
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
import java.awt.Toolkit;
import java.util.LinkedList;
//prueba

public class broken extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables.

    private int x1; // posicion del mouse en x
    private int y1; // posicion del mouse en y
    //~~~~~~~~~~~~~~~~
    private int direccion;    // Direccion del elefante
    private int puntos;
    private int perdida;
    private int velocidad;
    private double velocidadx;
    private double velocidady;
    private double angulo;
    private Image instrucciones;
    private Brick brick;
    private double px;
    private double rx;
    private double tiempo;
    private int incX;    // Incremento en x
    private int incY;    // Incremento en y
    //~~~~~~~~~~~~~~~~~
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private SoundClip aww;    // Objeto AudioClip
    private SoundClip yay;    // Objeto AudioClip
    private SoundClip bomb;    //Objeto AudioClip 
    private Bueno tank;    // Objeto de la clase Elefante
    private Malo Pelota;    //Objeto de la clase Raton
    private ImageIcon elefante; // Imagen del elefante.
    private int posrX;
    private int posrY;
    // ~~~~~~~~~~~~~~~~
    private boolean left = false;
    private boolean right = false;
    private boolean pausar = false;
    private boolean pico = false;
    private boolean time = false;
    private boolean instr = false;
    private boolean sound_off = false;
    private LinkedList lista;
    // ~~~~~~~~~~~~~~~~
    private int vidas;
    // ~~~~~~~~~~~~~~~~
    //Variables de control de tiempo de la animación e
    private long tiempoActual;
    private long tiempoInicial;
    private String nombreArchivo;

    public broken() {
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
        puntos = 0;
        perdida = 0;
        //px = velocidad * (Math.cos(angulo)); // actualiza la posicion x
        //~~~~~~~~~~~
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setSize(1000, 700);
        // Cargamos texto final.
        URL noURL = this.getClass().getResource("Images/instrucciones.gif");
        instrucciones = Toolkit.getDefaultToolkit().getImage(noURL);
        setBackground(Color.black);
        //~~~~~~~~~~~ Creamos a la canasta
        int posY = (int) (getHeight() - 100); // La canasta va en la parte de abajo
        int posX = (int) (getWidth() / 2); //Creamos en el 3er cuarto
        tank = new Bueno(posX, posY);
        tank.setPosX(posX);
        tank.setPosY(posY);
        //~~~~~~~~~~~ Creamos a la bola
        int posrX = (int) (tank.getPosX() + 30);    // posicion en x es un cuarto del applet
        int posrY = (int) (tank.getPosY() - 10);    // posicion en y es un cuarto del applet
        Pelota = new Malo(posrX, posrY);
        Pelota.setPosX(posrX);
        Pelota.setPosY(posrY);
        angulo = (int) ((Math.random() * (60 - 45)) + 45); // angulo general
        velocidad = (int) ((Math.random() * (6 - 4)) + 4);
        //~~~~~~~~~ Creamos los bloques       
        lista = new LinkedList();
        for (int i = 0; i < 13; i++) {
            for (int r = 0; r < 6; r++) {
                brick = new Brick(30 + 72 * i, r * 30 + 72);
                lista.addLast(brick);
            }
        }
        //Se cargan los awws.
        aww = new SoundClip("Musica/aww.wav");
        yay = new SoundClip("Musica/yay.wav");
        nombreArchivo = "tarea_pareja.txt";
        //bomb = new SoundClip("Musica/explosion.wav");
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
        while (vidas > 0) {
            if (!pausar && !instr) {
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
     * Metodo usado para actualizar la posicion de objetos elefante y Pelota.
     *
     */
    public void actualiza() {
        //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        tank.actualiza(tiempoTranscurrido);
        Pelota.actualiza(tiempoTranscurrido);
        brick.actualiza(tiempoTranscurrido);

        if (time) {
            tiempo += .020;
        }
        if (!pausar) {
            if (pico) {
                velocidadx = 5;
                velocidady = -5;
                time = true;
                pico = false;
            }
        }
        Pelota.setPosX(Pelota.getPosX() + (int) velocidadx);
        Pelota.setPosY(Pelota.getPosY() + (int) velocidady);

        if (left) { // movimiento de la canasta.
            tank.setPosX(tank.getPosX() - 20);
            left = false;
        } else if (right) {
            tank.setPosX(tank.getPosX() + 20);
            right = false;
        }
        if (tank.getPosX() + tank.getAncho() >= (getWidth())) {
            tank.setPosX(getWidth() - tank.getAncho());
        }
        if (tank.getPosX() <= 0) {
            tank.setPosX(0);
        }
    }

    /**
     * Metodo usado para checar las colisiones del objeto elefante y Pelota con
     * las orillas del <code>Applet</code>.
     */
    public void checaColision() {
        if (Pelota.getPosX() + Pelota.getAncho() >= getWidth()) { // si choca en el lado derehco
            velocidadx = velocidadx * (-1);
        }
        if (Pelota.getPosX() + Pelota.getAncho() <= 0) {
            velocidadx = velocidadx * (-1);
        }
        if (Pelota.getPosY() + Pelota.getAlto() >= getHeight()) {
            velocidadx = 0;
            velocidady = 0;
            int posY = (int) (getHeight() - 100); // La canasta va en la parte de abajo
            int posX = (int) (getWidth() / 2); //Creamos en el 3er cuarto
            tank.setPosX(posX);
            tank.setPosY(posY);
            int posrX = (int) (tank.getPosX() + 30);    // posicion en x es un cuarto del applet
            int posrY = (int) (tank.getPosY() - 10);    // posicion en y es un cuarto del applet
            Pelota.setPosX(posrX);
            Pelota.setPosY(posrY);
            time = false;
            pico = false;
            tiempo = 0;
            vidas--;
        }
        if (Pelota.getPosY() <= 0) {
            velocidady = velocidady * (-1);
        }
        //Colision entre objetos
        if (Pelota.intersecta(tank) && (Pelota.getPosY() + Pelota.getAlto() - 5) <= tank.getPosY()) {
            if (Pelota.getPosX() + Pelota.getAncho() / 2 < tank.getPosX() + tank.getAncho() / 2 && velocidadx > 0) {
                velocidady = velocidady * (-1);
            } else if (Pelota.getPosX() + Pelota.getAncho() / 2 > tank.getPosX() + tank.getAncho() / 2 && velocidadx < 0) {
                velocidady = velocidady * (-1);
            }
            //velocidady = velocidady * (-1);
        }
        for (int i = 0; i < lista.size(); i++) {
            Brick brick = (Brick) lista.get(i);
            if (Pelota.intersecta(brick) && (brick.getPosY() + brick.getAlto() - 15) < Pelota.getPosY()) {
                velocidady = velocidady * (-1);
                lista.remove(i);
                puntos++;
            } else if (Pelota.intersecta(brick) && brick.getPosY() + brick.getAlto() - 15 >= Pelota.getPosY()) {
                velocidadx = velocidadx * (-1);
                lista.remove(i);
                puntos++;
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
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direccion = 3;
            left = true;

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direccion = 4;
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            pausar = !pausar;
        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            instr = !instr;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            sound_off = !sound_off;
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
        if (vidas > 0) {
            if (tank != null && Pelota != null) {
                //Dibuja la imagen en la posicion actualizada
                // draw image antes que todo
                //g.drawImage(
                g.drawImage(tank.getImagenI(), tank.getPosX(), tank.getPosY(), this);
                g.drawImage(Pelota.getImagenI(), Pelota.getPosX(), Pelota.getPosY(), this);
                g.setColor(Color.white);
                g.drawString(" Puntaje = " + puntos, 60, 60);
                g.drawString(" Vidas = " + vidas, 200, 60);
                if (pausar) {
                    g.setColor(Color.white);
                    g.drawString(Bueno.getPAUSADO(), tank.getPosX() + tank.getAncho() / 3, tank.getPosY() + tank.getAlto() / 2);
                } else if (instr) {

                    g.drawImage(instrucciones, 0, 0, this);
                }
                for (int i = 0; i < lista.size(); i++) {
                    Brick brick = (Brick) lista.get(i);
                    g.drawImage(brick.getImagenI(), brick.getPosX(), brick.getPosY(), this);
                }
            } else {
                //Da un mensaje mientras se carga el dibujo	
                g.drawString("No se cargo la imagen..", 20, 20);
            }
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
        if (Pelota.contiene(x1, y1)) {
            pico = true;
        }
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
//a

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
