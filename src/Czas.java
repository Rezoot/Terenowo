import javax.swing.*;
/**
 * tworzy odliczanie
 */
public class Czas{
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

    /**
     * zamyka aplikacje
     */
    private void zamknij()
    {
        //apk=false;

        new Wynik(gra.t, gra.odliczanie.getText(),gra.gra,gra.mapka.longp,gra.mapka.latp,gra.X,gra.Y,gra.wynik,gra.mapka.klikniete,gra.zagrane);
        gra.pulpit.dispose();
        gra.mapka.pulpit.dispose();
    }
}
