/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreak;

/**
 * Clase Bueno
 *
 * @author David Martínez
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Bueno extends Base {

    private static final String PAUSADO = "PAUSADO";
    private static final String DESAPARECE = "DESAPARECE";

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto elefante.
     * @param posY es el <code>posiscion en y</code> del objeto elefante.
     */
    public Bueno(int posX, int posY) {
        super(posX, posY);
        Image Canasta0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Canasta0.gif"));
        Image Canasta1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Canasta1.gif"));
        Image Canasta2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Canasta2.gif"));
        Image Canasta3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Canasta3.gif"));
        Image Canasta4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Canasta4.gif"));
        Image Canasta5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/Canasta5.gif"));

        animo = new Animacion();
        animo.sumaCuadro(Canasta0, 100);
        animo.sumaCuadro(Canasta1, 100);
        animo.sumaCuadro(Canasta2, 100);
        animo.sumaCuadro(Canasta3, 100);
        animo.sumaCuadro(Canasta4, 100);
        animo.sumaCuadro(Canasta5, 100);
    }

    public static String getPAUSADO() {
        return PAUSADO;
    }

    public static String getDESAPARECE() {
        return DESAPARECE;
    }
}
