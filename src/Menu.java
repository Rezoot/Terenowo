import javax.swing.*;
import javax.swing.border.LineBorder;
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
        //pulpit.setUndecorated(true);
        pulpit.setLocationRelativeTo(null);
        pulpit.setVisible(true);
        pulpit.setLayout(new BoxLayout(pulpit.getContentPane(), BoxLayout.Y_AXIS));



        pierwszy_zamkniecia();
        drugi_glowny();

    }

    void pierwszy_zamkniecia()
    {
        JPanel pierwszy = new JPanel();
        pierwszy.setBackground(Color.RED);
        pulpit.add(pierwszy);
        pierwszy.setPreferredSize(new Dimension(400,50));
        pierwszy.setLayout(new BorderLayout());

        JButton zamknij = new JButton("zamknij");
        pierwszy.setLayout(new BorderLayout());
        pierwszy.add(zamknij,BorderLayout.EAST);

    }
    void drugi_glowny()
    {

        JPanel drugi = new JPanel();
        drugi.setBackground(Color.BLACK);
        pulpit.add(drugi);
        drugi.setPreferredSize(new Dimension(400,350));
        drugi.setLayout(new BoxLayout(pulpit.getContentPane(), BoxLayout.Y_AXIS));




    }
}

