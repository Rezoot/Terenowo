import javax.swing.*;
import java.awt.*;


abstract class Okno extends JFrame{
    JFrame pulpit;

    int szerokoscokna=1280,wysokoscokna=1024;
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


    Okno(){

    pulpit = new JFrame();
    pulpit.setUndecorated(true);

    pulpit.setSize(szerokoscokna,wysokoscokna );



    zamknij.setPreferredSize(new Dimension(55, 30));
    zamknij.setOpaque(false);
    zamknij.setContentAreaFilled(false);
    zamknij.setBorderPainted(false);





    }





}


class Czas{
    int min,sek;
    JFrame pulpit;
    String m,s,tek;
    boolean apk=true;
    Czas(int t, JLabel tekst, JFrame Pulpit) {
        pulpit=Pulpit;
        min = t/60;
        if (t==60){sek=0;}
        else{sek=30;}

         new Thread(() -> {
             try {

                 //int x,y;
                 while((min>0 || sek>0) && apk) {
                     m = String.valueOf(min);s = String.valueOf(sek);
                     if (sek<10){s= "0" + s;}
                     tek = m+":"+s;
                     tekst.setText(tek);
                     if (sek<=0 && min!=0)
                     {
                     sek=60;
                     min-=1;
                     }
                     sek-=1;
                     Thread.sleep(1000);


                 }
                 zamknij();
             }catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

         }).start();
    }
    void zamknij()
    {
        apk=false;
        pulpit.dispose();
    }
}

class Zdjecie extends JFrame{
    ImageIcon zdjecie;

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






