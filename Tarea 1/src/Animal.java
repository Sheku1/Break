import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class Animal {
        private int posX;    //posicion en x.       
	private int posY;	//posicion en y.
	private ImageIcon icono;    //icono.
        // declaraste las variables que heredara la clase elefante.
        	public Animal(int posX, int posY ,Image image) {
		this.posX=posX;
		this.posY=posY;
		icono = new ImageIcon(image);
                // creas el elefante por medio de este metodo.
                }
                
        // metodo utilizado para para cambiar posiciond de la x.
                public void setPosX(int posX) {
		this.posX = posX;
                }
        // metodo que regresa la posicion de la x del objeto.
                public int getPosX() {
		return posX;
                }
        // metodo utilizado para cambiar la posicion de la y
                public void setPosY(int posY) {
		this.posY = posY;
                }
        // metodo que regresa la posicion y del objeto.
                public int getPosY() {
		return posY;
                }
        // metodo que cambia el icono del objeto
                public void setImageIcon(ImageIcon icono) {
		this.icono = icono;
                }
        // metodo que regresa el icono del objeto.
                public ImageIcon getImageIcon() {
		return icono;
                }
        // metodo que regresa el ancho del icono
                public int getAncho() {
		return icono.getIconWidth();
                }        
        // meotod que regresa el alto del icono
                public int getAlto() {
		return icono.getIconHeight();
                }
        // metodo que regresa la imagen del icono
                public Image getImagenI() {
		return icono.getImage();
                }
        // metodo que regresa el rectangulo del objeto de una clase ( elefante ) que es el perimetro
                public Rectangle getPerimetro(){
		return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
                }
        // checa si el objeto intersecta a otro.. regresa un valor bool si lo intersecta es true.
                public boolean intersecta(Animal obj){
		return getPerimetro().intersects(obj.getPerimetro());
                }
}      