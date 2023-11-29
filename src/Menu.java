import javax.swing.*;
//import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Menu extends Okno{

    JFrame pulpit;
    Menu()
    {
        pulpit = new JFrame();
        pulpit.setName("menu");
        pulpit.setName("menu");
        pulpit.setSize(400,400);
        pulpit.setLocationRelativeTo(null);
        pulpit.setUndecorated(true);
        pulpit.setLayout(new BoxLayout(pulpit.getContentPane(), BoxLayout.Y_AXIS));
        pulpit.setLocationRelativeTo(null);


        pierwszy_zamkniecia();
        drugi_glowny();



        pulpit.setVisible(true);

    }

    void pierwszy_zamkniecia()
    {
        JPanel pierwszy = new JPanel();
        pierwszy.setBackground(Color.RED);
        pulpit.add(pierwszy);
        pierwszy.setPreferredSize(new Dimension(400,40));
        pierwszy.setLayout(new BorderLayout());


        pierwszy.add(zamknij,BorderLayout.EAST);

        zamknij.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
            pulpit.dispose();
        }});



    }
    void drugi_glowny()
    {

        JPanel drugi = new JPanel();
        drugi.setBackground(Color.BLACK);
        pulpit.add(drugi);
        drugi.setPreferredSize(new Dimension(400,360));
        drugi.setLayout(new BoxLayout(drugi,BoxLayout.Y_AXIS));


        JButton graj = new JButton("Graj");
        drugi.add(graj);
        drugi.add(new JButton("Przycisk 2"));
        drugi.add(new JButton("Przycisk 3"));


        graj.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
            Gra glowna = new Gra();

        }});

    }
}

