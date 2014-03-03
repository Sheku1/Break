
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
        Image dumbo1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame0.gif"));
        Image dumbo2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame1.gif"));
        Image dumbo3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame2.gif"));
        Image dumbo4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame3.gif"));
        Image dumbo5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame4.gif"));
        Image dumbo6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame5.gif"));
        animo = new Animacion();
        animo.sumaCuadro(dumbo1, 100);
        animo.sumaCuadro(dumbo2, 100);
        animo.sumaCuadro(dumbo3, 100);
        animo.sumaCuadro(dumbo4, 100);
        animo.sumaCuadro(dumbo5, 100);
        animo.sumaCuadro(dumbo6, 100);
    }

    public static String getPAUSADO() {
        return PAUSADO;
    }

    public static String getDESAPARECE() {
        return DESAPARECE;
    }
}
