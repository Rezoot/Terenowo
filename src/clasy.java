import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


abstract class Okno extends JFrame{
    JFrame pulpit;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int szerokoscokna=screenSize.width,wysokoscokna=screenSize.height-45;
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


class Czas{
    int min,sek;
    JFrame pulpit,pulpit2;
    String m,s,tek;
    boolean apk=true;
    Czas(int t, JLabel tekst, JFrame Pulpit,JFrame Pulpit2) {
        pulpit=Pulpit;
        pulpit2=Pulpit2;
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
        pulpit2.dispose();
    }
}

class Zdjecie extends JFrame{
    ImageIcon zdjecie;
    double x,y;
    String a="",b="",c="";
    int tryb=0;
    String wylosowane;
    Zdjecie(){
        try {
            wylosowane=losuj();
            String filename = "plansza/"+wylosowane+".jpg";
            zdjecie = new ImageIcon(filename);
            Image image  = zdjecie.getImage();
            zdjecie = new ImageIcon(image.getScaledInstance(zdjecie.getIconWidth()/2,zdjecie.getIconHeight()/2,Image.SCALE_FAST));
            try (BufferedReader br = new BufferedReader(new FileReader("lokalizacja"))) {
                String linia;

                while ((linia = br.readLine()) != null) {
                    System.out.println(linia);

                    for(int i = 0; i < linia.length(); i++){

                        if(linia.charAt(i)==';')
                        {
                            /*
                            if (tryb==0 && c.equals(wylosowane))
                            {
                                }
                            */
                            tryb++;
                        }
                        else if (tryb==1)
                        {
                            a+=linia.charAt(i);
                        }
                        else if (tryb==2)
                        {
                            b+=linia.charAt(i);
                        }
                        else if (tryb==0)
                        {
                            c+=linia.charAt(i);

                        }

                    }
                    y=Double.parseDouble(a);
                    x=Double.parseDouble(b);
                    System.out.println(x);
                    System.out.println(y);
                    c="";
                    a="";
                    b="";
                    tryb=0;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(Exception e){
            System.out.println("nie dziala");
        }
    }
String losuj(){
        int liczba=1;

      return String.valueOf(liczba);
}

}






