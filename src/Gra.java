import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;


public class Gra extends Okno {

        boolean mouse,mapkakliknieta=false;
        ImageIcon zdj;
        int x ,y;

        JLabel glowne_zjecie,glowne_zjecieprzed,glowne_zjeciepo;
        JPanel wybormiejs;
        Dimension size;

        Gra(){


                glowne();


/*
                JScrollPane scrollPane = new JScrollPane();

                scrollPane.addMouseWheelListener(new MouseWheelListener() {
                        public void mouseWheelMoved(MouseWheelEvent e) {
                                int notches = e.getWheelRotation();
                                JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                                int scrollValue = verticalScrollBar.getValue();
                                int scrollAmount = verticalScrollBar.getUnitIncrement(notches);
                                verticalScrollBar.setValue(scrollValue + scrollAmount * notches);
                                System.out.println(e.getWheelRotation());
                        }

                });
*/

                addMouseListener(new MouseAdapter(){
                        @Override
                        public void mousePressed(MouseEvent e) {
                                if (e.getButton()==MouseEvent.BUTTON1){
                                        mouse=true;

                                        new Thread() {
                                                public void run(){
                                                        int x1,x2,y1,y2;
                                                        int buforx,bufory;

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
                                                }

                                        }.start();
                                }

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                                if(e.getButton()==MouseEvent.BUTTON1) {
                                        mouse = false;
                                }

                        }

                        @Override
                        public void mouseWheelMoved(MouseWheelEvent e) {
                                super.mouseWheelMoved(e);
                                System.out.println("DASDA");
                        }
                });

                setVisible(true);
        }

        void glowne() {

                fotka();
                x=-zdj.getIconWidth()/2;
                y=-zdj.getIconHeight()/2;

                JPanel top = new JPanel();
                JPanel Bottom = new JPanel();
                JPanel full = new JPanel();

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

                JLabel odliczanie = new JLabel();

                odliczanie.setFont(new Font("Arial", Font.PLAIN, 30));
                odliczanie.setBounds(szerokoscokna/2,-0,65,30);
                odliczanie.setOpaque(true);
                odliczanie.setBackground(Color.BLACK);
                odliczanie.setForeground(Color.WHITE);
                odliczanie.setBorder(new LineBorder(Color.BLACK, 2));
                add(odliczanie);
                Czas czas = new Czas(5,odliczanie);


                przyciski();





        }
        void przyciski(){
                JButton przycisk = new JButton();
                przycisk.setIcon(map);
                add(przycisk);
                przycisk.setBounds(1210,720,map.getIconWidth(), map.getIconHeight());
                przycisk.setOpaque(false);
                przycisk.setContentAreaFilled(false);
                przycisk.setBorderPainted(false);



                wybormiejs = new JPanel();
                //wybormiejs.setBounds(szerokoscokna-310,wysokoscokna-500,300,300);
                wybormiejs.setBounds(szerokoscokna-310,wysokoscokna-300,260,100);
                wybormiejs.setVisible(mapkakliknieta);
                wybormiejs.setOpaque(false);



                add(wybormiejs);

                JButton Gdynia = new JButton("Gdynia");
                JButton Sopot = new JButton("Sopot");
                JButton Gdansk = new JButton("Gdańsk");

                wybormiejs.add(Gdynia);
                wybormiejs.add(Sopot);
                wybormiejs.add(Gdansk);



                przycisk.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                }});
                Gdansk.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                }});
                Gdynia.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                }});
                Sopot.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                        mapkakliknieta=!mapkakliknieta;
                        wybormiejs.setVisible(mapkakliknieta);
                }});



        }
        void fotka(){

                Zdjecie zdjecie = new Zdjecie();

                zdj=zdjecie.zdjecie;


        }




}
