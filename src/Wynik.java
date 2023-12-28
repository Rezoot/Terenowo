import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Wynik extends Okno{
    String gra;
    int wynik;
    int plus;
    int gorawys=50,gora2wys=50,mapa=100,dol=100;
    String czas;
    double x1,x2,y1,y2;
    Wynik(String Czas,String Gra,double X1,double Y1,double X2,double Y2)
    {
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



       // pulpit.setUndecorated(true);
        pulpit.setVisible(true);

    }
    void gora() {
        JPanel gora = new JPanel();
        pulpit.add(gora);
        gora.setLayout(new OverlayLayout(gora));
        gora.setOpaque(false);
        gora.setPreferredSize(new Dimension(szerokoscokna,gorawys));

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
        gora.setPreferredSize(new Dimension(szerokoscokna,gora2wys));

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
        pan.setPreferredSize(new Dimension(szerokoscokna, wysokoscokna-gorawys-gora2wys));
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


        new Map(x1,51,y1,18,srodek);
        pan.setBackground(Color.PINK);
        pulpit.add(pan);


    }


    void punkty(){}

}
