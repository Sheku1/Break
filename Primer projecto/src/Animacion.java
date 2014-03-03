/**
 * Una clase para ejemplificar la animacion de una imagen
 *
 * Animacion <code>Applet</code> application
 *
 * @author Antonio Mejorado
 * @version 1.00 2008/6/10
 */

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.net.URL;

public class Animacion extends Applet implements Runnable
{
	private static final long serialVersionUID = 1L;
	// Se declaran las variables.
	private int x_pos;			// Posicion x del elefante
	private int y_pos;			// Posicion y del elefante
	private Image elefante;		// Imagen del elefante
        private Image dbImage; // Imagen a proyectar.
        private Graphics dbg; // Objeto grafico.

	/** 
	 * Metodo <I>init</I> sobrescrito de la clase <code>Applet</code>.<P>
	 * En este metodo se inizializan las variables o se crean los objetos 
	 * y se ejecuta una sola vez cuando inicia el <code>Applet</code>.
	 */
	public void init() {
		x_pos=(int) (Math.random() *(getWidth() / 9));    // posicion en x es un cuarto del applet;
		y_pos=(int) (Math.random() *(getHeight() / 9));    // posicion en y es un cuarto del applet
		URL eURL = this.getClass().getResource("imagenes/elefante.gif");
		elefante = Toolkit.getDefaultToolkit().getImage(eURL);
		setBackground (Color.yellow);
	}

	/** 
	 * Metodo <I>start</I> sobrescrito de la clase <code>Applet</code>.<P>
	 * En este metodo se crea e inicializa el hilo
	 * para la animacion este metodo es llamado despues del init o 
	 * cuando el usuario visita otra pagina y luego regresa a la pagina
	 * en donde esta este <code>Applet</code>
	 * 
	 */
	public void start () {
		// Declaras un hilo
		Thread th = new Thread(this);
		// Empieza el hilo
		th.start();
	}

	/**
	 * Metodo <I>stop</I> sobrescrito de la clase <code>Applet</code>.<P>
	 * En este metodo se pueden tomar acciones para cuando se termina
	 * de usar el <code>Applet</code>. Usualmente cuando el usuario sale de la pagina
	 * en donde esta este <code>Applet</code>.
	 */
	public void stop() {

	}

	/**
	 * Metodo <I>destroy</I> sobrescrito de la clase <code>Applet</code>.<P>
	 * En este metodo se toman las acciones necesarias para cuando
	 * el <code>Applet</code> ya no va a ser usado. Usualmente cuando el usuario
	 * cierra el navegador.
	 */
	public void destroy() {

	}

	/** 
	 * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
	 * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se incrementa
	 * la posicion en x, se repinta el <code>Applet</code> y luego manda a dormir
	 * el hilo.
	 * 
	 */
	public void run () {
		while (true)
		{
			// Actualizas la coordenada en x
			x_pos ++;

			// Se actualiza el <code>Applet</code> repintando el contenido
			repaint();

			try	{
				// El thread se duerme
				Thread.sleep(20);
			}
			catch (InterruptedException ex)	{
				System.out.println("Error en " + ex.toString());
			}
		}
	}

	/**
	 * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>,
	 * heredado de la clase Container.<P>
	 * En este metodo se dibuja la imagen con la posicion actualizada,
	 * ademas que cuando la imagen es cargada te despliega una advertencia.
	 * @param g es el <code>objeto grafico</code> usado para dibujar.
	 */
	public void paint(Graphics g) {
		if (elefante != null) {
			//Dibuja la imagen en la posicion actualizada
			g.drawImage(elefante, x_pos, y_pos, this);

		} else {
			//Da un mensaje mientras se carga el dibujo	
			g.drawString("Estoy cargando..",10,10);
		}

	}

}