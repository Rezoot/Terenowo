import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.ForkJoinPool;

public class Wynik extends Okno{
    String gra;
    int wynik;
    int plus;
    Map mapka;
    int tekst=50,mapa=400;
    String czas;
    double x1,x2,y1,y2;
    Wynik(String Czas,String Gra,double X1,double Y1,double X2,double Y2) {
        x1=X1;x2=X2;y1=Y1;y2=Y2;
        gra=Gra;
        czas=Czas;
        pulpit.setLayout(new BoxLayout(pulpit.getContentPane(),BoxLayout.Y_AXIS));
        //pulpit.setUndecorated(false);


        //Map mapka = new Map(szerokoscokna,wysokoscokna);
        //mapka.pulpit.setVisible(true);

        gora();
        gora2();

        mappa();

        dol();




       // pulpit.setUndecorated(true);
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

        zamknij.addActionListener(e -> {pulpit.dispose();});



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


        JLabel odliczanie = new JLabel("wynik: 100");
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
        //srodek.setBackground(Color.blue);
        srodek.setPreferredSize(new Dimension(600,300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.CENTER;

        pan.add(srodek,gbc);


        mapka = new Map(x1,x2,y1,y2,srodek);
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

        JLabel uzyskane = new JLabel("wynik: 100");
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
        dol2.setPreferredSize(new Dimension(szerokoscokna,wysokoscokna-mapa-3*tekst));

        JPanel odl = new JPanel();
        wynik.setOpaque(false);
        dol2.add(odl);

        JLabel km = new JLabel("odleglosc: "+ mapka.odleglosc);
        km.setFont(new Font("Arial", Font.PLAIN, 30));
        km.setOpaque(true);
        km.setBackground(Color.BLACK);
        km.setForeground(Color.WHITE);
        km.setBorder(new LineBorder(Color.BLACK, 2));
        odl.add(km,BorderLayout.CENTER);
    }

    void punkty(){}

}
