import javax.swing.*;
import java.awt.*;



abstract class Okno extends JFrame{
    ImageIcon map;
    int szerokoscokna=1280,wysokoscokna=1024;
    JButton zamknij = new JButton(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            int[] xPoints = {0, 40, 40, 0}; // Współrzędne x dla trójkąta
            int[] yPoints = {15, 15, 25, 25 }; // Współrzędne y dla trójkąta
            g.fillPolygon(xPoints, yPoints, 4);

            int[] xPoints2 = {35, 35, 65}; // Współrzędne x dla trójkąta
            int[] yPoints2 = {0, 40,20 }; // Współrzędne y dla trójkąta
            g.fillPolygon(xPoints2, yPoints2, 3);
        }
    };


    Okno(){

    setName("terenowo");
    setTitle("terenowo");
    setUndecorated(true);

    setSize(szerokoscokna,wysokoscokna );
    setLayout(null);


    zamknij.setPreferredSize(new Dimension(65, 40));
    zamknij.setOpaque(false);
    zamknij.setContentAreaFilled(false);
    zamknij.setBorderPainted(false);


    map = new ImageIcon("map.png");


    }





}


class Czas{
    int min,sek;
    String m,s,tek;

    Czas(int t, JLabel tekst) {
        min=t;
        sek=0;
        new Thread() {
            @Override
            public void run() {
                try {

                    //int x,y;
                    while(min>0 || sek>0) {
                        m = String.valueOf(min);s = String.valueOf(sek);
                        if (sek<10){s="0"+s;}
                        tek = m+":"+s;
                        tekst.setText(tek);
                        if (sek==0 && min!=0)
                        {
                        sek=60;
                        min-=1;
                        }
                        sek-=1;
                        Thread.sleep(1000);



                    }
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        }.start();
    }
}

class Zdjecie extends JFrame{
    ImageIcon zdjecie,map;

    Zdjecie(){
        try {
            zdjecie = new ImageIcon("1.jpg");
            Image image  = zdjecie.getImage();
            zdjecie = new ImageIcon(image.getScaledInstance(zdjecie.getIconWidth()/2,zdjecie.getIconHeight()/2,Image.SCALE_FAST));

        }
        catch(Exception e){
            System.out.println("nie dziala");
        }
    }
}






