package holamundo;
/**
 *	Applicación JFrameHolaMundo
 *
 *	Tutorial ¿Qué es un JFrame? - Jugando con JAVA
 *
 *	@autor Jugando con JAVA
 *	@version 1.0 13/08/2010
 */
 
 import javax.swing.JFrame;
 import java.awt.Graphics;
 import java.awt.Font;
 import java.awt.Color;
 
 public class JFrameHolaMundo extends JFrame{
 	
 	public JFrameHolaMundo(){
 		setTitle("JFrame HolaMundo");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		setSize(200, 200);
 	}
 	 	
 	public void paint(Graphics g){
 		g.setColor(Color.WHITE);
 		g.fillRect(0, 0, getWidth(), getHeight());
 		g.setColor(Color.BLACK);
 		g.setFont(new Font("Serif", Font.BOLD, 18));
 		g.drawString("HOLA MUNDO", this.getSize().width / 2 - 60, this.getSize().height / 2 + 9);
 	}
 	
 	public static void main(String[] args){
 		JFrameHolaMundo holaMundo = new JFrameHolaMundo();
 		holaMundo.setVisible(true);
 	}
 }