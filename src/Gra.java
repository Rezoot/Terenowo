import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;


public class Gra extends Okno {

        boolean mouse,mapkakliknieta=true;
        ImageIcon zdj;
        int x ,y,t;

        JLabel glowne_zjecie,glowne_zjecieprzed,glowne_zjeciepo;
        JPanel wybormiejs;
        Dimension size;

        Gra(int time){

                t=time;

                glowne();




                addMouseListener(new MouseAdapter(){
                        @Override
                        public void mousePressed(MouseEvent e) {
                                if (e.getButton()==MouseEvent.BUTTON1){
                                        mouse=true;

                                        new Thread(() -> {
                                                int x1,x2,y1,y2;


                                                while(mouse){
                                                        x1=getMousePosition().x;
                                                        y1=getMousePosition().y;
                                                        x2=getMousePosition().x;
                                                        y2=getMousePosition().y;

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

                addMouseWheelListener(e -> {
                        int notches = e.getWheelRotation();
                        System.out.println(notches);
                });


                setVisible(true);
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



                add(glowne_zjecie);
                add(glowne_zjecieprzed);
                add(glowne_zjeciepo);




        }

        void labelki(){

                JPanel gora = new JPanel();
                gora.setBounds(0,0,szerokoscokna,50);
                add(gora);
                gora.setOpaque(false);

                JLabel odliczanie = new JLabel();
                odliczanie.setFont(new Font("Arial", Font.PLAIN, 30));
                odliczanie.setOpaque(true);
                odliczanie.setBackground(Color.BLACK);
                odliczanie.setForeground(Color.WHITE);
                odliczanie.setBorder(new LineBorder(Color.BLACK, 2));

                gora.add(odliczanie);


                Czas czas = new Czas(t,odliczanie);

                JPanel goraprawo =new JPanel();
                goraprawo.setBounds(0,10,szerokoscokna,50);
                add(goraprawo);
                goraprawo.setOpaque(false);
                goraprawo.setLayout(new BorderLayout());
                goraprawo.add(zamknij,BorderLayout.EAST);
                zamknij.addActionListener(e -> {dispose(); czas.zamknij();new Menu();});

                przyciski();





        }
        void przyciski(){

                JPanel cale = new JPanel();
                cale.setBounds(0,0,szerokoscokna,wysokoscokna);
                cale.setOpaque(false);
                add(cale);
                cale.setLayout(new BorderLayout());


                JPanel dol = new JPanel();
                dol.setOpaque(false);
                cale.add(dol,BorderLayout.SOUTH);
                dol.setLayout(new BorderLayout());


                wybormiejs = new JPanel();
                //wybormiejs.setBounds(szerokoscokna-310,wysokoscokna-300,260,100);
                wybormiejs.setVisible(mapkakliknieta);
                //wybormiejs.setOpaque(false);
                wybormiejs.setPreferredSize(new Dimension(400,350));
                dol.add(wybormiejs,BorderLayout.EAST);





                /*
                JButton przycisk = new JButton();
                przycisk.setIcon(map);

                przycisk.setBounds(1210,720,map.getIconWidth(), map.getIconHeight());
                przycisk.setOpaque(false);
                przycisk.setContentAreaFilled(false);
                przycisk.setBorderPainted(false);
                dol.add(przycisk,BorderLayout.EAST);
                */





                //dol.add(wybormiejs,BorderLayout.EAST);

                JButton Gdynia = new JButton("Gdynia");
                JButton Sopot = new JButton("Sopot");
                JButton Gdansk = new JButton("Gdańsk");

                wybormiejs.add(Gdynia);
                wybormiejs.add(Sopot);
                wybormiejs.add(Gdansk);



                /*przycisk.addActionListener(e -> {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                });
                 */
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



        }
        void fotka(){

                Zdjecie zdjecie = new Zdjecie();

                zdj=zdjecie.zdjecie;


        }




}
