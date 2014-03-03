import javax.swing.ImageIcon;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;

/**
	El applet AppletAnimacion muestra una animación en pantalla.
*/
public class AppletAnimacion extends Applet implements Runnable{

	//Objeto de la clase Animacion para el manejo de la animación
	private Animacion anim;
	
	//Variables de control de tiempo de la animación
	private long tiempoActual;
	private long tiempoInicial;
	int posX, posY;
	
	/**
		El método init() crea la animación que se mostrará en pantalla.
	*/
	public void init(){
		
		//Se cargan las imágenes(cuadros) para la animación
		Image raton1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141331.jpg"));
		Image raton2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141332(0).jpg"));
                Image raton3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141332.jpg"));
                Image raton4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141333(0).jpg"));
                Image raton5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141333.jpg"));
                Image raton6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141334(0).jpg"));
                Image raton7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141334.jpg"));
                Image raton8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141335(0).jpg"));
                Image raton9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141335.jpg"));
                Image raton10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141336(0).jpg"));
                Image raton11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141336.jpg"));                Image raton12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141337(0).jpg"));
                Image raton13 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_141337.jpg"));
                Image raton14 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142037.jpg"));
                Image raton15 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142038(0).jpg"));
                Image raton16 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142038.jpg"));
                Image raton17 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142039.jpg"));
                Image raton18 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142040(0).jpg"));
                Image raton19 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142040.jpg"));
                Image raton20 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142041(0).jpg"));
                Image raton21 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142041.jpg"));
                Image raton22 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142042(0).jpg"));
                Image raton23 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142042.jpg"));
                Image raton24 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142043.jpg"));
                Image raton25 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142046.jpg"));
                Image raton26 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/20140129_142055.jpg"));
               
                
                
                
                
		
		
		//Se crea la animación
		anim = new Animacion();
		anim.sumaCuadro(raton1, 100);
		anim.sumaCuadro(raton2, 100);
		anim.sumaCuadro(raton3, 100);
		anim.sumaCuadro(raton4, 100);
		anim.sumaCuadro(raton5, 100);
		anim.sumaCuadro(raton6, 100);
		anim.sumaCuadro(raton7, 100);
		anim.sumaCuadro(raton8, 100);
		anim.sumaCuadro(raton9, 100);
		anim.sumaCuadro(raton10, 100);
		anim.sumaCuadro(raton11, 100);
		anim.sumaCuadro(raton12, 100);
                anim.sumaCuadro(raton13, 100);
                anim.sumaCuadro(raton14, 100);
                anim.sumaCuadro(raton15, 100);
                anim.sumaCuadro(raton16, 100);
                anim.sumaCuadro(raton17, 100);
                anim.sumaCuadro(raton18, 100);
                anim.sumaCuadro(raton19, 100);
                anim.sumaCuadro(raton20, 100);
                anim.sumaCuadro(raton21, 100);
                anim.sumaCuadro(raton22, 100);
                anim.sumaCuadro(raton23, 100);
                anim.sumaCuadro(raton24, 100);
                anim.sumaCuadro(raton25, 100);
                anim.sumaCuadro(raton26, 100);
                
                
		
		//Pinta el fondo del Applet de color amarillo		
		setBackground(Color.yellow);
                this.setSize(1000,1000);
	}
	
	//El método start() inicializa el thread que utiliza el Applet
	public void start(){
		
		//Crea el thread
		Thread hilo = new Thread(this);
	    //Inicializa el thread
	    hilo.start();
	}
	
	/**
	 * Metodo stop sobrescrito de la clase Applet.
	 * En este metodo se pueden tomar acciones para cuando se termina
	 * de usar el Applet. Usualmente cuando el usuario sale de la pagina
	 * en donde esta este Applet.
	 */
	public void stop() {

	}

	/**
	 * Metodo destroy sobrescrito de la clase Applet.
	 * En este metodo se toman las acciones necesarias para cuando
	 * el Applet ya no va a ser usado. Usualmente cuando el usuario
	 * cierra el navegador.
	 */
	public void destroy() {

	}

	
	/**
		El método run() manda a llamar los métodos atualiza() y repaint(),
		nesecarios para actualizar y mostrar la animación en pantalla.
	*/
	 public void run() {
	 	
	 	//Guarda el tiempo actual del sistema
        tiempoActual = System.currentTimeMillis();

		//Ciclo principal del Applet. Actualiza y despliega en pantalla la animación hasta que el Applet sea cerrado
        while (true) {
        	
        	//Actualiza la animación
	         actualiza();
	         //Manda a llamar al método paint() para mostrar en pantalla la animación
	         repaint();
            
            //Hace una pausa de 200 milisegundos
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException ex) { }
        }

   	 }
    
    /**
   	 El método actualiza() actualiza la animación
    */
    public void actualiza() {
   	
   		 //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
         long tiempoTranscurrido =
             System.currentTimeMillis() - tiempoActual;
            
         //Guarda el tiempo actual
       	 tiempoActual += tiempoTranscurrido;

         //Actualiza la animación en base al tiempo transcurrido
         anim.actualiza(tiempoTranscurrido);
    }
   
    /**
   	 El método paint() muestra en pantalla la animación
    */
    public void paint(Graphics g) {
   		 posX =0;  //this.getWidth()/2 - anim.getImagen().getWidth(null)/2;
         posY =0;//	this.getHeight()/2 - anim.getImagen().getHeight(null)/2;
         // Muestra en pantalla el cuadro actual de la animación
         if (anim != null) {
        	 g.drawImage(anim.getImagen(), posX, posY, 1000,1000, this);
         }

    }
}