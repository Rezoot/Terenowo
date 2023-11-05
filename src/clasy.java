import javax.swing.*;
import java.awt.*;



abstract class Okno extends JFrame{
    ImageIcon map;
    int szerokoscokna=1280,wysokoscokna=1024;
    Okno(){
    setName("terenowo");
    setTitle("terenowo");
    setSize(szerokoscokna,wysokoscokna );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(null);

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

                    int x,y;
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



class Menu extends Okno{

}


