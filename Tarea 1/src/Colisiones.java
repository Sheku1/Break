
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
import java.net.URL;

public class Colisiones extends Applet implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables.
    private int counter = 0;
    private int direccion;    // Direccion del elefante
    private int incX;    // Incremento en x
    private int incY;    // Incremento en y
    private final int MIN = -5;    //Minimo al generar un numero al azar.
    private final int MAX = 6;    //Maximo al generar un numero al azar.
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private AudioClip sonido;    // Objeto AudioClip
    private AudioClip rat;    // Objeto AudioClip
    private AudioClip bomb;    //Objeto AudioClip 
    private Elefante dumbo;    // Objeto de la clase Elefante
    private Elefante monito;
    private boolean choco = false;
    private int velocidad = 1;

    /** 
     * Metodo <I>init</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos
     * a usarse en el <code>Applet</code> y se definen funcionalidades.
     */
    public void init() {
        direccion = 4;
        int posX = (int) (Math.random() * (getWidth() / 4));    // posicion en x es un cuarto del applet
        int posY = (int) (Math.random() * (getHeight() / 4));    // posicion en y es un cuarto del applet
        URL eURL = this.getClass().getResource("Imagenes/dalmata.gif"); // cambiar a otro
        URL aURL = this.getClass().getResource("Imagenes/sentado.gif"); // el que choco
        dumbo = new Elefante(posX, posY, Toolkit.getDefaultToolkit().getImage(eURL)); // crea el elefante.
        monito = new Elefante(posX, posY, Toolkit.getDefaultToolkit().getImage(aURL));
        setBackground(Color.green);
        addKeyListener(this);
        URL eaURL = this.getClass().getResource("Sonidos/bark.wav");
        sonido = getAudioClip(eaURL); // el de el pajaro al chocar
        this.setSize(800, 500);
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
        while (true) {
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
     * Metodo usado para actualizar la posicion y la velocidad de 
     * el perro
     */
    public void actualiza() {
        //Dependiendo de la direccion del elefante es hacia donde se mueve.
        switch (direccion) {
            case 1: {
                dumbo.setPosY(dumbo.getPosY() - velocidad);
                break;    //se mueve hacia arriba
            }
            case 2: {
                dumbo.setPosY(dumbo.getPosY() + velocidad);
                break;    //se mueve hacia abajo
            }
            case 3: {
                dumbo.setPosX(dumbo.getPosX() - velocidad);
                break;    //se mueve hacia izquierda
            }
            case 4: {
                dumbo.setPosX(dumbo.getPosX() + velocidad);
                break;    //se mueve hacia derecha	
            }
        }
        // genero un numero al azar en incx e incy de -5 a 5
        incX = ((int) (Math.random() * (MAX - MIN))) + MIN;
        incY = ((int) (Math.random() * (MAX - MIN))) + MIN;

    }

    /**
     *
     * Metodo usado para checar las colisiones del perro con las orillas del
     * <code>Applet</code>.
     */
    public void checaColision() {
        //Colision del elefante con el Applet dependiendo a donde se mueve.
        switch (direccion) {
            case 1: { //se mueve hacia arriba con la flecha arriba.
                if (dumbo.getPosY() < 0) {
                    direccion = 2;
                    sonido.play();
                    choco = true;
                }
                break;
            }
            case 2: { //se mueve hacia abajo con la flecha abajo.
                if (dumbo.getPosY() + dumbo.getAlto() > getHeight()) {
                    direccion = 1;
                    sonido.play();
                    choco = true;
                }
                break;
            }
            case 3: { //se mueve hacia izquierda con la flecha izquierda.
                if (dumbo.getPosX() < 0) {
                    direccion = 4;
                    sonido.play();
                    choco = true;
                }
                break;
            }
            case 4: { //se mueve hacia derecha con la flecha derecha.
                if (dumbo.getPosX() + dumbo.getAncho() > getWidth()) {
                    direccion = 3;
                    sonido.play();
                    choco = true;
                }
                break;
            }
        }

    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
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
     * @param e es el <code>evento</code> generado al presionar las teclas.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && direccion == 1) {
            velocidad++;

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && direccion == 1) {
            if (velocidad == 1) {
                direccion = 2;
            } else {
                velocidad--;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && direccion == 1) {
            direccion = 3;
            velocidad = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direccion == 1) {
            direccion = 4;
            velocidad = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && direccion == 2) {
            velocidad++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP && direccion == 2) {
            if (velocidad == 1) {
                direccion = 1;
            } else {
                velocidad--;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && direccion == 2) {
            direccion = 3;
            velocidad = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direccion == 2) {
            direccion = 4;
            velocidad = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && direccion == 3) {
            velocidad++;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direccion == 3) {
            if (velocidad == 1) {
                direccion = 4;
            } else {
                velocidad--;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP && direccion == 3) {
            direccion = 1;
            velocidad = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && direccion == 3) {
            direccion = 2;
            velocidad = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direccion == 4) {
            velocidad++;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && direccion == 4) {
            if (velocidad == 1) {
                direccion = 3;
            } else {
                velocidad--;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP && direccion == 4) {
            direccion = 1;
            velocidad = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && direccion == 4) {
            direccion = 2;
            velocidad = 1;
        }

    }

    /**
     * Metodo <I>keyTyped</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una tecla que
     * no es de accion.
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
     * En este metodo se dibuja la imagen del perro con la posicion actualizada, 
     * tambien te despliega una advertencia al ser cargada una imagen.
     * Cuando existe un choque del objeto con la clase se cambia de imagen.
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        if (dumbo != null) {
            //Dibuja la imagen en la posicion actualizada
            if (choco) {
                g.drawImage(monito.getImagenI(), dumbo.getPosX(), dumbo.getPosY(), this);
                counter++;
                if(counter == 30){
                    choco = false;
                    counter = 0;
                }
            } else {
                g.drawImage(dumbo.getImagenI(), dumbo.getPosX(), dumbo.getPosY(), this);
            }

        } else {
            //Da un mensaje mientras se carga el dibujo    
            g.drawString("No se cargo la imagen..", 20, 20);
        }
    }
}
