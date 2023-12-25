import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Gra extends Okno {

        boolean mouse,mapkakliknieta=false;
        ImageIcon zdj, map;
        int x ,y ,t;
        Mapka mapka = new Mapka();
        JLabel glowne_zjecie,glowne_zjecieprzed,glowne_zjeciepo;
        Dimension size;

        Gra(int time){


                t=time;
                pulpit.setLayout(null);



                glowne();


                przesuwanie();


                pulpit.setVisible(true);
        }

        void glowne() {

                fotka();
                x=-zdj.getIconWidth()/2;
                y=-zdj.getIconHeight()/2;


                labelki();


                glowne_zjecie = new JLabel(); //JLabel Creation
                glowne_zjecieprzed = new JLabel(); //JLabel Creation
                glowne_zjeciepo = new JLabel(); //JLabel Creation


                glowne_zjecie.setIcon(zdj);
                glowne_zjecieprzed.setIcon(zdj);
                glowne_zjeciepo.setIcon(zdj);

                size = glowne_zjecie.getPreferredSize(); //Gets the size of the image
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

                JLabel odliczanie = new JLabel();
                odliczanie.setFont(new Font("Arial", Font.PLAIN, 30));
                odliczanie.setOpaque(true);
                odliczanie.setBackground(Color.BLACK);
                odliczanie.setForeground(Color.WHITE);
                odliczanie.setBorder(new LineBorder(Color.BLACK, 2));

                gora.add(odliczanie);


                Czas czas = new Czas(t,odliczanie,pulpit,mapka.pulpit);

                JPanel goraprawo =new JPanel();
                goraprawo.setBounds(0,10,szerokoscokna,50);
                pulpit.add(goraprawo);
                goraprawo.setOpaque(false);
                goraprawo.setLayout(new BorderLayout());
                goraprawo.add(zamknij,BorderLayout.EAST);
                zamknij.addActionListener(e -> {czas.zamknij();new Menu();});


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


                JPanel dol1 = new JPanel();
                dol1.setOpaque(false);
                dol.add(dol1,BorderLayout.CENTER);
                dol1.setLayout(new BorderLayout());

                JPanel dol2 = new JPanel();
                dol2.setOpaque(false);
                dol.add(dol2,BorderLayout.SOUTH);
                dol2.setLayout(new BorderLayout());





                JPanel prawo = new JPanel();
                dol2.add(prawo,BorderLayout.EAST);
                prawo.setLayout(new BorderLayout());
                prawo.setOpaque(false);


                JButton przycisk = new JButton();
                przycisk.setIcon(map);
                przycisk.setOpaque(false);
                przycisk.setContentAreaFilled(false);
                przycisk.setBorderPainted(false);


                JPanel przyciski = new JPanel();
                przyciski.setLayout(new FlowLayout(FlowLayout.LEFT));
                przyciski.setOpaque(false);
                przyciski.setVisible(mapkakliknieta);
                prawo.add(przycisk,BorderLayout.CENTER);

                /*
                JButton Gdynia = new JButton("Gdynia");
                JButton Sopot = new JButton("Sopot");
                JButton Gdansk = new JButton("Gdańsk");


                przyciski.add(Gdynia);
                przyciski.add(Sopot);
                przyciski.add(Gdansk);
                prawo.add(przyciski,BorderLayout.WEST);


//DDDDD



                Gdansk.addActionListener(e -> {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                });
                Gdynia.addActionListener(e -> {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                });
                Sopot.addActionListener(e -> {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                });
*/
                przycisk.addActionListener(e -> {
                        mapkakliknieta=!mapkakliknieta;
                        //przyciski.setVisible(mapkakliknieta);
                        //wybormiejs.setVisible(mapkakliknieta);
                        mapka.chowanie(mapkakliknieta);
                });


        }
        void fotka(){

                map = new ImageIcon("map.png");
                Zdjecie zdjecie = new Zdjecie();
                zdj=zdjecie.zdjecie;


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

                pulpit.addMouseWheelListener(e -> {
                        int notches = e.getWheelRotation();
                        System.out.println(notches);
                });


        }


}
