
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Gra extends Okno {
        private boolean mouse,mapkakliknieta=false;
        private ImageIcon zdj, map, potwierdz;
        private int x,y;
        final int t;
        double X,Y;
        Map mapka = new Map(szerokoscokna,wysokoscokna);
        private JLabel glowne_zjecie,glowne_zjecieprzed,glowne_zjeciepo;
        JLabel odliczanie;
        private Dimension size;
        private Czas czas;
        int wynik;
        int gra;
        int[] zagrane;
        Gra(int time, int Gra, int Wynik, int[] Zagrane){
                zagrane=Zagrane;
                wynik=Wynik;
                gra=Gra;
                t=time;
                pulpit.setLayout(null);

                glowne();

                przesuwanie();

                pulpit.setVisible(true);
        }

        void glowne() {

                fotka();

                labelki();


                glowne_zjecie = new JLabel(); //JLabel Creation
                glowne_zjecieprzed = new JLabel(); //JLabel Creation
                glowne_zjeciepo = new JLabel(); //JLabel Creation


                glowne_zjecie.setIcon(zdj);
                glowne_zjecieprzed.setIcon(zdj);
                glowne_zjeciepo.setIcon(zdj);

                size = glowne_zjecie.getPreferredSize(); //Gets the size of the image
                x=-zdj.getIconWidth()/2;
                y=-zdj.getIconHeight()/2;

                if (y<=-size.height+wysokoscokna) {
                        y=-size.height+wysokoscokna;

                }


                glowne_zjecie.setBounds(x,y,size.width,size.height);
                glowne_zjecieprzed.setBounds(x+size.width,y,size.width,size.height);
                glowne_zjeciepo.setBounds(x-size.width,y,size.width,size.height);



                pulpit.add(glowne_zjecie);
                pulpit.add(glowne_zjecieprzed);
                pulpit.add(glowne_zjeciepo);




        }

        void labelki(){

                JPanel gora = new JPanel();
                gora.setBounds(0,0,szerokoscokna,50);
                pulpit.add(gora);
                gora.setOpaque(false);

                odliczanie = new JLabel();
                odliczanie.setFont(new Font("Arial", Font.PLAIN, 30));
                odliczanie.setOpaque(true);
                odliczanie.setBackground(Color.BLACK);
                odliczanie.setForeground(Color.WHITE);
                odliczanie.setBorder(new LineBorder(Color.BLACK, 2));

                gora.add(odliczanie);

                czas = new Czas(t,odliczanie,this);

                JPanel goraprawo = new JPanel();
                goraprawo.setBounds(0,10,szerokoscokna,50);
                pulpit.add(goraprawo);
                goraprawo.setOpaque(false);
                goraprawo.setLayout(new BorderLayout());
                goraprawo.add(zamknij,BorderLayout.EAST);
                zamknij.addActionListener(e -> {
                        czas.apk=false;
                        pulpit.dispose();
                        mapka.dispose();
                        new Menu();
                });

                przyciski();
        }
        void przyciski(){

                JPanel cale = new JPanel();
                cale.setBounds(0,0,szerokoscokna,wysokoscokna);
                cale.setOpaque(false);
                pulpit.add(cale);
                cale.setLayout(new BorderLayout());


                JPanel dol = new JPanel();
                dol.setOpaque(false);
                cale.add(dol,BorderLayout.SOUTH);
                dol.setLayout(new BorderLayout());



                JPanel prawo = new JPanel();
                dol.add(prawo,BorderLayout.EAST);
                prawo.setLayout(new BorderLayout());
                prawo.setOpaque(false);

                JButton przycisk = new JButton();
                przycisk.setIcon(map);
                przycisk.setOpaque(false);
                przycisk.setContentAreaFilled(false);
                przycisk.setBorderPainted(false);

                JButton zatwierdz = new JButton();
                zatwierdz.setIcon(potwierdz);
                zatwierdz.setOpaque(false);
                zatwierdz.setContentAreaFilled(false);
                zatwierdz.setBorderPainted(false);
                zatwierdz.setVisible(mapkakliknieta);

                prawo.add(zatwierdz,BorderLayout.WEST);
                prawo.add(przycisk,BorderLayout.CENTER);

                przycisk.addActionListener(e -> {
                        mapkakliknieta=!mapkakliknieta;
                        mapka.widoczny(mapkakliknieta);
                        zatwierdz.setVisible(mapkakliknieta);
                });

                zatwierdz.addActionListener(e -> {
                        pulpit.dispose();
                        mapka.pulpit.dispose();
                        czas.apk=false;
                        new Wynik(t, odliczanie.getText(),gra,mapka.longp,mapka.latp,X,Y,wynik,mapka.klikniete,zagrane);

                });

        }
        void fotka(){
                map = new ImageIcon("map.png");
                potwierdz = new ImageIcon("zatwierdz.png");

                Zdjecie zdjecie = new Zdjecie(zagrane);
                zagrane[gra-1]=zdjecie.wyl;



                zdj=zdjecie.zdjecie;
                X=zdjecie.x;
                Y=zdjecie.y;

        }

        void przesuwanie() {
                pulpit.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mousePressed(MouseEvent e) {
                                if (e.getButton()==MouseEvent.BUTTON1){
                                        mouse=true;
                                        new Thread(() -> {
                                                int x1,x2,y1,y2;
                                                while(mouse){
                                                        x1=pulpit.getMousePosition().x;
                                                        y1=pulpit.getMousePosition().y;
                                                        x2=pulpit.getMousePosition().x;
                                                        y2=pulpit.getMousePosition().y;

                                                        x+=(x2-x1)*5;
                                                        y+=(y2-y1)*5;

                                                        if(y>=0){
                                                                y=0;
                                                        } else if (y<=-size.height+wysokoscokna) {
                                                                y=-size.height+wysokoscokna;

                                                        }

                                                        if (x<-size.width-szerokoscokna/2){
                                                                x=x+size.width;
                                                        } else if (x>szerokoscokna/2) {
                                                                x=x-size.width;
                                                        }
                                                        glowne_zjecie.setBounds(x,y,size.width, size.height);
                                                        glowne_zjecieprzed.setBounds(x+size.width,y,size.width, size.height);
                                                        glowne_zjeciepo.setBounds(x-size.width,y,size.width, size.height);

                                                }
                                        }).start();
                                }
                        }
                        @Override
                        public void mouseReleased(MouseEvent e) {
                                if(e.getButton()==MouseEvent.BUTTON1) {
                                        mouse = false;
                                }
                        }
                });

                /*
                pulpit.addMouseWheelListener(e -> {
                        int notches = e.getWheelRotation();
                        System.out.println(notches);
                });
                */

        }
}