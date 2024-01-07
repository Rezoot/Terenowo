import javax.swing.*;
import java.awt.*;

/**
 * abstract clasa dla nowych okien
 */
public abstract class Okno extends JFrame{
    /**
     *
     */
    JFrame pulpit;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     *
     */
    int szerokoscokna=screenSize.width,wysokoscokna=screenSize.height-45;
    /**
     * generacja przycisku zamykajacego
     */
    JButton zamknij = new JButton(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            int[] xPoints = {0, 15, 45, 30}; // Współrzędne x dla trójkąta
            int[] yPoints = {30, 30, 0, 0 }; // Współrzędne y dla trójkąta
            g.fillPolygon(xPoints, yPoints, 4);

            int[] xPoints2 = {30, 45, 15,0}; // Współrzędne x dla trójkąta
            int[] yPoints2 = {30, 30,0,0 }; // Współrzędne y dla trójkąta
            g.fillPolygon(xPoints2, yPoints2, 4);
        }
    };

    /**
     * tworzy okno
     */
    Okno(){
    if(szerokoscokna>1280){szerokoscokna=1280;}
    if(wysokoscokna>1080){wysokoscokna=1080;}
    pulpit = new JFrame();
    pulpit.setUndecorated(true);

    pulpit.setSize(szerokoscokna,wysokoscokna );



    zamknij.setPreferredSize(new Dimension(55, 30));
    zamknij.setOpaque(false);
    zamknij.setContentAreaFilled(false);
    zamknij.setBorderPainted(false);





    }





}







