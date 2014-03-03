
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
     * @param posX es la <code>posiscion en x</code> del objeto raton.
     * @param posY es el <code>posiscion en y</code> del objeto raton.
     */
    public Malo(int posX, int posY) {
        super(posX, posY);
        Image raton1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_000.gif"));
        Image raton2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_001.gif"));
        Image raton3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_002.gif"));
        Image raton4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_003.gif"));
        Image raton5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_004.gif"));
        Image raton6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_005.gif"));
        Image raton7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_006.gif"));
        Image raton8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_007.gif"));
        Image raton9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_008.gif"));
        Image raton10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_009.gif"));
        Image raton11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_010.gif"));
        Image raton12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Images/frame_011.gif"));
        //Se crea la animación
        animo = new Animacion();
        animo.sumaCuadro(raton1, 100);
        animo.sumaCuadro(raton2, 100);
        animo.sumaCuadro(raton3, 100);
        animo.sumaCuadro(raton4, 100);
        animo.sumaCuadro(raton5, 100);
        animo.sumaCuadro(raton6, 100);
        animo.sumaCuadro(raton7, 100);
        animo.sumaCuadro(raton8, 100);
        animo.sumaCuadro(raton9, 100);
        animo.sumaCuadro(raton10, 100);
        animo.sumaCuadro(raton11, 100);
        animo.sumaCuadro(raton12, 100);
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
