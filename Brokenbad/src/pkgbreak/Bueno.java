/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreak;

/**
 * Clase Bueno
 *
 * @author David Martínez A01191485
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
        Image tank0 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank0.gif"));
        Image tank1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank1.gif"));
        Image tank2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank2.gif"));
        Image tank3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank3.gif"));
        Image tank4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank4.gif"));
        Image tank5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank5.gif"));
        Image tank6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank6.gif"));
        Image tank7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank7.gif"));
        Image tank8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank8.gif"));
        Image tank9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank9.gif"));
        Image tank10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank10.gif"));
        Image tank11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank11.gif"));
        Image tank12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank12.gif"));
        Image tank13 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank13.gif"));
        Image tank14 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank14.gif"));
        Image tank15 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank15.gif"));
        Image tank16 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank16.gif"));
        Image tank17 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank17.gif"));
        Image tank18 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank18.gif"));
        Image tank19 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank19.gif"));
        Image tank20 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank20.gif"));
        Image tank21 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank21.gif"));
        Image tank22 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank22.gif"));
        Image tank23 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank23.gif"));
        Image tank24 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank24.gif"));
        Image tank25 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank25.gif"));
        Image tank26 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank26.gif"));
        Image tank27 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank27.gif"));
        Image tank28 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank28.gif"));
        Image tank29 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank29.gif"));
        Image tank30 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank30.gif"));
        Image tank31 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/tank31.gif"));

        animo = new Animacion();
        animo.sumaCuadro(tank0, 100);
        animo.sumaCuadro(tank1, 100);
        animo.sumaCuadro(tank2, 100);
        animo.sumaCuadro(tank3, 100);
        animo.sumaCuadro(tank4, 100);
        animo.sumaCuadro(tank5, 100);
        animo.sumaCuadro(tank6, 100);
        animo.sumaCuadro(tank7, 100);
        animo.sumaCuadro(tank8, 100);
        animo.sumaCuadro(tank9, 100);
        animo.sumaCuadro(tank10, 100);
        animo.sumaCuadro(tank11, 100);
        animo.sumaCuadro(tank12, 100);
        animo.sumaCuadro(tank13, 100);
        animo.sumaCuadro(tank14, 100);
        animo.sumaCuadro(tank15, 100);
        animo.sumaCuadro(tank16, 100);
        animo.sumaCuadro(tank17, 100);
        animo.sumaCuadro(tank18, 100);
        animo.sumaCuadro(tank19, 100);
        animo.sumaCuadro(tank20, 100);
        animo.sumaCuadro(tank21, 100);
        animo.sumaCuadro(tank22, 100);
        animo.sumaCuadro(tank23, 100);
        animo.sumaCuadro(tank24, 100);
        animo.sumaCuadro(tank25, 100);
        animo.sumaCuadro(tank26, 100);
        animo.sumaCuadro(tank27, 100);
        animo.sumaCuadro(tank28, 100);
        animo.sumaCuadro(tank29, 100);
        animo.sumaCuadro(tank30, 100);
        animo.sumaCuadro(tank31, 100);

    }

    public static String getPAUSADO() {
        return PAUSADO;
    }

    public static String getDESAPARECE() {
        return DESAPARECE;
    }
}
