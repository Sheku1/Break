/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreak;

/**
 *
 * @author David
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
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
    private Bueno Canasta;    // Objeto de la clase Elefante
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
        int posX = (int) (getWidth() - (getWidth() / 4)); //Creamos en el 3er cuarto
        Canasta = new Bueno(posX, posY);
        Canasta.setPosX(posX);
        Canasta.setPosY(posY);
        //~~~~~~~~~~~ Creamos a la bola
        int posrX = (int) (10);    // posicion en x es un cuarto del applet
        int posrY = (int) (getHeight() / 2);    // posicion en y es un cuarto del applet
        Pelota = new Malo(posrX, posrY);
        Pelota.setPosX(posrX);
        Pelota.setPosY(posrY);
        angulo = (int) ((Math.random() * (60 - 45)) + 45); // angulo general
        velocidad = (int) ((Math.random() * (6 - 4)) + 4);
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
    /*public void leeArchivo()  {
                                                          
     BufferedReader fileIn;
     try {
     fileIn = new BufferedReader(new FileReader(nombreArchivo));
     } catch (FileNotFoundException e){
     File puntos = new File(nombreArchivo);
     PrintWriter fileOut = new PrintWriter(puntos);
     fileOut.println("100");
     fileOut.close();
     fileIn = new BufferedReader(new FileReader(nombreArchivo));
     }
     String dato = fileIn.readLine();
     while(dato != null) {  
                                                        
     arr = dato.split(",");
     int num = (Integer.parseInt(arr[0]));
     String nom = arr[1];
     vec.add(new Puntaje(nom,num));
     dato = fileIn.readLine();
     }
     fileIn.close();
     }
    
     public void grabaArchivo() {
                                                          
     PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
     for (int i = 0; i < vec.size(); i++) {

     Puntaje x;
     x = (Puntaje) vec.get(i);
     fileOut.println(x.toString());
     }
     fileOut.close();
     }*/

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
        Canasta.actualiza(tiempoTranscurrido);
        Pelota.actualiza(tiempoTranscurrido);

        if (time) {
            tiempo += .020;
        }

        if (pico) {
            velocidadx = velocidad * (Math.sin(Math.toRadians(angulo)));
            velocidady = -velocidad + 2.5 * tiempo;
            time = true;
            Pelota.setPosX(Pelota.getPosX() + (int) velocidadx);
            Pelota.setPosY(Pelota.getPosY() + (int) velocidady);
        }

        if (perdida == 3) {
            vidas--;
            perdida = 0;
            velocidad = velocidad + 2;
        }

        if (left) {
            Canasta.setPosX(Canasta.getPosX() - 5);
            left = false;
        } else if (right) {
            Canasta.setPosX(Canasta.getPosX() + 5);
            right = false;
        }
        if (Canasta.getPosX() <= (getWidth() / 2)) {
            Canasta.setPosX(getWidth() / 2);
        }
        if (Canasta.getPosX() + Canasta.getAncho() >= (getWidth())) {
            Canasta.setPosX(getWidth() - Canasta.getAncho());
        }
    }

    /**
     * Metodo usado para checar las colisiones del objeto elefante y Pelota con
     * las orillas del <code>Applet</code>.
     */
    public void checaColision() {
        // Con la formula no deberia de haber ifs
        if (Pelota.getPosX() >= getWidth()) {
            int posrX = (int) (0);    // posicion en x es un cuarto del applet
            int posrY = (int) (getHeight() / 2);    // posicion en y es un cuarto del applet
            Pelota.setPosX(posrX);
            Pelota.setPosY(posrY);
            time = false;
            pico = false;
            tiempo = 0;
            angulo = (int) ((Math.random() * (60 - 45)) + 45); // angulo general
            velocidad = (int) ((Math.random() * (6 - 4)) + 4);
            if (!sound_off) {
                aww.play();
            }

        }
        if (Pelota.getPosY() >= getHeight()) {
            int posrX = (int) (0);    // posicion en x es un cuarto del applet
            int posrY = (int) (getHeight() / 2);    // posicion en y es un cuarto del applet
            Pelota.setPosX(posrX);
            Pelota.setPosY(posrY);
            perdida++;
            time = false;
            pico = false;
            tiempo = 0;
            angulo = (int) ((Math.random() * (60 - 45)) + 45); // angulo general
            velocidad = (int) ((Math.random() * (6 - 4)) + 4);
            // emitir aww de tristeza
            if (!sound_off) {
                aww.play();
            }
        }
        //Colision entre objetos
        if (Pelota.intersecta(Canasta) && (Pelota.getPosY() + Pelota.getAlto() - 5) < Canasta.getPosY()) {
            int posrX = (int) (0);    // posicion en x es un cuarto del applet
            int posrY = (int) (getHeight() / 2);    // posicion en y es un cuarto del applet
            Pelota.setPosX(posrX);
            Pelota.setPosY(posrY);
            puntos = puntos + 2;
            time = false;
            pico = false;
            tiempo = 0;
            angulo = (int) ((Math.random() * (60 - 45)) + 45); // angulo general
            velocidad = (int) ((Math.random() * (6 - 4)) + 4);
            // emitir aww de alegria
            if (!sound_off) {
                yay.play();
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
        if (vidas > 0) {
            if (Canasta != null && Pelota != null) {
                //Dibuja la imagen en la posicion actualizada
                g.drawImage(Canasta.getImagenI(), Canasta.getPosX(), Canasta.getPosY(), this);
                g.drawImage(Pelota.getImagenI(), Pelota.getPosX(), Pelota.getPosY(), this);
                g.setColor(Color.white);
                g.drawString(" Puntaje = " + puntos, 60, 60);
                g.drawString(" Vidas = " + vidas, 60, 80);
                if (pausar) {
                    g.setColor(Color.white);
                    g.drawString(Bueno.getPAUSADO(), Canasta.getPosX() + Canasta.getAncho() / 3, Canasta.getPosY() + Canasta.getAlto() / 2);
                } else if (instr) {

                    g.drawImage(instrucciones, 0, 0, this);
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
