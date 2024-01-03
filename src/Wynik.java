import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Wynik extends Okno{
    String gra;
    int punkty;
    int plus;
    Map mapka;
    int tekst=50,mapa=400;
    String czas;
    double x1,x2,y1,y2;
    int tryb;
    int[] tablica = new int[3];
    Wynik(int Tryb, String Czas,String Gra,double X1,double Y1,double X2,double Y2,int Punkty) {
        tryb=Tryb;
        punkty=Punkty;
        x1=X1;x2=X2;y1=Y1;y2=Y2;
        gra=Gra;
        czas=Czas;
        mapka = new Map(x1,x2,y1,y2);

        pulpit.setLayout(new BoxLayout(pulpit.getContentPane(),BoxLayout.Y_AXIS));

        gora();
        gora2();

        mappa();

        dol();

        dalej();

        JPanel gora = new JPanel();
        pulpit.add(gora);
        gora.setOpaque(false);
        gora.setPreferredSize(new Dimension(szerokoscokna,wysokoscokna-mapa-5*tekst));


        pulpit.setVisible(true);

    }
    void gora() {
        JPanel gora = new JPanel();
        pulpit.add(gora);
        gora.setLayout(new OverlayLayout(gora));
        gora.setOpaque(false);
        gora.setPreferredSize(new Dimension(szerokoscokna,tekst));

        JPanel wynik = new JPanel();
        wynik.setOpaque(false);
        gora.add(wynik);


        JLabel time = new JLabel(czas);
        time.setFont(new Font("Arial", Font.PLAIN, 30));
        time.setOpaque(true);
        time.setBackground(Color.BLACK);
        time.setForeground(Color.WHITE);
        time.setBorder(new LineBorder(Color.BLACK, 2));
        wynik.add(time,BorderLayout.CENTER);


        JPanel plansza = new JPanel();
        plansza.setOpaque(false);
        plansza.setLayout(new BorderLayout());



        gora.add(plansza);
        plansza.add(zamknij,BorderLayout.EAST);

        zamknij.addActionListener(e -> pulpit.dispose());



    }
    void gora2() {

        JPanel gora = new JPanel();
        pulpit.add(gora);
        gora.setLayout(new OverlayLayout(gora));
        gora.setOpaque(false);
        gora.setBackground(Color.blue);
        gora.setPreferredSize(new Dimension(szerokoscokna,tekst));

        JPanel wynik = new JPanel();
        wynik.setOpaque(false);
        gora.add(wynik);


        tablica=punkty();
        plus=tablica[2];
        punkty+=plus;

        JLabel odliczanie = new JLabel("wynik: "+punkty);
        odliczanie.setFont(new Font("Arial", Font.PLAIN, 30));
        odliczanie.setOpaque(true);
        odliczanie.setBackground(Color.BLACK);
        odliczanie.setForeground(Color.WHITE);
        odliczanie.setBorder(new LineBorder(Color.BLACK, 2));
        wynik.add(odliczanie,BorderLayout.CENTER);


        JPanel plansza = new JPanel();
        plansza.setOpaque(false);
        plansza.setLayout(new BorderLayout());

        JLabel poziom = new JLabel("plansza: "+ gra +"/3");
        poziom.setFont(new Font("Arial", Font.PLAIN, 30));
        poziom.setOpaque(true);
        poziom.setBackground(Color.BLACK);
        poziom.setForeground(Color.WHITE);
        poziom.setBorder(new LineBorder(Color.BLACK, 2));

        gora.add(plansza);
        plansza.add(poziom,BorderLayout.EAST);


    }

    void mappa(){

        JPanel pan = new JPanel();
        pan.setPreferredSize(new Dimension(szerokoscokna, mapa));
        pan.setLayout(new GridBagLayout());

        JPanel srodek = new JPanel();
        srodek.setPreferredSize(new Dimension(600,300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.CENTER;

        pan.add(srodek,gbc);



        mapka.panel=srodek;
        mapka.wstaw();
        pan.setBackground(Color.PINK);
        pulpit.add(pan);


    }
    void dol(){

        JPanel dol = new JPanel();
        pulpit.add(dol);
        dol.setOpaque(false);
        dol.setBackground(Color.blue);
        dol.setPreferredSize(new Dimension(szerokoscokna,tekst));

        JPanel wynik = new JPanel();
        wynik.setOpaque(false);
        dol.add(wynik);

        JLabel uzyskane = new JLabel("wynik: "+plus);
        uzyskane.setFont(new Font("Arial", Font.PLAIN, 30));
        uzyskane.setOpaque(true);
        uzyskane.setBackground(Color.BLACK);
        uzyskane.setForeground(Color.WHITE);
        uzyskane.setBorder(new LineBorder(Color.BLACK, 2));
        wynik.add(uzyskane,BorderLayout.CENTER);

        JPanel dol2 = new JPanel();
        pulpit.add(dol2);
        dol2.setOpaque(false);
        dol2.setBackground(Color.blue);
        dol2.setPreferredSize(new Dimension(szerokoscokna,tekst));

        JPanel odl = new JPanel();
        wynik.setOpaque(false);
        dol2.add(odl);

        String napis;
        if(mapka.odleglosc>2500)
        {
             napis = "odleglosc: " + mapka.odleglosc/1000 + "km";

        }
        else
        {
             napis = "odleglosc: " + (int)mapka.odleglosc + "m";
        }

        JLabel km = new JLabel(napis);
        km.setFont(new Font("Arial", Font.PLAIN, 30));
        km.setOpaque(true);
        km.setBackground(Color.BLACK);
        km.setForeground(Color.WHITE);
        km.setBorder(new LineBorder(Color.BLACK, 2));
        odl.add(km,BorderLayout.CENTER);
    }

    void dalej(){
        JPanel gora = new JPanel();
        pulpit.add(gora);
        gora.setLayout(new OverlayLayout(gora));
        gora.setOpaque(false);
        gora.setPreferredSize(new Dimension(szerokoscokna,2*tekst));


        JPanel wynik = new JPanel();
        wynik.setOpaque(false);
        gora.add(wynik);

        if(plus!=0){
            JPanel dodatkowy =new JPanel();
            dodatkowy.setLayout(new BoxLayout(dodatkowy,BoxLayout.Y_AXIS));
            dodatkowy.setOpaque(false);
            wynik.add(dodatkowy);

            String tek = "+" + tablica[0];
            JLabel pun = new JLabel(tek);
            pun.setFont(new Font("Arial", Font.PLAIN, 30));
            dodatkowy.add(pun);

            tek = "+" + tablica[1];
            JLabel pun2 = new JLabel(tek);
            pun2.setFont(new Font("Arial", Font.PLAIN, 30));
            dodatkowy.add(pun2);

           new Thread(()->{

               try {
                   Thread.sleep(1000);
                   for(int i=255;i>=0;i--){
                      pun.setForeground(new Color(0,0,0,i));
                      pun2.setForeground(new Color(0,0,0,i));
                      Thread.sleep(10);
                   }

               } catch (InterruptedException e) {
                   System.out.println("Ddddd");
                   throw new RuntimeException(e);

               }


           }).start();


        }



//ddd
        JPanel plansza = new JPanel();
        plansza.setOpaque(false);
        plansza.setLayout(new BorderLayout());



        gora.add(plansza);

        JButton dalej =  new JButton();
        dalej.setPreferredSize(new Dimension(70,50));

        plansza.add(dalej,BorderLayout.EAST);

        //zamknij.addActionListener(e -> {pulpit.dispose();});




    }


    int[] punkty(){
        int punkty;
        int[] c = new int[3];
        int a = (int)Math.round(mapka.odleglosc);
        punkty = 5000 - a;
        c[0]=punkty;

        if(punkty<0){punkty=0;c[0]=0;} else if (punkty<2000){punkty+=500/tryb*30;c[1]=500/tryb*30;}else{punkty+=1000/tryb*30;c[1]=1000/tryb*30;}
        c[2]=punkty;
        return c;
    }

}
