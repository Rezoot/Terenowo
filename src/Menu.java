import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Menu extends Okno{

    JFrame pulpit;
    String color="#f5d0ef",color_napis="#5c555b";
    JPanel fasolka;
    JLabel tek;
    int time=90;
    Menu()
    {

        pulpit = new JFrame();
        pulpit.setName("menu");
        pulpit.setName("menu");
        pulpit.setSize(400,400);
        pulpit.setLocationRelativeTo(null);
        pulpit.setUndecorated(true);
        //pulpit.setLayout(new BoxLayout(pulpit.getContentPane(), BoxLayout.Y_AXIS));
        pulpit.setLocationRelativeTo(null);
        pulpit.setBackground(new Color(0, 0, 0, 0));

        //główny frame


        fasolka = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.decode(color));
                g.fillArc(0, 300, getWidth(), 100,180,180);
                g.fillArc(0, 0, getWidth(), 100,0,180);
                int[] x = {0,getWidth(),getWidth(),0};
                int[] y = {50,50,350,350};
                g.fillPolygon(x,y,4);
            }
        };
        fasolka.setBackground(new Color(0,0,0,0));

        pulpit.add(fasolka);
        fasolka.setLayout(new BoxLayout(fasolka, BoxLayout.Y_AXIS));

        pierwszy_zamkniecia();//góra gdzie jest wyjście
        drugi_glowny();

        //przyciski tytuł



        pulpit.setVisible(true);

    }

    void pierwszy_zamkniecia()
    {
        JPanel pierwszy = new JPanel();
        fasolka.add(pierwszy);


        pierwszy.setPreferredSize(new Dimension(400,60));
        pierwszy.setLayout(new BorderLayout());
        pierwszy.setOpaque(false);

        JPanel prawy = new JPanel();
        prawy.setLayout(new BorderLayout());
        prawy.setOpaque(false);

        pierwszy.add(prawy,BorderLayout.EAST);

        prawy.add(zamknij,BorderLayout.SOUTH);
        zamknij.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
            pulpit.dispose();
        }});




    }
    void drugi_glowny()
    {

        JPanel drugi = new JPanel();
        fasolka.add(Box.createVerticalGlue());
        fasolka.add(drugi);

        drugi.setPreferredSize(new Dimension(400,330));
        drugi.setOpaque(false);

        //duży panel

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        center.setOpaque(false);
        drugi.add(center);

        //panel wycentrowany

        JPanel tyt = new JPanel();
        tyt.setOpaque(false);
        JLabel tytul=new JLabel("TERENOWO");
        tytul.setForeground(Color.decode(color_napis));
        tytul.setFont(new Font("Arial", Font.BOLD, 40));
        tyt.add(tytul);

        JPanel czas = new JPanel();
        czas.setOpaque(false);
        tek = new JLabel("1:30");
        tek.setForeground(Color.decode(color_napis));
        czas.add(tek);

        JPanel guzik =new JPanel();
        guzik.setOpaque(false);
        JButton graj = new JButton("START");
        guzik.add(graj);

        JPanel wyb = jradiobutony();


        center.add(Box.createVerticalStrut(10));
        center.add(tyt);
        center.add(Box.createVerticalStrut(30));
        center.add(czas);
        center.add(Box.createVerticalStrut(30));
        center.add(guzik);
        center.add(Box.createVerticalStrut(30));
        center.add(wyb);

        graj.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
            Gra glowna = new Gra(time);

        }});

    }
    JPanel jradiobutony(){

        JPanel wyb = new JPanel();
        wyb.setOpaque(false);
        wyb.setLayout(new GridLayout(2,3));
        wyb.setBorder(new EmptyBorder(0,40,0,0));
        JLabel nap1 = new JLabel("30s");
        JLabel nap2 = new JLabel("60s");
        JLabel nap3 = new JLabel("90s");

        nap1.setForeground(Color.decode(color_napis));
        nap2.setForeground(Color.decode(color_napis));
        nap3.setForeground(Color.decode(color_napis));

        ButtonGroup group = new ButtonGroup();

        JRadioButton cz = new JRadioButton();
        JRadioButton cz2 = new JRadioButton();
        JRadioButton cz3 = new JRadioButton();

        cz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tek.setText("0:30"); time=30;
            }
        });
        cz2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tek.setText("1:00"); time=60;
            }
        });
        cz3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tek.setText("1:30"); time=90;
            }
        });


        cz3.setSelected(true);

        cz.setOpaque(false);
        cz2.setOpaque(false);
        cz3.setOpaque(false);

        group.add(cz);
        group.add(cz2);
        group.add(cz3);

        wyb.add(cz);
        wyb.add(cz2);
        wyb.add(cz3);
        wyb.add(nap1);
        wyb.add(nap2);
        wyb.add(nap3);
        return wyb;






    }


}

