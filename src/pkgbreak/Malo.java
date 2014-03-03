/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreak;

/**
 * Clase Malo
 *
 * @author David Martinez
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Malo extends Base {

    private static int conteo;
    private int speed;

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto Pelota.
     * @param posY es el <code>posiscion en y</code> del objeto Pelota.
     */
    public Malo(int posX, int posY) {
        super(posX, posY);
        Image Pelota0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota0.gif"));
        Image Pelota1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota1.gif"));
        Image Pelota2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota2.gif"));
        Image Pelota3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota3.gif"));
        Image Pelota4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota4.gif"));
        Image Pelota5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota5.gif"));

        //Se crea la animación
        animo = new Animacion();
        animo.sumaCuadro(Pelota0, 100);
        animo.sumaCuadro(Pelota1, 100);
        animo.sumaCuadro(Pelota2, 100);
        animo.sumaCuadro(Pelota3, 100);
        animo.sumaCuadro(Pelota4, 100);
        animo.sumaCuadro(Pelota5, 100);
    }

    public static int getConteo() {
        return conteo;
    }

    public static void setConteo(int cont) {
        conteo = cont;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int vel) {
        speed = vel;
    }
}
