import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


abstract class Okno extends JFrame{
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

/**
 * tworzy odliczanie
 */
class Czas{
    private int min,sek;
    private String m,s,tek;
    private final Gra gra;
    /**
     * warunek czy gra dalej jest grana
     */
    boolean apk=true;

    /**
     *
     * @param t czas poczatkowy
     * @param tekst Jlabel gdzie zostanie wpisany czas
     * @param Gra ktora gra z kolejnosci
     */
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
    private void zamknij()
    {
        //apk=false;

        new Wynik(gra.t, gra.odliczanie.getText(),gra.gra,gra.mapka.longp,gra.mapka.latp,gra.X,gra.Y,gra.wynik,gra.mapka.klikniete,gra.zagrane);
        gra.pulpit.dispose();
        gra.mapka.pulpit.dispose();
    }
}

/**
 * tworzy i losuje zdjecie
 */
class Zdjecie extends JFrame {
    /**
     *
     */
    ImageIcon zdjecie;
    /**
     * geo lokacja zdjecia
     */
    double x, y;
    /**
     * wylosowana liczba
     */
    int wyl;
    private final int[] zagrane;

    /**
     *
     * @param Zagrane przechowuje ktore zdjecia zostaly juz zagrane
     */
    Zdjecie(int[] Zagrane) {
        zagrane=Zagrane;
        wyl = losuj();
        try {
            String wylosowane = String.valueOf(wyl);

            String filename = "plansza/" + wylosowane + ".jpg";
            zdjecie = new ImageIcon(filename);

            if(zdjecie.getIconHeight()>2100){
                Image image = zdjecie.getImage();
                zdjecie = new ImageIcon(image.getScaledInstance(zdjecie.getIconWidth() / 2, zdjecie.getIconHeight() / 2, Image.SCALE_FAST));
            }


            try (BufferedReader br = new BufferedReader(new FileReader("lokalizacja"))) {
                String linia;
                while ((linia = br.readLine()) != null) {

                    String a = "",c = "",b = "";
                    int tryb = 0;

                    for (int i = 0; i < linia.length(); i++) {

                        if (linia.charAt(i) == ',') {


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




                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int losuj() {


        int dolnyZakres = 1;
        int gornyZakres = ilosc_plikow();
        int wylosowanaLiczba = 0;
        // Tworzymy obiekt klasy Random

        Random losowanie = new Random();
        boolean warunek = true;
        boolean warunek2 = true;
        // Losujemy liczbę z podanego przedziału
        while(warunek){

        wylosowanaLiczba = losowanie.nextInt(gornyZakres - dolnyZakres + 1) + dolnyZakres;

        for(int i : zagrane)
        {

            if (i == wylosowanaLiczba) {
                warunek2 = false;

                break;
            }
        }

        if (warunek2) {warunek=false;}
        warunek2=true;

        }
        return wylosowanaLiczba;
    }


    private int ilosc_plikow() {
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







