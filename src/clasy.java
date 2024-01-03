import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


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
    String m,s,tek;
    Gra gra;
    boolean apk=true;
    Czas(int t, JLabel tekst,Gra Gra) {
        gra=Gra;
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
                 if(apk){zamknij();}

             }catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }

         }).start();
    }
    void zamknij()
    {
        //apk=false;

        new Wynik(gra.t, gra.odliczanie.getText(),gra.gra,gra.mapka.longp,gra.mapka.latp,gra.X,gra.Y,gra.wynik,gra.mapka.klikniete);
        gra.pulpit.dispose();
        gra.mapka.pulpit.dispose();
    }
}

class Zdjecie extends JFrame {
    ImageIcon zdjecie;
    double x, y;
    String a = "", b = "", c = "";
    int tryb = 0;
    String wylosowane;

    Zdjecie() {

        try {
            wylosowane = losuj();

            String filename = "plansza/" + wylosowane + ".jpg";
            zdjecie = new ImageIcon(filename);
            Image image = zdjecie.getImage();
            zdjecie = new ImageIcon(image.getScaledInstance(zdjecie.getIconWidth() / 2, zdjecie.getIconHeight() / 2, Image.SCALE_FAST));

            try (BufferedReader br = new BufferedReader(new FileReader("lokalizacja"))) {
                String linia;

                while ((linia = br.readLine()) != null) {


                    for (int i = 0; i < linia.length(); i++) {

                        if (linia.charAt(i) == ';') {

                            if (tryb == 0 && !(c.equals(wylosowane))) {

                                break;

                            }

                            tryb++;
                        } else if (tryb == 1) {
                            a += linia.charAt(i);
                        } else if (tryb == 2) {
                            b += linia.charAt(i);
                        } else if (tryb == 0) {
                            c += linia.charAt(i);

                        }

                    }
                    if (!Objects.equals(a, "")) {
                        y = Double.parseDouble(a);
                        x = Double.parseDouble(b);

                    }

                    c = "";
                    a = "";
                    b = "";
                    tryb = 0;


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("nie dziala");
        }
    }

    String losuj() {


        int dolnyZakres = 1;
        int gornyZakres = ilosc_plikow();

        // Tworzymy obiekt klasy Random
        Random losowanie = new Random();

        // Losujemy liczbę z podanego przedziału
        int wylosowanaLiczba = losowanie.nextInt(gornyZakres - dolnyZakres + 1) + dolnyZakres;

        return String.valueOf(wylosowanaLiczba);
    }


    int ilosc_plikow() {
        int ile=0;
        File folder = new File("plansza");
        if (folder.isDirectory())
        {
            File[] listaPlikow = folder.listFiles();
            if (listaPlikow != null)
            {
                for (File ignored : listaPlikow)
                {
                  ile++;
                }
            }
            else
            {
                System.out.println("Folder jest pusty.");
            }
        }
        else
        {
            System.out.println("Podana ścieżka nie wskazuje na folder.");
        }
        return ile;
    }
}







