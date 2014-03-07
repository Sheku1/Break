/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreak;

/**
 * Clase Malo
 *
 * @author David Martinez A01191485
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
     * @param posX es la <code>posiscion en x</code> del objeto bomb.
     * @param posY es el <code>posiscion en y</code> del objeto bomb.
     */
    public Malo(int posX, int posY) {
        super(posX, posY);
        Image bomb0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota0.gif"));
        Image bomb1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota1.gif"));
        Image bomb2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Pelota2.gif"));


        //Se crea la animación
        animo = new Animacion();
        animo.sumaCuadro(bomb0, 100);
        animo.sumaCuadro(bomb1, 100);
        animo.sumaCuadro(bomb2, 100);
        
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
