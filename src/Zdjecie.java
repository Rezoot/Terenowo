import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random; /**
 * tworzy i losuje zdjecie
 */
public class Zdjecie extends JFrame {
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

    /**
     * losuje gre
     * @return zwraca wylosowana gre
     */
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

    /**
     * ile jest plansz
     * @return zwraca ilosc plansz
     */
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
