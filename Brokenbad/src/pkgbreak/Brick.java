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
import java.awt.Image;
import java.awt.Toolkit;

public class Brick extends Base {

    private static final String PAUSADO = "PAUSADO";
    private static final String DESAPARECE = "DESAPARECE";

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto elefante.
     * @param posY es el <code>posiscion en y</code> del objeto elefante.
     */
    public Brick(int posX, int posY) {
        super(posX, posY);
        Image brick0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/recto.jpg"));

        animo = new Animacion();
        animo.sumaCuadro(brick0, 100);

    }

    public static String getPAUSADO() {
        return PAUSADO;
    }

    public static String getDESAPARECE() {
        return DESAPARECE;
    }
}
